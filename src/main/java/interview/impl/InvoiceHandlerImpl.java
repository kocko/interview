package interview.impl;

import interview.InvoiceHandler;
import interview.Writer;
import interview.bean.Invoice;
import interview.csv.CsvInvoicesParser;
import interview.csv.CsvInvoicesWriter;
import interview.enums.OutputFormat;
import interview.utils.FileUtils;
import interview.xml.XmlInvoicesWriter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class InvoiceHandlerImpl implements InvoiceHandler {
	
	private CsvInvoicesWriter csvWriter;
	private XmlInvoicesWriter xmlWriter;
	
	public InvoiceHandlerImpl() {
		csvWriter = new CsvInvoicesWriter();
		xmlWriter = new XmlInvoicesWriter();
	}

	public boolean ingestInvoiceData(File file, Set<OutputFormat> outputFormats) throws IllegalArgumentException {
		Objects.requireNonNull(file, "You have to provide a non-null reference to a file");
		Objects.requireNonNull(outputFormats, "You have to provice a non-null list of output formats");
		if (outputFormats.size() == 0) {
			throw new IllegalArgumentException("You have to provide a non-empty list of output formats!");
		}
		
		Map<String, List<Invoice>> parsedData = null;
		
		try {
			parsedData = CsvInvoicesParser.parseCsvData(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Boolean result = Boolean.FALSE;
		
		if (parsedData != null && FileUtils.makeOutputDir()) {
			for (OutputFormat format: outputFormats) {
				Optional<Writer<?>> writerExistence = getWriterForOutputFormat(format);
				if (writerExistence.isPresent()) {
					Writer<?> writer = writerExistence.get();
					writer.process(parsedData);
				}
			}
			result = Boolean.TRUE;
		}
		
		return result;
	}
	
	private Optional<Writer<?>> getWriterForOutputFormat(OutputFormat outputFormat) {
		Optional<Writer<?>> result = Optional.empty();
		switch (outputFormat){
			case CSV: {
				result = Optional.of(csvWriter);
			}
			case XML: {
				result = Optional.of(xmlWriter); 
			}
		}
		return result;
	}
}

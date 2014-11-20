package interview.impl;

import interview.InvoiceHandler;
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
import java.util.Set;

public class InvoiceHandlerImpl implements InvoiceHandler {

	public boolean ingestInvoiceData(File file, Set<OutputFormat> outputFormats) throws IllegalArgumentException {
		if (outputFormats == null || outputFormats.size() == 0) {
			throw new IllegalArgumentException("You have to provide non-null and non-empty list of output formats!");
		}
		
		Map<String, List<Invoice>> parsedData = null;
		
		try {
			parsedData = CsvInvoicesParser.parseCsvData(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CsvInvoicesWriter csvWriter = new CsvInvoicesWriter();
		XmlInvoicesWriter xmlWriter = new XmlInvoicesWriter();
		
		Boolean result = null;
		
		if (parsedData != null && FileUtils.makeOutputDir()) {
			for (OutputFormat format: outputFormats) {
				if (format.equals(OutputFormat.CSV)) {
					csvWriter.process(parsedData);
				}
				
				if (format.equals(OutputFormat.XML)) {
					xmlWriter.process(parsedData);
				}
			}
			result = Boolean.TRUE;
		} else {
			result = Boolean.FALSE;
		}
		
		return result;
	}
}

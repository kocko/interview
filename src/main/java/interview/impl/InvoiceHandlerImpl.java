package interview.impl;

import interview.InvoiceHandler;
import interview.bean.Invoice;
import interview.csv.CSVInvoicesWriter;
import interview.enums.OutputFormat;
import interview.utils.FileUtils;
import interview.xml.XmlInvoicesWriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class InvoiceHandlerImpl implements InvoiceHandler {

	public boolean ingestInvoiceData(File file, Set<OutputFormat> outputFormats) throws IllegalArgumentException {
		if (outputFormats == null || outputFormats.size() == 0) {
			throw new IllegalArgumentException("You have to provide non-null and non-empty list of output formats!");
		}
		
		Map<String, List<Invoice>> parsedData = null;
		
		try {
			parsedData = parseData(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CSVInvoicesWriter csvWriter = new CSVInvoicesWriter();
		XmlInvoicesWriter xmlWriter = new XmlInvoicesWriter();
		
		Boolean result = null;
		
		if (parsedData != null && FileUtils.makeOutputDir()) {
			for (OutputFormat format: outputFormats) {
				if (format.equals(OutputFormat.CSV)) {
					csvWriter.writeSplitByBuyerDataToFiles(parsedData);
				}
				
				if (format.equals(OutputFormat.XML)) {
					xmlWriter.writeToXML(parsedData);
				}
			}
			result = Boolean.TRUE;
		} else {
			result = Boolean.FALSE;
		}
		
		return result;
	}

	private Map<String, List<Invoice>> parseData(File file) throws IOException {
		CSVFormat format = CSVFormat.EXCEL.withHeader().withDelimiter(',');

		CSVParser parser = new CSVParser(new FileReader(file), format);

		List<Invoice> invoices = new ArrayList<Invoice>();

		Map<String, List<Invoice>> result = new HashMap<String, List<Invoice>>();

		for (CSVRecord record : parser.getRecords()) {
			Invoice invoice = new Invoice();

			invoice.setId(record.get(0));
			invoice.setInvoiceNo(record.get(1));

			String buyer = record.get(2);
			invoice.setBuyer(buyer);
			invoice.setAmount(new BigDecimal(record.get(3)));
			invoice.setCurrency(record.get(4));
			invoice.setPurchaseOrder(record.get(5));
			invoice.setInvoiceImage(record.get(6));
			invoice.setImageName(record.get(7));
			invoice.setItemNumber(Integer.valueOf(record.get(8)));
			invoice.setItemAmount(new BigDecimal(record.get(9)));
			invoice.setQuantity(Double.valueOf(record.get(10)));

			invoices.add(invoice);
			if (result.containsKey(buyer)) {
				result.get(buyer).add(invoice);
			} else {
				List<Invoice> newBuyerList = new ArrayList<Invoice>();
				newBuyerList.add(invoice);
				result.put(buyer, newBuyerList);
			}

		}
		parser.close();
		
		return result;
	}
}

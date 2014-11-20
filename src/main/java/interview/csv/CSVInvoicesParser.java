package interview.csv;

import interview.bean.Invoice;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVInvoicesParser {

	public Map<String, List<Invoice>> parseCsvData(File file) throws IOException {
		CSVFormat format = CSVFormat.EXCEL.withHeader().withDelimiter(',');
		CSVParser parser = new CSVParser(new FileReader(file), format);

		Map<String, List<Invoice>> result = new HashMap<String, List<Invoice>>();

		for (CSVRecord record : parser.getRecords()) {
			Invoice invoice = new Invoice();

			invoice.setId(record.get(0));
			invoice.setInvoiceNo(record.get("invoice_number"));
			String buyer = record.get("buyer");
			invoice.setBuyer(buyer);
			invoice.setAmount(new BigDecimal(record.get("amount")));
			invoice.setCurrency(record.get("currency"));
			invoice.setPurchaseOrder(record.get("purchase_order"));
			invoice.setInvoiceImage(record.get("invoice_image"));
			invoice.setImageName(record.get("image_name"));
			invoice.setItemNumber(Integer.valueOf(record.get("item_number")));
			invoice.setItemAmount(new BigDecimal(record.get("amount1")));
			invoice.setQuantity(Double.valueOf(record.get("quantity")));

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

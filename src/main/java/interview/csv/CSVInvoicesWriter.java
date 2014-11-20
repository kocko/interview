package interview.csv;

import interview.bean.Invoice;
import interview.enums.OutputFormat;
import interview.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVInvoicesWriter {

	public void writeSplitByBuyerDataToFiles(Map<String, List<Invoice>> data) {
		CSVFormat format = CSVFormat.EXCEL.withHeader().withDelimiter(',');
		for (Map.Entry<String, List<Invoice>> entry : data.entrySet()) {
			String buyerName = entry.getKey();
			List<Invoice> value = entry.getValue();
			CSVPrinter printer = null;
			try {
				File outputFile = new File(FileUtils.OUTPUT_FOLDER + File.separator + buyerName + OutputFormat.CSV.getFileExtension());
				PrintStream printStream = new PrintStream(outputFile);
				printer = new CSVPrinter(printStream, format.withDelimiter(','));
				printer.printRecord("id", "invoice_number", "buyer", "amount", 
									"currency", "purchase_order", "invoice_image", "image_name",
									"item_number", "amount", "quantity");
				for (Invoice invoice : value) {
					printer.printRecord(invoice.getId(), invoice.getInvoiceNo(), invoice.getBuyer(), invoice.getAmount(),
										invoice.getCurrency(), invoice.getPurchaseOrder(), invoice.getInvoiceImage(), invoice.getImageName(),
										invoice.getItemNumber(), invoice.getItemAmount(), invoice.getQuantity());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					printer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

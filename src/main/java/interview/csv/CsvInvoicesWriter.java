package interview.csv;

import interview.Writer;
import interview.bean.Invoice;
import interview.bean.Invoice_;
import interview.enums.OutputFormat;
import interview.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvInvoicesWriter implements Writer<List<Invoice>> {

	public void process(Map<String, List<Invoice>> data) {
		data.entrySet()
		    .stream()
		    .forEach( entry -> {
		    	writeInvoicesToFile(entry.getKey(), entry.getValue());
		    });
	}
	
	@Override
	public void writeInvoicesToFile(String filename, List<Invoice> invoices) {
		CSVFormat format = CSVFormat.EXCEL.withHeader().withDelimiter(',');
		CSVPrinter printer = null;
		try {
			File outputFile = new File(FileUtils.OUTPUT_FOLDER + File.separator + filename + OutputFormat.CSV.getFileExtension());
			PrintStream printStream = new PrintStream(outputFile);
			printer = new CSVPrinter(printStream, format.withDelimiter(','));
			printer.printRecord(Invoice_.id, Invoice_.invoiceNo, Invoice_.buyer, Invoice_.amount, 
								Invoice_.currency, Invoice_.purchaseOrder, Invoice_.invoiceImage, 
								Invoice_.imageName, Invoice_.itemNumber, Invoice_.itemAmount, Invoice_.quantity);
			for (Invoice invoice : invoices) {
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

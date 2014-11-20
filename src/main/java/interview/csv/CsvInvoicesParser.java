package interview.csv;

import interview.bean.Invoice;
import interview.bean.Invoice_;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Parses a CSV file containing invoice data.
 * 
 * @author Konstantin Yovkov
 *
 */
public final class CsvInvoicesParser {

	public static Map<String, List<Invoice>> parseCsvData(File file) throws IOException {
		Function<CSVRecord, Invoice> mapCsvRecordToInvoice = record -> {
			Invoice invoice = new Invoice();
			invoice.setId(record.get(0));
			invoice.setInvoiceNo(record.get(Invoice_.id));
			invoice.setBuyer(record.get(Invoice_.buyer));
			invoice.setAmount(new BigDecimal(record.get(Invoice_.amount)));
			invoice.setCurrency(record.get(Invoice_.currency));
			invoice.setPurchaseOrder(record.get(Invoice_.purchaseOrder));
			invoice.setInvoiceImage(record.get(Invoice_.invoiceImage));
			invoice.setImageName(record.get(Invoice_.imageName));
			invoice.setItemNumber(Integer.valueOf(record.get(Invoice_.itemNumber)));
			invoice.setItemAmount(new BigDecimal(record.get(Invoice_.itemAmount)));
			invoice.setQuantity(Double.valueOf(record.get(Invoice_.quantity)));
			return invoice;
		};

		CSVFormat format = CSVFormat.EXCEL.withHeader().withDelimiter(',');
		CSVParser parser = new CSVParser(new FileReader(file), format);
		
		Map<String, List<Invoice>> result = parser.getRecords()
												  .stream()
												  .map(mapCsvRecordToInvoice)
												  .collect(Collectors.groupingBy(Invoice::getBuyer));
		parser.close();
		return result;
	}

}

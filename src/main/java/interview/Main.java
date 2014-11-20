package interview;

import interview.enums.OutputFormat;
import interview.impl.InvoiceHandlerImpl;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws IOException {
		InvoiceHandler invoiceHandler = new InvoiceHandlerImpl();
		
		Set<OutputFormat> set = new TreeSet<OutputFormat>();
		set.add(OutputFormat.CSV);
		set.add(OutputFormat.XML);
		boolean result = invoiceHandler.ingestInvoiceData(new File("loadData_small.csv"), set);
		System.out.println(result ? "Success! Check for an 'output' folder!" : "Oops...some error appeared.");
	}
}

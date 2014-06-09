interview
=========

The application by a simple main(String[] args) method. 

For example:

public class Main {

	public static void main(String[] args) throws IOException {
		TauliaInvoiceHandler taulia = new TauliaInvoiceHandlerImpl();
		
		Set<OutputFormat> set = new TreeSet<OutputFormat>();
		set.add(OutputFormat.CSV);
		set.add(OutputFormat.XML);
		boolean result = taulia.ingestInvoiceData(new File("loadData_small.csv"), set);
		System.out.println(result ? "Success! Check for an 'output' folder!" : "Oops...some error appeared.");
	}
}


The application can be started by Maven, as well. 

1) In a command-prompt, navigate to the project root.
2) Type
   
     mvn exec:java -D exec.mainClass=interview.Main 

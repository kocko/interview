package interview;

import interview.enums.OutputFormat;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Represents a handler for parsing large CSV files
 * to a specified {@link OutputFormat}.
 * 
 * @author Konstantin Yovkov
 * 
 */
public interface InvoiceHandler {

	/**
	 * /**
	 * Ingests and parses a given file to the specified output format(s).
	 * 
	 * @param file
	 *            A CSV file, containing the data for parsing.
	 * @param outputFormats
	 *            A set of the desired {@link OutputFormat}s for the parsed
	 *            data.
	 * @return true, if the ingestion and parsing succeeds. Otherwise, false.
	 * @throws IllegalArgumentException
	 *             When the outputParam list is null or empty.
	 * @throws IOException
	 * 			   When an I/O error occurs.
	 */
	public boolean ingestInvoiceData(File file, Set<OutputFormat> outputFormats)
			throws IllegalArgumentException, IOException;

}

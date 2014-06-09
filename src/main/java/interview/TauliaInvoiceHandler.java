package interview;

import interview.enums.OutputFormat;

import java.io.File;
import java.util.Set;

/**
 * An interface, containing a single abstract method for parsing large CSV files
 * to a specified output format.
 * 
 * @author Konstantin Yovkov
 * 
 */
public interface TauliaInvoiceHandler {

	/**
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
	 */
	public boolean ingestInvoiceData(File file, Set<OutputFormat> outputFormats)
			throws IllegalArgumentException;

}

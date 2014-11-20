package interview;

import interview.bean.Invoice;

import java.util.List;
import java.util.Map;

/**
 * Writes a batch of data (e.g. single {@link Invoice} object or a {@List
 * } of {@Invoice} objects) to a single file.
 * 
 * @author Konstantin Yovkov
 *
 * @param <T>
 *            The type of the data that will be written in a single file.
 */
public interface Writer<T> {

	public void process(Map<String, List<Invoice>> data);

	public void writeInvoicesToFile(String filenamePrefix, T data);

}

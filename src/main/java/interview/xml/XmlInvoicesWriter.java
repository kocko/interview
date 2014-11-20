package interview.xml;

import interview.Writer;
import interview.bean.Invoice;
import interview.enums.OutputFormat;
import interview.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlInvoicesWriter implements Writer<Invoice> {

	@Override
	public void process(Map<String, List<Invoice>> data) {
		data.entrySet()
			.stream()
			.forEach(entry -> {
				entry.getValue()
					 .stream()
					 .forEach(invoice -> writeInvoicesToFile(FileUtils.createUniqueName(entry.getKey()), invoice));
			});	
	}
	
	@Override
	public void writeInvoicesToFile(String filename, Invoice invoice) {
		try {
			File resultFile = new File(FileUtils.OUTPUT_FOLDER + File.separator + filename + OutputFormat.XML.getFileExtension());
			JAXBContext jaxbContext = JAXBContext.newInstance(Invoice.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(invoice, resultFile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
}

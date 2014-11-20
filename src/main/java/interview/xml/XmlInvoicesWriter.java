package interview.xml;

import interview.bean.Invoice;
import interview.enums.OutputFormat;
import interview.utils.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlInvoicesWriter {

	public void writeToXML(Map<String, List<Invoice>> data) {
		
		for (Map.Entry<String, List<Invoice>> entry : data.entrySet()) {
			String buyerName = entry.getKey();
			List<Invoice> invoices = entry.getValue();
			for (int i = 0; i < invoices.size(); i++) {
				persistInvoiceForBuyer(buyerName + "_" + i, invoices.get(i));
			}
		}		
	}
	
	private void persistInvoiceForBuyer(String filename, Invoice invoice) {
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

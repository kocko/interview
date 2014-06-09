package interview.bean;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Invoice {

	private String id;
	
	private String invoiceNo;
	
	private String buyer;
	
	private BigDecimal amount;
	
	private String currency;
	
	private String purchaseOrder;
	
	private String invoiceImage;
	
	private String imageName;
	
	private Integer itemNumber;
	
	private BigDecimal itemAmount;
	
	private Double quantity;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	@XmlElement(name = "invoice_number")
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	@XmlElement(name = "purchase_order")
	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getInvoiceImage() {
		return invoiceImage;
	}
	
	@XmlTransient
	public void setInvoiceImage(String invoiceImage) {
		this.invoiceImage = invoiceImage;
	}

	public String getImageName() {
		return imageName;
	}

	@XmlElement(name = "image_name")
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getItemNumber() {
		return itemNumber;
	}

	@XmlElement(name = "item_number")
	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	public BigDecimal getItemAmount() {
		return itemAmount;
	}

	@XmlElement(name = "item_amount")
	public void setItemAmount(BigDecimal itemAmount) {
		this.itemAmount = itemAmount;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}

package interview.enums;

public enum OutputFormat {
	CSV(".csv"), XML(".xml");
	
	private String fileExtension;
	
	OutputFormat(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
}

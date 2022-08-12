package pe.indigital.customers.enums;

public enum SeverityEnum {

	SUCCESS("success"),
	WARNING("warning"),
	ERROR("error");
	
	private String value;

	private SeverityEnum(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}

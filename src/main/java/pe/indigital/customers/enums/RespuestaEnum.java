package pe.indigital.customers.enums;

public enum RespuestaEnum {

	OK("200"),
	BAD_REQUEST("400"),
	UNAUTHORIZED("401"),
	NOT_FOUND("404"),
	INTERNAL_SERVER_ERROR("500");
	
	private String value;

	private RespuestaEnum(String value) {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}

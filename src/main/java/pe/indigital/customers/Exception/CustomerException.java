package pe.indigital.customers.Exception;

public class CustomerException extends RuntimeException {
	private String message;

	public CustomerException() {
	}

	public CustomerException(String msg) {
		super(msg);
		this.message = msg;
	}
}

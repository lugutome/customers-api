package pe.indigital.customers.request;

import java.io.Serializable;

public class GenericRequest<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user;
	private T object;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void set(T object) {
		this.object = object;
	}

	public T get() {
		return object;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
}
package pe.indigital.customers.response;

import java.io.Serializable;
import java.util.List;

import pe.indigital.customers.enums.RespuestaEnum;
import pe.indigital.customers.enums.SeverityEnum;

public class GenericResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String summary;
	private String severity;
	private T object;	
	private List<T> objects;

	public GenericResponse() {
		super();
	}

	public GenericResponse(String summary) {
		super();
		this.status = RespuestaEnum.OK.getValue();
		this.severity = SeverityEnum.SUCCESS.getValue();
		this.summary = summary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.severity = SeverityEnum.SUCCESS.getValue();
		
		if (status.equals(RespuestaEnum.INTERNAL_SERVER_ERROR.getValue())) {
			this.severity = SeverityEnum.ERROR.getValue();
		}
		if (status.equals(RespuestaEnum.NOT_FOUND.getValue())) {
			this.severity = SeverityEnum.WARNING.getValue();
		}

		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
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
	
	public List<T> getObjects() {
		return objects;
	}

	public void setObjects(List<T> objects) {
		this.objects = objects;
	}
}
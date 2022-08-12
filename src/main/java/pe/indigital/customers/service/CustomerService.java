package pe.indigital.customers.service;

import java.util.List;

import pe.indigital.customers.model.CustomerEntity;
import pe.indigital.customers.request.CustomerRequest;
import pe.indigital.customers.to.CustomerIndicatorTO;

public interface CustomerService {
	public CustomerEntity findByCode(String code) throws Exception;
	
	public List<CustomerEntity> consult(CustomerRequest request) throws Exception;
	
	public CustomerEntity create(CustomerRequest request) throws Exception;
	
	public CustomerIndicatorTO indicators() throws Exception;
}

package pe.indigital.customers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.indigital.customers.enums.SeverityEnum;
import pe.indigital.customers.model.CustomerEntity;
import pe.indigital.customers.request.CustomerRequest;
import pe.indigital.customers.response.GenericResponse;
import pe.indigital.customers.service.CustomerService;
import pe.indigital.customers.to.CustomerIndicatorTO;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/search/{code}")
	public ResponseEntity<?> search(@PathVariable String code) {
		GenericResponse<CustomerEntity> response = new GenericResponse<CustomerEntity>("SearchCustomer");
		try {
			CustomerEntity customer = customerService.findByCode(code);
			response.set(customer);
		} catch (Exception e) {
			response.setSummary(e.getMessage());
			response.setSeverity(SeverityEnum.ERROR.getValue());
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/consult")
	public ResponseEntity<?> consult(@RequestBody CustomerRequest request) {
		GenericResponse<CustomerEntity> response = new GenericResponse<CustomerEntity>("ConsultCustomer");
		try {
			List<CustomerEntity> customers = customerService.consult(request);
			response.setObjects(customers);
		} catch (Exception e) {
			response.setSummary(e.getMessage());
			response.setSeverity(SeverityEnum.ERROR.getValue());
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody CustomerRequest request) {
		GenericResponse<CustomerEntity> response = new GenericResponse<CustomerEntity>("CreateCustomer");
		try {
			CustomerEntity customer = customerService.create(request);
			response.set(customer);
		} catch (Exception e) {
			response.setSummary(e.getMessage());
			response.setSeverity(SeverityEnum.ERROR.getValue());
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/indicators")
	public ResponseEntity<?> indicators() {
		GenericResponse<CustomerIndicatorTO> response = new GenericResponse<CustomerIndicatorTO>("IndicatorsCustomer");
		try {
			CustomerIndicatorTO indicator = customerService.indicators();
			response.setObject(indicator);
		} catch (Exception e) {
			response.setSummary(e.getMessage());
			response.setSeverity(SeverityEnum.ERROR.getValue());
		}
		return ResponseEntity.ok(response);
	}
}

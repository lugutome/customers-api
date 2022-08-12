package pe.indigital.customers.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.indigital.customers.Exception.CustomerException;
import pe.indigital.customers.model.CustomerEntity;
import pe.indigital.customers.repository.CustomerRepository;
import pe.indigital.customers.request.CustomerRequest;
import pe.indigital.customers.to.CustomerIndicatorDetailTO;
import pe.indigital.customers.to.CustomerIndicatorTO;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerEntity findByCode(String code) throws Exception {
		Optional<CustomerEntity> customerOptional = customerRepository.findById(code);
		return customerOptional.orElseThrow(() -> new CustomerException("El DNI " + code + " no se encuentra registrado!"));
	}

	@SuppressWarnings("serial")
	@Override
	public List<CustomerEntity> consult(CustomerRequest request) throws Exception {
		return customerRepository.findAll(new Specification<CustomerEntity>() {
			@Override
			public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (request.getDni() != null && !request.getDni().isEmpty()) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("dni"), request.getDni())));
				}
				if (request.getEmail() != null && !request.getEmail().isEmpty()) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), request.getEmail())));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	@Override
	public CustomerEntity create(CustomerRequest request) throws Exception {
		Optional<CustomerEntity> customerOptional = customerRepository.findById(request.getDni());
		CustomerEntity customerFinder = customerOptional.orElse(null);
		if(customerFinder == null) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			final CustomerEntity customer = objectMapper.convertValue(request, CustomerEntity.class);
			customer.setCreateDate(new Date());
			return customerRepository.save(customer);
		}
		else {
			throw new CustomerException("El DNI "+request.getDni()+" ya se encuentra registrado!");
		}
	}

	@Override
	public CustomerIndicatorTO indicators() throws Exception {
		CustomerIndicatorTO customerIndicator = new CustomerIndicatorTO();
		List<CustomerIndicatorDetailTO> lsCustomerGivenBirthByYearMonth = customerRepository.selectCustomerGivenBirthByYearMonth();
		customerIndicator.setCustomerGivenBirthByYearMonth(lsCustomerGivenBirthByYearMonth);
		List<CustomerIndicatorDetailTO> lsCustomerGivenBirthMajorInYearMonth = customerRepository.selectCustomerGivenBirthMajorInYearMonth();
		customerIndicator.setCustomerGivenBirthMajorInYearMonth(lsCustomerGivenBirthMajorInYearMonth);
		List<CustomerIndicatorDetailTO> lsCustomerGivenBirthMinorInYearMonth = customerRepository.selectCustomerGivenBirthMinorInYearMonth();
		customerIndicator.setCustomerGivenBirthMinorInYearMonth(lsCustomerGivenBirthMinorInYearMonth);
		List<CustomerIndicatorDetailTO> lsCustomerGivenBirthRateByYearMonth = customerRepository.selectCustomerGivenBirthRateByYearMonth();		
		customerIndicator.setCustomerGivenBirthRateByYearMonth(lsCustomerGivenBirthRateByYearMonth);
		return customerIndicator;
	}

}

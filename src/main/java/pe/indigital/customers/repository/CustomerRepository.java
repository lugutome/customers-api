package pe.indigital.customers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import pe.indigital.customers.model.CustomerEntity;
import pe.indigital.customers.to.CustomerIndicatorDetailTO;

public interface CustomerRepository extends JpaRepository<CustomerEntity, java.lang.String>, JpaSpecificationExecutor<CustomerEntity> {
	
	@Query(name = "Customer_Given_Birth_By_Year_Month", nativeQuery = true)
	List<CustomerIndicatorDetailTO> selectCustomerGivenBirthByYearMonth();
	
	@Query(name = "Customer_Given_Birth_Major_In_Year_Month", nativeQuery = true)
	List<CustomerIndicatorDetailTO> selectCustomerGivenBirthMajorInYearMonth();
	
	@Query(name = "Customer_Given_Birth_Minor_In_Year_Month", nativeQuery = true)
	List<CustomerIndicatorDetailTO> selectCustomerGivenBirthMinorInYearMonth();
	
	@Query(name = "Customer_Given_Birth_Rate_By_Year_Month", nativeQuery = true)
	List<CustomerIndicatorDetailTO> selectCustomerGivenBirthRateByYearMonth();
}

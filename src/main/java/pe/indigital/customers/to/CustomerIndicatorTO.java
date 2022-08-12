package pe.indigital.customers.to;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerIndicatorTO {
	private List<CustomerIndicatorDetailTO> customerGivenBirthByYearMonth;
	private List<CustomerIndicatorDetailTO> customerGivenBirthMajorInYearMonth;
	private List<CustomerIndicatorDetailTO> customerGivenBirthMinorInYearMonth;
	private List<CustomerIndicatorDetailTO> customerGivenBirthRateByYearMonth;
}

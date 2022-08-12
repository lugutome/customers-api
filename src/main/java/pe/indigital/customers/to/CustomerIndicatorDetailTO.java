package pe.indigital.customers.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerIndicatorDetailTO {
	private String indYear;
	private String indMonth;
	private double indCant;
}

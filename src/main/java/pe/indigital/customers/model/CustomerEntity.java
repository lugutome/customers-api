package pe.indigital.customers.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.indigital.customers.to.CustomerIndicatorDetailTO;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "CUSTOMER")
@NamedNativeQuery(
	name = "Customer_Given_Birth_By_Year_Month",
	query = "SELECT "
				+ "TO_CHAR(BIRTH_DATE, 'YYYY') AS indYear, "
				+ "TO_CHAR(BIRTH_DATE, 'MM') AS indMonth, "
				+ "COUNT(*) AS indCant "
			+ "FROM CUSTOMER "
			+ "GROUP BY "
				+ "TO_CHAR(BIRTH_DATE, 'YYYY'), "
				+ "TO_CHAR(BIRTH_DATE, 'MM') "
			+ "ORDER BY 1,2",
	resultSetMapping = "Customer_Indicator_Detail_TO"
)
@NamedNativeQuery(
	name = "Customer_Given_Birth_Major_In_Year_Month",
	query = "SELECT * FROM( "
				+ "SELECT "
					+ "TO_CHAR(BIRTH_DATE, 'YYYY') AS indYear, "
					+ "TO_CHAR(BIRTH_DATE, 'MM') AS indMonth, "
					+ "COUNT(*) AS indCant "
				+ "FROM CUSTOMER "
				+ "GROUP BY "
					+ "TO_CHAR(BIRTH_DATE, 'YYYY'), "
					+ "TO_CHAR(BIRTH_DATE, 'MM') "
				+ "ORDER BY 1,2 "
			+ ") "
			+ "ORDER BY indCant DESC LIMIT 1",
	resultSetMapping = "Customer_Indicator_Detail_TO"
)
@NamedNativeQuery(
	name = "Customer_Given_Birth_Minor_In_Year_Month",
	query = "SELECT * FROM( "
				+ "SELECT "
					+ "TO_CHAR(BIRTH_DATE, 'YYYY') AS indYear, "
					+ "TO_CHAR(BIRTH_DATE, 'MM') AS indMonth, "
					+ "COUNT(*) AS indCant "
				+ "FROM CUSTOMER "
				+ "GROUP BY "
					+ "TO_CHAR(BIRTH_DATE, 'YYYY'), "
					+ "TO_CHAR(BIRTH_DATE, 'MM') "
				+ "ORDER BY 1,2 "
			+ ") "
			+ "ORDER BY indCant ASC LIMIT 1",
	resultSetMapping = "Customer_Indicator_Detail_TO"
)
@NamedNativeQuery(
	name = "Customer_Given_Birth_Rate_By_Year_Month",
	query = "SELECT indYear, indMonth, ROUND(SUM(indCant)*1000/(SELECT COUNT(*) FROM CUSTOMER), 2) AS indCant FROM( "
				+ "SELECT "
					+ "TO_CHAR(BIRTH_DATE, 'YYYY') AS indYear, "
					+ "TO_CHAR(BIRTH_DATE, 'MM') AS indMonth, "
					+ "COUNT(*) AS indCant "
				+ "FROM CUSTOMER "
				+ "GROUP BY "
					+ "TO_CHAR(BIRTH_DATE, 'YYYY'), "
					+ "TO_CHAR(BIRTH_DATE, 'MM') "
				+ "ORDER BY 1,2 "
			+ ") "
			+ "GROUP BY indYear, indMonth",
	resultSetMapping = "Customer_Indicator_Detail_TO"
)
@SqlResultSetMapping(
    name = "Customer_Indicator_Detail_TO",
    classes = @ConstructorResult(
        targetClass = CustomerIndicatorDetailTO.class,
        columns = {
            @ColumnResult(name = "indYear", type = String.class),
            @ColumnResult(name = "indMonth", type = String.class),
            @ColumnResult(name = "indCant", type = double.class)
        }
    )
)
public class CustomerEntity implements Serializable {
	@Id
	@Column(name = "DNI", nullable = false)
	private String dni;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "BIRTH_DATE", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date birthDate;
	
	@Column(name = "CREATE_DATE", nullable = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date createDate;
}

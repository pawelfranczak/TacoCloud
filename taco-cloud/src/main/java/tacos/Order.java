package tacos;

import java.sql.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {
	
	private Long id;
	private Date placedAt;

	@NotBlank(message = "Należy podać imię i nazwisko")
	private String name;

	@NotBlank(message = "Należy podać ulicę")
	private String street;

	@NotBlank(message = "Należy podać miejscowość")
	private String city;

	@NotBlank(message = "Należy podać województwo")
	private String state;

	@NotBlank(message = "Należy podać kod pocztowy")
	private String zip;

	@CreditCardNumber(message = "Niepoprawny numer karty")
	private String ccNumber;

	@Pattern(regexp = "^([0-1][0-9])([\\/])([1-9][0-9])$", message = "Niepoprawny format. Poprawny format to MM/RR")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Niepoprawny kod CVV")
	private String ccCCV;

}

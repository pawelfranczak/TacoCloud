package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
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
	
	@ManyToMany(targetEntity = Taco.class)
	private List<Taco> tacos = new ArrayList<Taco>();
	
	public void addDeisgn(Taco design) {
		this.tacos.add(design);
	}

	
	@PrePersist
	void createdAt() {
		this.placedAt = new Date();
	}
	
}

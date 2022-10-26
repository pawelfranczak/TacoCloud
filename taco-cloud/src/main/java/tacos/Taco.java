package tacos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	
	private Date createAt;
	 
	@NotNull
	@Size(min = 5, message = "Nazwa musi składać się przynajmniej z 5 znaków")
	private String name;
	
	@ManyToMany(targetEntity = Ingredient.class)
	@NotNull(message = "Musisz wybrać przynajmniej jeden składnik")
	@Size(min = 1, message = "Musisz wybrać przynajmniej jeden składnik")
	private List<String> ingredients;
	
	@PrePersist
	void createdAt() {
		this.createAt = new Date();
	}

}

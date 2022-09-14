package tacos;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
	 
	@NotNull
	@Size(min = 5, message = "Nazwa musi składać się przynajmniej z 5 znaków")
	private String name;
	
	@NotNull(message = "Musisz wybrać przynajmniej jeden składnik")
	@Size(min = 1, message = "Musisz wybrać przynajmniej jeden składnik")
	private List<String> ingredients;

}

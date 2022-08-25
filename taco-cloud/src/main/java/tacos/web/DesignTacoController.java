package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	public String showDesignForm(Model model) {
		
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO", "pszenna", Type.WRAP),
				new Ingredient("GRBF", "mielona wołowina", Type.PROTEIN),
				new Ingredient("CARN", "kawałki mięsa", Type.PROTEIN),
				new Ingredient("TMTO", "pomidory pokrojone w kostkę", Type.VEGGIES),
				new Ingredient("LECT", "sałata", Type.VEGGIES),
				new Ingredient("CHED", "chedar", Type.CHEESE),
				new Ingredient("JACK", "Montery Jack", Type.CHEESE),
				new Ingredient("SLSA", "pikantny sos pomidorowy", Type.SAUCE),
				new Ingredient("SRCR", "smietana", Type.SAUCE)
		);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		
		return("design");
		
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
	    return ingredients.stream()
	            .filter(x -> x.getType().equals(type))
	            .collect(Collectors.toList());
	}
}

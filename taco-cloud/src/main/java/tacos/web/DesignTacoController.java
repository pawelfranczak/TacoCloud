package tacos.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = Arrays.asList(
		  new Ingredient("FLTO", "pszenna", Type.WRAP),
		  new Ingredient("COTO", "kukurydziana", Type.WRAP),
		  new Ingredient("GRBF", "mielona wołowina", Type.PROTEIN),
		  new Ingredient("CARN", "kawałki mięsa", Type.PROTEIN),
		  new Ingredient("TMTO", "pomidory pokrojone w kostkę", Type.VEGGIES),
		  new Ingredient("LETC", "sałata", Type.VEGGIES),
		  new Ingredient("CHED", "cheddar", Type.CHEESE),
		  new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
		  new Ingredient("SLSA", "pikantny sos pomidorowy", Type.SAUCE),
		  new Ingredient("SRCR", "śmietana", Type.SAUCE)
		);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
		  model.addAttribute(type.toString().toLowerCase(),
		      filterByType(ingredients, type));
		}
	}

	@GetMapping
	public String showDesignForm(Model model) {
		Taco design = new Taco();
		design.setIngredients(new ArrayList<String>());
		model.addAttribute("design", design);
		return "design";
		
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors) {
		if (errors.hasErrors()) {
			return "design";
		}
		
		log.info("Przetwarzanie obiektu taco " + design);
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
	    return ingredients.stream()
	            .filter(x -> x.getType().equals(type))
	            .collect(Collectors.toList());
	}
}

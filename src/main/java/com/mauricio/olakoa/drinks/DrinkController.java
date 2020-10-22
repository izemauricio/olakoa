package com.mauricio.olakoa.drinks;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mauricio.olakoa.users.User;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = "user", types = User.class)
public class DrinkController {

	@Autowired
	private DrinkService drinkService;

	@ModelAttribute("drinks")
	private List<Drink> fillDrinks(User user,
			@RequestParam(required = false, defaultValue = "name") String sort,
			@RequestParam(required = false, defaultValue = "asc") String order) {
		List<Drink> result = null;

		if (user.getRole() == 1)
			result = drinkService.findAllDrinksByUser(user);

		if (user.getRole() == 2)
			result = drinkService.findAllDrinksByEnabledUser();

		if (user.getRole() == 3)
			result = drinkService.findAllDrinks();

		if (sort.equals("name")) {
			Collections.sort(result, new Comparator<Drink>() {
				public int compare(Drink t1, Drink t2) {
					return t1.getName().compareToIgnoreCase(t2.getName());
				}
			});
		}

		if (sort.equals("cost")) {
			Collections.sort(result, new Comparator<Drink>() {
				public int compare(Drink t1, Drink t2) {
					return t1.getCostString().compareToIgnoreCase(
							t2.getCostString());
				}
			});
		}

		if (sort.equals("posted")) {
			Collections.sort(result, new Comparator<Drink>() {
				public int compare(Drink t1, Drink t2) {
					return t1.isPostedString().compareToIgnoreCase(
							t2.isPostedString());
				}
			});
		}

		if (order.equals("desc")) {
			Collections.reverse(result);
		}

		return result;
	}

	@RequestMapping(value = "drinks", method = RequestMethod.GET)
	public String drinks(@ModelAttribute User user) {

		if (user.getId() == null)
			return "redirect:/login";
		else {
			if (user.getRole() == 1) {
				return "drinksUser";
			}
			if (user.getRole() == 2) {
				return "drinksShopper";
			}
			if (user.getRole() == 3) {
				return "drinksAdmin";
			}
		}
		return "drinks";
	}

	@RequestMapping(value = "drinks/create", method = RequestMethod.GET)
	public String drinkCreator(@ModelAttribute User user) {
		return "drinkCreator";
	}

	@RequestMapping(value = "drinks/update/{idd}", method = RequestMethod.GET)
	public String drinkUpdate(@ModelAttribute User user, Model model,
			@PathVariable String idd) {
		Drink drink = drinkService.findTaskById(user, idd);

		model.addAttribute("thedrink", drink);

		if (user.getRole() != 1)
			return "redirect:/user/drinks";

		return "drinkUpdate";
	}

	@RequestMapping(value = "drinks/create", method = RequestMethod.POST)
	public String createDrink(
			@ModelAttribute User user,
			@RequestParam(required = true) String name,
			@RequestParam(required = true) String description,
			@RequestParam(required = true) URL thumbnail,
			@RequestParam(required = true) int cost,
			@RequestParam(required = false, defaultValue = "false") boolean posted) {

		Drink drink = new Drink.Builder().description(description).name(name)
				.thumbnail(thumbnail).cost(cost).posted(posted)
				.ownerId(user.getId()).build();

		drinkService.addDrink(user, drink);

		return "redirect:/user/drinks";
	}

	@RequestMapping(value = "drinks/update", method = RequestMethod.POST)
	public String updateDrink(
			@ModelAttribute User user,
			@RequestParam(required = true) String idd,
			@RequestParam(required = true) String name,
			@RequestParam(required = true) String description,
			@RequestParam(required = true) URL thumbnail,
			@RequestParam(required = true) int cost,
			@RequestParam(required = false, defaultValue = "false") boolean posted) {

		Drink drink = new Drink.Builder().description(description).name(name)
				.thumbnail(thumbnail).cost(cost).posted(posted).id(idd)
				.ownerId(user.getId()).build();

		drinkService.updateDrink(user, drink);

		return "redirect:/user/drinks";
	}
}

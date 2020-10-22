package com.mauricio.olakoa.drinks;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauricio.olakoa.users.User;
import com.mauricio.olakoa.users.UserService;

@Service
public class DrinkService {

	@Autowired
	private DrinkDao drink_store;

	public void load() {
		drink_store.load();
	}

	private boolean checkOwner(User user, Drink drink) {
		return user != null && drink != null && user.getId() != null
				&& user.getId().equals(drink.getOwnerId());
	}

	// User
	public List<Drink> findAllDrinksByUser(User user) {
		return drink_store.findAllDrinksByUser(user.getId());
	}

	// Shopper
	public List<Drink> findAllDrinksByEnabledUser() {
		return drink_store.findAllDrinksByEnabledUser();
	}

	// Admin
	public List<Drink> findAllDrinks() {
		return drink_store.findAllDrinks();
	}

	public void addDrink(User owner, Drink drink) {
		if (checkOwner(owner, drink)) {
			drink.setId(UUID.randomUUID().toString());
			drink_store.add(drink);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void updateDrink(User owner, Drink drink) {
		if (checkOwner(owner, drink)) {
			drink_store.remove(drink.getId());
			drink_store.add(drink);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public Drink findTaskById(User owner, String taskId) {
		Drink t = drink_store.findById(taskId);
		if (t.getOwnerId() != null && t.getOwnerId().equals(owner.getId())) {
			return t;
		} else {
			return null;
		}
	}

}

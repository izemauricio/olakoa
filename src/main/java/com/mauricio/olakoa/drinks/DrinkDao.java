package com.mauricio.olakoa.drinks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mauricio.olakoa.users.User;
import com.mauricio.olakoa.users.UserService;

@Repository
public class DrinkDao {

	@Autowired
	private UserService userService;

	private String path = System.getProperty("java.io.tmpdir") + "/drinks.db";

	Map<String, Drink> drinks = new HashMap<String, Drink>();

	public Collection<Drink> findAll() {
		return drinks.values();
	}

	public Drink findById(String id) {
		return drinks.get(id);
	}

	// User
	public List<Drink> findAllDrinksByUser(String ownerId) {
		List<Drink> result = new ArrayList<Drink>();
		if (ownerId == null)
			return result;

		for (Drink t : drinks.values()) {
			if (ownerId.equals(t.getOwnerId()))
				result.add(t);
		}
		return result;
	}

	// Shopper
	public List<Drink> findAllDrinksByEnabledUser() {
		List<Drink> result = new ArrayList<Drink>();

		for (Drink t : drinks.values()) {
			if (userService.getUserById(t.getOwnerId()).getEnabled() == 1
					&& t.isPosted())
				result.add(t);
		}

		return result;
	}

	// Admin
	public List<Drink> findAllDrinks() {
		Collection<Drink> coll = drinks.values();
		List<Drink> list;

		if (coll instanceof List)
			list = (List<Drink>) coll;
		else
			list = new ArrayList<Drink>(coll);

		return list;
	}

	private void rewrite() {
		BufferedWriter w = null;

		try {
			w = new BufferedWriter(new FileWriter(path, false));

			for (Drink drink : drinks.values()) {
				String id = drink.getId();
				String name = drink.getName();
				URL thumbnail = drink.getThumbnail();
				String description = drink.getDescription();
				int cost = drink.getCost();
				String ownerId = drink.getOwnerId();
				String posted = drink.isPosted() ? "1" : "0";

				w.write(id + "," + name + "," + thumbnail + "," + description
						+ "," + cost + "," + ownerId + "," + posted + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				w.close();
			} catch (Exception e) {
			}
		}
	}

	public void load() {
		Scanner scanner = null;
		Scanner split = null;

		drinks.clear();

		try {
			scanner = new Scanner(new File(path));

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				split = new Scanner(line);
				split.useDelimiter(",");
				while (split.hasNext()) {
					String id = split.next();
					String name = split.next();
					String thumbnail = split.next();
					String description = split.next();
					String cost = split.next();
					String ownerId = split.next();
					String posted = split.next();

					Drink drink = null;
					
					try {
						drink = new Drink.Builder().id(id).ownerId(ownerId)
								.name(name).thumbnail(new URL(thumbnail))
								.description(description)
								.cost(new Integer(cost))
								.posted(posted.equals("1") ? true : false)
								.build();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}

					drinks.put(drink.getId(), drink);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				split.close();
				scanner.close();
			} catch (Exception e) {
			}
		}

	}

	public void add(Drink drink) {
		drinks.put(drink.getId(), drink);

		rewrite();
	}

	public void remove(String id) {
		drinks.remove(id);

		rewrite();
	}
}

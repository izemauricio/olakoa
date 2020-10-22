package com.mauricio.olakoa.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauricio.olakoa.drinks.DrinkDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	@Autowired
	private UserDao user_store;
	
	public void load() {
		user_store.load();
	}

	public User getUserById(String id) {
		for (User user : user_store.getList()) {
			if (user.getId().equals(id))	
				return user;
		}

		return null;
	}
	
	public User getUser(String username, String password) {
		if (username == null || password == null)
			return null;

		for (User user : user_store.getList()) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				logger.info("USUARIO LOGOU: "+username+" "+password+ " com db " +user.getUsername()+" "+user.getPassword());
				return user;
			} else {
				logger.info("USUARIO NAAAO LOGOU: "+username+" "+password+ " com db " +user.getUsername()+" "+user.getPassword());
			}
		}

		return null;
	}

}

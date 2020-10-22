package com.mauricio.olakoa.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mauricio.olakoa.drinks.DrinkService;
import com.mauricio.olakoa.users.User;
import com.mauricio.olakoa.users.UserDao;
import com.mauricio.olakoa.users.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(UserDao.class);

	@Autowired
	private UserService userService;

	@Autowired
	private DrinkService drinkService;

	@PostConstruct
	public void init() {
		userService.load();
		drinkService.load();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpSession session, HttpServletResponse response) {

		User user = userService.getUser(username, password);

		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/user/drinks";
		} else {
			return "redirect:/login?msg=1";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		return "redirect:/login?msg=2";
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login() {
		return "login";
	}

}

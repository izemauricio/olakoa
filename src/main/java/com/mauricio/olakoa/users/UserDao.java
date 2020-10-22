package com.mauricio.olakoa.users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.mauricio.olakoa.drinks.Drink;

@Repository
public class UserDao {
	
	Logger logger = LoggerFactory.getLogger(UserDao.class);

	private String path = System.getProperty("java.io.tmpdir") + "/users.db";

	private List<User> users = new ArrayList<>();

	public void add(User user) {
		users.add(user);
	}

	public List<User> getList() {
		logger.info("getList() FOI CHAMADO");
		return users;
	}

	private void generateTxtFile() {
		logger.info("generateTxtFile FOI CHAMADO");
		
		BufferedWriter w = null;

		try {
			w = new BufferedWriter(new FileWriter(path));

			w.write(UUID.randomUUID().toString() + ","
					+ "mauricio,ize,mau,123,1,1\n");
			w.write(UUID.randomUUID().toString() + ","
					+ "mickey,mouse,mickey40,123,1,1\n");
		} catch (IOException e) {
			System.err.println("Nao foi possivel escrever no arquivo: " + e.getMessage());
			logger.info("Nao foi possivel escrever arquivo: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				w.close();
			} catch (Exception e) {
				System.err.println("Nao foi possivel fechar arquivo: " + e.getMessage());
				logger.info("Nao foi possivel fechar arquivo: " + e.getMessage());
			}
		}
	}

	public void load() {
		logger.info("load() FOI CHAMADO");
		
		Scanner scanner = null;
		Scanner split = null;
		
		logger.info("load() FOI CHAMADO 1");

		try {
			scanner = new Scanner(new File(path));
			
			logger.info("load() FOI CHAMADO 2");

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				logger.info("load() FOI CHAMADO 3");

				split = new Scanner(line);
				split.useDelimiter(",");
				while (split.hasNext()) {
					try {
						logger.info("load() FOI CHAMADO 4");
						String id = split.next();
						logger.info(id);
						logger.info("load() FOI CHAMADO 4.1");
						String email = split.next();
						logger.info(email);
						logger.info("load() FOI CHAMADO 4.2");
						String first = split.next();
						logger.info(first);
						logger.info("load() FOI CHAMADO 4.3");
						String last = split.next();
						logger.info(last);
						logger.info("load() FOI CHAMADO 4.4");
						String username = split.next();
						logger.info(username);
						logger.info("load() FOI CHAMADO 4.5");
						String password = split.next();logger.info(password);
						logger.info("load() FOI CHAMADO 4.6");
						String role = split.next();logger.info(role);
						logger.info("load() FOI CHAMADO 4.7");
						String enabled = split.next();logger.info(enabled);
						logger.info("load() FOI CHAMADO 4.8");
						
						
						logger.info("USUARIO LIDO: "+email+" "+username+" "+password+" ");

						User user = new User.Builder().id(id).email(email)
								.first(first).last(last).username(username)
								.password(password).role(Integer.parseInt(role))
								.enabled(Integer.parseInt(enabled)).build();
						
						logger.info("load() FOI CHAMADO 5");

						add(user);
					} catch(Exception e) {
						logger.info("ERRO AO LER USUARIO!!! "+e.getMessage());
					}
				}
			}
		} catch (FileNotFoundException e) {
			logger.info("Nao foi possivel ler arquivo: " + e.getMessage());
			System.err.println("Nao foi possivel ler arquivo: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				split.close();
				scanner.close();
			} catch (Exception e) {
				logger.info("Nao foi possivel fechar arquivo: " + e.getMessage());
			}
		}

	}
}

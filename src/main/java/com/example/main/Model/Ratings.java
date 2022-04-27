package com.example.main.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ratings {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String user;
	private int ambiance;
	private int food;
	private int service;
	private int drinks;
	private int cleanliness;
	
	public Ratings() {
		super();
	}

	public Ratings(int id, String user, int ambiance, int food, int service, int drinks, int cleanliness) {
		super();
		this.id = id;
		this.user = user;
		this.ambiance = ambiance;
		this.food = food;
		this.service = service;
		this.drinks = drinks;
		this.cleanliness = cleanliness;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getAmbiance() {
		return ambiance;
	}

	public void setAmbiance(int ambiance) {
		this.ambiance = ambiance;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getDrinks() {
		return drinks;
	}

	public void setDrinks(int drinks) {
		this.drinks = drinks;
	}

	public int getCleanliness() {
		return cleanliness;
	}

	public void setCleanliness(int cleanliness) {
		this.cleanliness = cleanliness;
	}
	
}

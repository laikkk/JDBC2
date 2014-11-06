package com.example.jdbcdemo.domain;

public class Dog {

	private long id;

	private String name;
	private int yob;
	private String breed;
	private long id_shelter;
	
	public Dog(String name, int yob, String rasa, long id_shelter) {
		super();
		this.name = name;
		this.yob = yob;
		this.breed = rasa;
		this.id_shelter = id_shelter;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Dog() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}

	public long getId_shelter() {
		return id_shelter;
	}

	public void setId_shelter(long id_shelter) {
		this.id_shelter = id_shelter;
	}

}

package com.example.jdbcdemo.domain;

public class Shelter {
	
	private long id;
	
	private String name;
	private int nrTel;
	private String advertisingLabel;

	public Shelter() {
	}
	
	public Shelter(String name, int nrTel, String advertisingLabel) {
		this.name = name;
		this.nrTel = nrTel;
		this.advertisingLabel = advertisingLabel;
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

	public void setName(String nazwa) {
		this.name = nazwa;
	}

	public int getNrTel() {
		return nrTel;
	}

	public void setNrTel(int nrTel) {
		this.nrTel = nrTel;
	}

	public String getAdvertisingLabel() {
		return advertisingLabel;
	}

	public void setAdvertisingLabel(String advertisingLabel) {
		this.advertisingLabel = advertisingLabel;
	}
}

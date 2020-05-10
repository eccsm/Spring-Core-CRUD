package com.eccsm.model;

public class City {

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", country=" + country + ", isCapital=" + isCapital + "]";
	}

	private int id;
	private String name;
	private String country;
	private boolean isCapital;

	public City() {
		super();
	}

	public City(int id, String name, String country, boolean isCapital) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.isCapital = isCapital;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isCapital() {
		return isCapital;
	}

	public void setCapital(boolean isCapital) {
		this.isCapital = isCapital;
	}

}

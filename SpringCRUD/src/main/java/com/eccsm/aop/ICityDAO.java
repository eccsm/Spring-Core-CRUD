package com.eccsm.aop;

import java.util.List;

import com.eccsm.model.City;

public interface ICityDAO {

	public void createCity(City c);
	public City getCity(int id);
	public void updateCity(City c,int id);
	public void deleteCity(int id);
	public List<City> getCities();
	
}

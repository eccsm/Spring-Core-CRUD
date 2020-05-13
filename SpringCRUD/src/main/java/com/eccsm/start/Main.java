package com.eccsm.start;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eccsm.helpers.Menu;
import com.eccsm.model.City;
import com.eccsm.services.CityDaoImpl;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Metadata.xml");

		CityDaoImpl cityDao = context.getBean("cityDaoBean", CityDaoImpl.class);

		City c = context.getBean("cityBean", City.class);
		
		Menu m = new Menu();
		
		m.getMenu(c, cityDao);

		
		
	
	}
}
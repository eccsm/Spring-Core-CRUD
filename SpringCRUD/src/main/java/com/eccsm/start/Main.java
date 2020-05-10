package com.eccsm.start;

import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eccsm.model.City;
import com.eccsm.services.CityDaoImpl;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int choice = 0;

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Metadata.xml");

		CityDaoImpl cityDao = context.getBean("cityDaoBean", CityDaoImpl.class);

		City c = context.getBean("cityBean", City.class);

		while (choice != 6) {

			System.out.println("Welcome to Database Manager\nPlease select your choise...");

			System.out.println("1. Get All Cities\n" + "2. Get City by ID\n" + "3. Create City\n" + "4. Delete City\n"
					+ "5. Update City\n" + "6.EXIT");

			choice = scan.nextInt();
			switch (choice) {

			case 1:
				System.out.println(cityDao.getCities());
				break;

			case 2:

				System.out.println("ID: ");
				int id = scan.nextInt();
				c = cityDao.getCity(id);
				System.out.println(c);
				break;

			case 3:
				Scanner sc = new Scanner(System.in);
				System.out.println("City Name:");
				c.setName(sc.nextLine());
				System.out.println("Country: ");
				c.setCountry(sc.nextLine());

				System.out.println("Is city Capital?(y/n): ");
				String ans = sc.nextLine();

				if (ans.toLowerCase().equals("y")) {
					c.setCapital(true);
					cityDao.createCity(c);
				} else if (ans.toLowerCase().equals("n")) {
					c.setCapital(false);
					cityDao.createCity(c);
				} else {
					System.out.println("Wrong Entry..");
					break;
				}

				break;

			case 4:

				System.out.println("ID: ");
				int cid = scan.nextInt();
				cityDao.deleteCity(cid);
				break;

			case 5:
				Scanner scn = new Scanner(System.in);
				System.out.println("ID: ");
				int idC = scn.nextInt();
				scn.nextLine();
				System.out.println("City Name:");
				c.setName(scn.nextLine());
				System.out.println("Country: ");
				c.setCountry(scn.nextLine());

				System.out.println("Is city Capital?(y/n): ");
				String ansC = scn.nextLine();

				if (ansC.toLowerCase().equals("y")) {
					c.setCapital(true);
					cityDao.updateCity(c, idC);
				} else if (ansC.toLowerCase().equals("n")) {
					c.setCapital(false);
					cityDao.updateCity(c, idC);
				} else {
					System.out.println("Wrong Entry..");
					break;
				}

				break;
			default:

				break;

			}

		}
	}
}
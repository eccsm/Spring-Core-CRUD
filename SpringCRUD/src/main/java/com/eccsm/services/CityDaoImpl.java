package com.eccsm.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.eccsm.aop.ICityDAO;
import com.eccsm.model.City;

public class CityDaoImpl implements ICityDAO {

	Connection con;
	String query;
	private DataSource ds;

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	public void createCity(City c) {
		query = "INSERT INTO springdb.cities (name, country, isCapital) VALUES ( ?, ?, ?)";
		JdbcTemplate jdbcT = new JdbcTemplate(ds);
		Object[] params = new Object[] { c.getName(), c.getCountry(), c.isCapital() };

		int state = jdbcT.update(query, params);

		if (state != 0) {
			System.out.println(c.getName() + " city successfully created...");
		} else {
			System.out.println("An error occured.");
		}

	}

	private static final class CityMapper implements RowMapper<City> {

		public City mapRow(ResultSet rs, int rowNum) throws SQLException {
			City city = new City();

			city.setId(rs.getInt("id"));
			city.setName(rs.getString("name"));
			city.setCountry(rs.getString("country"));
			city.setCapital(rs.getBoolean("isCapital"));

			return city;
		}
	}

	public City getCity(int id) {
		query = "SELECT * FROM springdb.cities WHERE id = ? ";
		JdbcTemplate jdbcT = new JdbcTemplate(ds);
		Object[] params = new Object[] { id };

		try {
			City c = jdbcT.queryForObject(query, params, new CityMapper());
			return c;
		} catch (Exception e) {
			System.err.println("Error occured : " + e);
			return null;
		}
	}

	public List<City> getCities() {
		query = "SELECT * FROM springdb.cities";
		JdbcTemplate jdbcT = new JdbcTemplate(ds);

		return jdbcT.query(query, new CityMapper());
	}

	public void updateCity(City c, int id) {

		query = "UPDATE springdb.cities SET NAME=?,COUNTRY=?,ISCAPITAL=? WHERE ID =?";
		JdbcTemplate jdbcT = new JdbcTemplate(ds);
		Object[] params = new Object[] { c.getName(), c.getCountry(), c.isCapital(), id };

		int state = jdbcT.update(query, params);

		if (state != 0) {
			System.out.println(c.getName() + " city successfully updated...");
		} else {
			System.out.println("An error occured.");
		}

	}

	public void deleteCity(int id) {
		query = "DELETE FROM springdb.cities WHERE ID = ?";
		JdbcTemplate jdbcT = new JdbcTemplate(ds);
		Object[] params = new Object[] { id };

		int state = jdbcT.update(query, params);

		if (state != 0) {
			System.out.println("City successfully deleted...");
		} else {
			System.out.println("An error occured.");
		}

	}

}

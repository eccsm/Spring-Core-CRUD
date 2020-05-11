package com.eccsm.aspect;

import javax.sql.DataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.jdbc.core.JdbcTemplate;

import com.eccsm.model.City;
import com.eccsm.services.CityDaoImpl;
import com.eccsm.services.CityDaoImpl.*;


@Aspect
public class UserOps {
	CityDaoImpl c;
	private DataSource dataSource;

	
	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Around("execution(* com.eccsm.services.CityDaoImpl.deleteCity(..)) && args(id)")
	public void deletedCities(ProceedingJoinPoint joinPoint, int id) throws Throwable
	{
		System.out.println("Öncesi "+id);
		String query = "SELECT * FROM springdb.cities WHERE id = ? ";
		JdbcTemplate jdbcT = new JdbcTemplate(dataSource);
		Object[] params = new Object[] { id };
		City city = jdbcT.queryForObject(query, params, new CityMapper());
			
		joinPoint.proceed();
		
		query = "INSERT INTO springdb.deletedcity (name, country, isCapital) VALUES ( ?, ?, ?)";
		
		Object[] param = new Object[] { city.getName(), city.getCountry(), city.isCapital() };

		jdbcT.update(query, param);
	}

}

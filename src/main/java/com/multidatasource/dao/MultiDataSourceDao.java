package com.multidatasource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.multidatasource.model.City;
import com.multidatasource.model.Employee;
import com.multidatasource.model.WorkBook;

@Repository
public class MultiDataSourceDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public static final String GET_WORKBOOK_BY_ID = "SELECT * FROM MDO_WORKBOOK WHERE workbookId = :workbookId";
	public static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = :employeeId";
	public static final String GET_CITY_BY_ID = "SELECT * FROM cities WHERE id = :cityId";

	public WorkBook getWorkBookById(String workbookId) {
		var namedParams = new MapSqlParameterSource("workbookId", workbookId);
		return namedParameterJdbcTemplate.queryForObject(GET_WORKBOOK_BY_ID, namedParams,
				BeanPropertyRowMapper.newInstance(WorkBook.class));
	}

	public Employee getEmployeeById(Integer employeeId) {
		var namedParams = new MapSqlParameterSource("employeeId", employeeId);
		return namedParameterJdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID, namedParams,
				BeanPropertyRowMapper.newInstance(Employee.class));
	}

	public City getCityById(Integer cityId) {
		var namedParams = new MapSqlParameterSource("cityId", cityId);
		return namedParameterJdbcTemplate.queryForObject(GET_CITY_BY_ID, namedParams,
				BeanPropertyRowMapper.newInstance(City.class));
	}

}

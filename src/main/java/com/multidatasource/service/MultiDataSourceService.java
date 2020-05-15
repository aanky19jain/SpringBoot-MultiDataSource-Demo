package com.multidatasource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multidatasource.dao.MultiDataSourceDao;
import com.multidatasource.model.City;
import com.multidatasource.model.Employee;
import com.multidatasource.model.WorkBook;

@Service
public class MultiDataSourceService {

	@Autowired
	private MultiDataSourceDao multiDataSourceDao;

	public WorkBook getWorkBookById(String workbookId) {
		return multiDataSourceDao.getWorkBookById(workbookId);
	}

	public Employee getEmployeeById(Integer employeeId) {
		return multiDataSourceDao.getEmployeeById(employeeId);
	}

	public City getCityById(Integer cityId) {
		return multiDataSourceDao.getCityById(cityId);
	}

}

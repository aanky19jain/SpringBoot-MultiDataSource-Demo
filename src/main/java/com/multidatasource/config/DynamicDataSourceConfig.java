package com.multidatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DynamicDataSourceConfig {

	private static final String DATA_SOURCE = "dataSource";

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.upm-dev")
	public DataSource upmDevDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.employees")
	public DataSource employeesDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.cities")
	public DataSource citiesDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Autowired
	@Bean(name = DATA_SOURCE)
	public DataSource dataSource() {
		DynamicRoutingDataSource dynamicDataSource = new DynamicRoutingDataSource();
		dynamicDataSource.setTargetDataSources(getDataSourceMap());
		return dynamicDataSource;
	}

	protected Map<Object, Object> getDataSourceMap() {
		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(DataSourceMapping.UPM_DEV_DATABASE, upmDevDataSource());
		dataSourceMap.put(DataSourceMapping.EMPLOYEE_DATABASE, employeesDataSource());
		dataSourceMap.put(DataSourceMapping.CITIES_DATABASE, citiesDataSource());
		log.info("====dataSourceMap size =====" + dataSourceMap.size());
		return dataSourceMap;
	}

	@Bean
	public NamedParameterJdbcTemplate template(@Named(DATA_SOURCE) final DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

}

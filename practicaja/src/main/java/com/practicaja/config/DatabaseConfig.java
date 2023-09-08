package com.practicaja.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	@Autowired
	private Environment env;

	@Primary
	@Bean(name = "mysqlDataSource")
	public DataSource mySQLDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));

		return dataSource;
	}

	@Bean(name = "jdbcMysql")
	@Autowired
	public JdbcTemplate mySQLJdbcTemplate(@Qualifier("mysqlDataSource") DataSource informixDataSource) {
		return new JdbcTemplate(mySQLDataSource());
	}

}


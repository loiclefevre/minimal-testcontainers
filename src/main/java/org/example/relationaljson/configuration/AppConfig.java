package org.example.relationaljson.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("org.example")
public class AppConfig {
	private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

	private JdbcTemplate jdbcTemplate;

	public AppConfig(JdbcTemplate jdbcTemplate) {
		LOG.debug("jdbcTemplate: "+System.getProperty("DB_URL"));

		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}

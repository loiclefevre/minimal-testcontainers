package org.example.relationaljson.model;

import org.example.relationaljson.configuration.AppConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.containers.OracleContainer;

import java.util.Objects;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@JdbcTest
@ContextConfiguration(classes = AppConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(value = {
		"classpath:application.properties",
		"classpath:application-test.properties"
})
public class OrderTest {
	private static final Logger LOG = LoggerFactory.getLogger(OrderTest.class);

	static OracleContainer container;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@BeforeAll
	public static void startDatabase() {
		container = new OracleContainer("gvenzl/oracle-xe:21-slim")
				.withReuse(true)
				.withEnv("ORACLE_PASSWORD", "test")
				.withEnv("APP_USER_PASSWORD", "test")
				.withEnv("APP_USER", "test");
		container.start();

		System.setProperty("DB_URL", container.getJdbcUrl());
	}

	@AfterAll
	public static void stopDatabase() {
		// csProvider.stop();
	}

	@Test
	public void testOrderCreation() {
		final String databaseVersion = Objects.requireNonNull(
				jdbcTemplate.queryForObject("""
								select version_full 
								  from product_component_version
								""",
						String.class));

		LOG.info("Running database version {}", databaseVersion);

		Assertions.assertEquals(databaseVersion, "21.3.0.0.0");
	}
}

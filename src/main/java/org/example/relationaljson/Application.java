package org.example.relationaljson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication(scanBasePackages = "org.example")
public class Application implements CommandLineRunner {
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	@Override
	public void run(String... args) throws Exception {
		LOG.info("=".repeat(126));
	}

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SpringApplication.run(Application.class, args);
		LOG.info("=".repeat(126));
	}
}

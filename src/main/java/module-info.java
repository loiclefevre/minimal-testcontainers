open module minimal.testcontainers {
	requires spring.jdbc;
	requires spring.beans;
	requires com.zaxxer.hikari;
	requires java.sql;
	requires spring.boot;
	requires org.slf4j;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires com.fasterxml.jackson.annotation;

	exports org.example.relationaljson.configuration;
	//opens org.example.relationaljson.configuration to spring.boot.autoconfigure, spring.context, spring.boot, spring.jdbc, spring.core, java.base;
}
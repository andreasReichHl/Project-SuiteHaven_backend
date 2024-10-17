package org.example.superbnb;

import org.example.superbnb.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SuiteHavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuiteHavenApplication.class, args);
	}

}

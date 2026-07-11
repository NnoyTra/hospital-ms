package com.nnoi.app.hospital_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HospitalMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalMsApplication.class, args);
	}

}

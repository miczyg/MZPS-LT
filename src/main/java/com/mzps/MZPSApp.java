package com.mzps;

import com.mzps.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;



@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.mzps"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class MZPSApp {

	public static void main(String[] args) {
		SpringApplication.run(MZPSApp.class, args);
	}
}

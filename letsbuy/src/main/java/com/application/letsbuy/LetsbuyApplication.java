package com.application.letsbuy;

import com.application.letsbuy.internal.utils.AscIILogo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LetsbuyApplication {
	public static void main(String[] args) {
		SpringApplication.run(LetsbuyApplication.class, args);
		System.out.println("\n \n" + AscIILogo.LOGO);
	}

}

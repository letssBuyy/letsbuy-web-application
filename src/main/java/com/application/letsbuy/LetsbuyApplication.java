package com.application.letsbuy;

import com.application.letsbuy.internal.utils.AscIILogo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class LetsbuyApplication {
	public static void main(String[] args) {
		SpringApplication.run(LetsbuyApplication.class, args);
		log.info("\n \n" + AscIILogo.LOGO);
	}
}

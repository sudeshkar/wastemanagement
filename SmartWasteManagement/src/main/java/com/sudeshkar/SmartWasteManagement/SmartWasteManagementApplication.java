
package com.sudeshkar.SmartWasteManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.github.cdimascio.dotenv.Dotenv;

@EnableScheduling
@SpringBootApplication 
public class SmartWasteManagementApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().directory("./").ignoreIfMalformed().ignoreIfMissing().load();
		if (dotenv.get("DB_URL_DEV")!=null) {
			System.setProperty("DB_URL_DEV", dotenv.get("DB_URL_DEV"));
		}
		if (dotenv.get("DB_USERNAME_DEV")!=null) {
			System.setProperty("DB_USERNAME_DEV", dotenv.get("DB_USERNAME_DEV"));
		}
		
		if (dotenv.get("DB_PASSWORD_DEV")!=null) {
			System.setProperty("DB_PASSWORD_DEV", dotenv.get("DB_PASSWORD_DEV"));
		}
		if (dotenv.get("Mail_userName")!=null) {
			System.setProperty("Mail_userName", dotenv.get("Mail_userName"));
		}
		
		if (dotenv.get("Mail_password")!=null) {
			System.setProperty("Mail_password", dotenv.get("Mail_password"));
		}
		
		if (dotenv.get("JWT_SECRET")!=null) {
			System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		}
		
		if (dotenv.get("JWT_EXP")!=null) {
			System.setProperty("JWT_EXP", dotenv.get("JWT_EXP"));
		}
		
		
		SpringApplication.run(SmartWasteManagementApplication.class, args);
	}

}

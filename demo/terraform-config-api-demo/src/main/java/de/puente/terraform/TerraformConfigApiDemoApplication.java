package de.puente.terraform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main-Klasse der Anwendung
 */
@SpringBootApplication
public class TerraformConfigApiDemoApplication {

	public static void main(String[] args) {
		// Übergibt an Spring zum Starten des Web-Servers
		SpringApplication.run(TerraformConfigApiDemoApplication.class, args);
	}

}

package no.ntnu.idatt2105.gr13.qs3backend;

import no.ntnu.idatt2105.gr13.qs3backend.model.mail.Mail;
import no.ntnu.idatt2105.gr13.qs3backend.service.mail.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Qs3BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Qs3BackendApplication.class, args);
	}

}

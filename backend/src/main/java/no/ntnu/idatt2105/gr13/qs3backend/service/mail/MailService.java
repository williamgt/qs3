package no.ntnu.idatt2105.gr13.qs3backend.service.mail;

import no.ntnu.idatt2105.gr13.qs3backend.model.mail.Mail;
import org.springframework.mail.javamail.JavaMailSender;

public interface MailService {
    public void sendEmail(Mail mail);
    JavaMailSender getJavaMailSender();
}

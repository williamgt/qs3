package no.ntnu.idatt2105.gr13.qs3backend.service.mail;

import no.ntnu.idatt2105.gr13.qs3backend.model.mail.Mail;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Interface for MailServiceImpl
 */
public interface MailService {
    void sendEmail(Mail mail);
    JavaMailSender getJavaMailSender();
}

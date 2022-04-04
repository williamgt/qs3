package no.ntnu.idatt2105.gr13.qs3backend.model.mail;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Models a mail
 */
public class Mail {
    private String mailFrom;
    private String mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    private String mailContent;
    private String contentType;
    private List< Object > attachments;

    /**
     * Instantiates a new Mail informing a user that they have been registered in QS3, letting them know their password.
     *
     * @param mailTo   the mail to
     * @param password the password
     */
    public Mail(String mailTo,String password) {
        contentType = "text/plain";
        mailFrom = "qs3Real@outlook.com";
        mailSubject = "QS3 - Registration!";
        mailContent = "Congratulations! You are now registered in QS3!\n" +
                "The mail you received this message from is your sign in mail\n" +
                "Your password is: " + password +
                "\nMake sure to change this after first log in";

        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(mailTo);
        if(!mat.matches() || !(mailTo.contains(".no") || mailTo.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }

        this.mailTo = mailTo;
    }

    /**
     * Gets content type.
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets content type.
     *
     * @param contentType the content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets mail bcc.
     *
     * @return the mail bcc
     */
    public String getMailBcc() {
        return mailBcc;
    }

    /**
     * Sets mail bcc.
     *
     * @param mailBcc the mail bcc
     */
    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    /**
     * Gets mail cc.
     *
     * @return the mail cc
     */
    public String getMailCc() {
        return mailCc;
    }

    /**
     * Sets mail cc.
     *
     * @param mailCc the mail cc
     */
    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    /**
     * Gets mail from.
     *
     * @return the mail from
     */
    public String getMailFrom() {
        return mailFrom;
    }

    /**
     * Sets mail from.
     *
     * @param mailFrom the mail from
     */
    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    /**
     * Gets mail subject.
     *
     * @return the mail subject
     */
    public String getMailSubject() {
        return mailSubject;
    }

    /**
     * Sets mail subject.
     *
     * @param mailSubject the mail subject
     */
    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    /**
     * Gets mail to.
     *
     * @return the mail to
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * Sets mail to.
     *
     * @param mailTo the mail to
     */
    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    /**
     * Gets mail send date.
     *
     * @return the mail send date
     */
    public Date getMailSendDate() {
        return new Date();
    }

    /**
     * Gets mail content.
     *
     * @return the mail content
     */
    public String getMailContent() {
        return mailContent;
    }

    /**
     * Sets mail content.
     *
     * @param mailContent the mail content
     */
    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    /**
     * Gets attachments.
     *
     * @return the attachments
     */
    public List < Object > getAttachments() {
        return attachments;
    }

    /**
     * Sets attachments.
     *
     * @param attachments the attachments
     */
    public void setAttachments(List < Object > attachments) {
        this.attachments = attachments;
    }

}

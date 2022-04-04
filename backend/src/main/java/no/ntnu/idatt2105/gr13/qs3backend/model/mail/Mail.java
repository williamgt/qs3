package no.ntnu.idatt2105.gr13.qs3backend.model.mail;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {
    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String contentType;

    private List< Object > attachments;

    public Mail(String mailTo,String password) {
        contentType = "text/plain";
        mailFrom = "qs3Real@outlook.com";
        mailSubject = "QS3 - Registration!";
        mailContent = "Congratulations! You are now registered in QS3!\n" +
                "Use the mail this is sent to as email to log in.\n" +
                "Your password is: " + password +
                "\nMake sure to change this after first log in";

        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(mailTo);
        if(!mat.matches() || !(mailTo.contains(".no") || mailTo.contains(".com"))){
            throw new IllegalArgumentException("This is not a valid email");
        }

        this.mailTo = mailTo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public Date getMailSendDate() {
        return new Date();
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public List < Object > getAttachments() {
        return attachments;
    }

    public void setAttachments(List < Object > attachments) {
        this.attachments = attachments;
    }

}

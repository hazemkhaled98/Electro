package com.electro.services.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {

    private static final String SITE_EMAIL = "electro.noreply@gmail.com";
    private static final String SITE_EMAIL_PASSWORD = "psis wqoz rwjt adhk";

    private EmailUtil(){}

    private static HtmlEmail createNewEmail() throws EmailException {
        var htmlEmail = new HtmlEmail();
        htmlEmail.setHostName( "smtp.gmail.com" );
        htmlEmail.setSmtpPort( 465 );
        htmlEmail.setAuthenticator( new DefaultAuthenticator(SITE_EMAIL, SITE_EMAIL_PASSWORD));
        htmlEmail.setSSLOnConnect( true );
        htmlEmail.setFrom(SITE_EMAIL);
        return htmlEmail;
    }




    public static void subscribeToNewsletter( String receiverMail) throws EmailException {
        Email email =  createNewEmail();
        email.setSubject("Electro - Newsletter");
        email.setMsg("Dear customer, Thank you for subscribing for our newsletter. You will receive our latest updates.");
        email.addTo(receiverMail);
        email.send();
    }

    public static void sendOrderConfirmation(String receiverMail) throws EmailException {
        Email email =  createNewEmail();
        email.setSubject( "Electro - Confirm Order" );
        email.setMsg( "Dear customer, we are pleased to inform you that you order has been placed and on its way to you!");
        email.addTo(receiverMail);
        email.send();
    }
}


package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.Sendable;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.net.URL;

public class EmailOutlookEmail extends HtmlEmail implements Sendable {

    public EmailOutlookEmail() {
        setHostName("smtp.gmail.com");
        setSmtpPort(587);
        setAuthenticator(new DefaultAuthenticator("letsbuygroup@gmail.com", "aflvilqgpmurzold"));
        setSSLOnConnect(true);
        try {
            setFrom("letsbuygroup@gmail.com");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Boolean sendWelcome(String receiverEmail, String receiverName) {
        try {
            setSubject("Vamos começar a comprar e vender juntos na LetsBuy!");
            URL url = new URL("https://i.imgur.com/NNQnocY.png");
            String cid = embed(url, "LetsBuy");

            setHtmlMsg("HTML para outlook");
            setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");

            addTo(receiverEmail);
            send();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean sendChangePassword(String receiverEmail, String receiverName) {
        return null;
    }

    @Override
    public Boolean sendSaleConfirmation(String receiverEmail, String receiverName) {
        return null;
    }
}

package com.application.letsbuy.internal.services;

import org.springframework.stereotype.Service;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import java.net.URL;

@Service
public class EmailService extends HtmlEmail{

    public EmailService() {
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

    public Boolean sendWelcome(String receiverName, String receiverEmail){
        try {
            setSubject("Vamos começar a comprar e vender juntos na LetsBuy!");
            URL url = new URL("https://i.imgur.com/NNQnocY.png");
            String cid = embed(url, "LetsBuy");

            setHtmlMsg("<h1><img src=\""+cid+"\" alt=\"\" width=\"129\" height=\"48\" /></h1>\n" +
                    "<p>Ol&aacute;, "+receiverName+"!</p>\n" +
                    "<p><span class=\"x_ContentPasted0\">Estamos muito felizes em t&ecirc;-lo(a) a bordo da LetsBuy, a plataforma que conecta pessoas para comprar e&nbsp;</span><span class=\"x_ContentPasted0\">vender produtos usados!</span></p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<div>\n" +
                    "<p><span class=\"x_ContentPasted0\">Para que voc&ecirc; possa aproveitar todas as oportunidades que a LetsBuy oferece, &eacute; necess&aacute;rio confirmar o&nbsp;</span><span class=\"x_ContentPasted0\">seu endere&ccedil;o de e-mail. &Eacute; super f&aacute;cil e r&aacute;pido, e voc&ecirc; j&aacute; estar&aacute; pronto(a) para come&ccedil;ar a explorar&nbsp;</span><span class=\"x_ContentPasted0\">todas as ofertas dispon&iacute;veis.</span></p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p font'><span class=\"x_ContentPasted0\"'>Para confirmar o seu e-mail, por favor, clique no bot&atilde;o m&aacute;gico que preparamos para voc&ecirc;:</span></p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>At&eacute; breve!</p>"+
                    "<div><span class=\"x_ContentPasted0\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span></div>\n" +
                    "<div>&nbsp;</div>\n" +
                    "<div style='display: flex;justify-content: center;align-items: center'>"+
                    "<button style='display: flex;justify-content: center;align-items: center;max-width: 300px;width: 100%;height: 50px;color: #FFFF;background-color: #F14866;font-family: 'Poppins';font-size: 16px;font-weight: 500;border-radius: 5px;margin: 50px 0;'>Confirmar Email</button>"+
                    "<div>&nbsp;</div>\n"+
                    "</div>"+
                    "<div>&nbsp;</div>\n"+
                    "<div style='display: flex;justify-content: center;border-top: 1px solid #E6E1DD;padding: 10px 0;'>" +
                    "<p style=\"font-family: 'sans-serif';font-size: 12px;font-weight: 500;color: #8F8F8F;text-align: center;\">LetsBuy - &copy; Todos os direitos reservados</p>" +
                    "</div>"+
                    "</div>");
            setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");

            addTo(receiverEmail);
            send();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

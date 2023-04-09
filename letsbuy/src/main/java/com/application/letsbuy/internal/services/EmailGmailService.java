package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.Sendable;
import org.springframework.stereotype.Service;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import java.net.URL;

@Service
public class EmailGmailService extends HtmlEmail implements Sendable {

    public EmailGmailService() {
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
    public Boolean sendWelcome(String receiverName,String receiverEmail) {
        try {
            setSubject("Vamos começar a comprar e vender juntos na LetsBuy!");
            setHtmlMsg("""
                    <div style="width:100%;font-family:arial,'helvetica neue',helvetica,sans-serif;padding:0;Margin:0">
                    <div style="background-color:#f6f6f6">
                    <table width="100%" cellspacing="0" cellpadding="0" style="border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#f6f6f6">
                    <tbody><tr>
                    <td valign="top" style="padding:0;Margin:0">
                    <table class="m_6229933818003390889es-header" cellspacing="0" cellpadding="0" align="center" style="border-collapse:collapse;border-spacing:0px;table-layout:fixed!important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top">
                    <tbody><tr>
                    <td align="center" style="padding:0;Margin:0">
                    <table class="m_6229933818003390889es-header-body" cellspacing="0" cellpadding="0" bgcolor="#ffffff" align="center" style="border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px">
                    <tbody><tr>
                    <td align="left" style="padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px">
                    <table cellspacing="0" cellpadding="0" width="100%" style="border-collapse:collapse;border-spacing:0px">
                    <tbody><tr>
                    <td class="m_6229933818003390889es-m-p0r" valign="top" align="center" style="padding:0;Margin:0;width:560px">
                    <table width="100%" cellspacing="0" cellpadding="0" role="presentation" style="border-collapse:collapse;border-spacing:0px">
                    <tbody><tr>
                    <td align="center" style="padding:0;Margin:0;font-size:0px"><img class="m_6229933818003390889adapt-img CToWUd" src="https://ci6.googleusercontent.com/proxy/u37faxLaK8dAOIJECx4Mgl2GUOgA_63Td4ouDUBW0bYgTX6Y4JusshPRjpBT6T6k-Zs=s0-d-e1-ft#https://i.imgur.com/NNQnocY.png" alt="" style="display:block;border:0;outline:none;text-decoration:none" height="59" width="159" data-bit="iit"></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table>
                    <table class="m_6229933818003390889es-content" cellspacing="0" cellpadding="0" align="center" style="border-collapse:collapse;border-spacing:0px;table-layout:fixed!important;width:100%">
                    <tbody><tr>
                    <td align="center" style="padding:0;Margin:0">
                    <table class="m_6229933818003390889es-content-body" cellspacing="0" cellpadding="0" bgcolor="#ffffff" align="center" style="border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px">
                    <tbody><tr>
                    <td align="left" style="padding:0;Margin:0;padding-top:20px;padding-right:20px;padding-left:30px">
                    <table width="100%" cellspacing="0" cellpadding="0" style="border-collapse:collapse;border-spacing:0px">
                    <tbody><tr>
                    <td valign="top" align="center" style="padding:0;Margin:0;width:550px">
                    <table width="100%" cellspacing="0" cellpadding="0" role="presentation" style="border-collapse:collapse;border-spacing:0px">
                    <tbody><tr>
                    <td align="left" bgcolor="#ffffff" style="padding:0;Margin:0"><p style="Margin:0;font-family:arial,'helvetica neue',helvetica,sans-serif;line-height:21px;color:#333333;font-size:14px">Olá, Kawan!</p><p style="Margin:0;font-family:arial,'helvetica neue',helvetica,sans-serif;line-height:21px;color:#333333;font-size:14px">Estamos muito felizes em tê-lo(a) a bordo da LetsBuy, a plataforma que conecta pessoas para comprar e&nbsp;vender produtos usados!</p><p style="Margin:0;font-family:arial,'helvetica neue',helvetica,sans-serif;line-height:21px;color:#333333;font-size:14px"><br></p><p style="Margin:0;font-family:arial,'helvetica neue',helvetica,sans-serif;line-height:21px;color:#333333;font-size:14px">Para que você possa aproveitar todas as oportunidades que a LetsBuy oferece, é necessário confirmar o&nbsp;seu endereço de e-mail. É super fácil e rápido, e você já estará pronto(a) para começar a explorar&nbsp;todas as ofertas disponíveis.</p><p style="Margin:0;font-family:arial,'helvetica neue',helvetica,sans-serif;line-height:21px;color:#333333;font-size:14px"><br></p><p style="Margin:0;font-family:arial,'helvetica neue',helvetica,sans-serif;line-height:21px;color:#333333;font-size:14px">Para confirmar o seu e-mail, por favor, clique no botão mágico que preparamos para você:</p><p style="Margin:0;font-family:arial,'helvetica neue',helvetica,sans-serif;line-height:21px;color:#333333;font-size:14px"><br></p><p style="Margin:0;font-family:arial,'helvetica neue',helvetica,sans-serif;line-height:21px;color:#333333;font-size:14px">Até breve!</p></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table>
                    <table class="m_6229933818003390889es-footer" cellspacing="0" cellpadding="0" align="center" style="border-collapse:collapse;border-spacing:0px;table-layout:fixed!important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top">
                    <tbody><tr>
                    <td align="center" style="padding:0;Margin:0">
                    <table class="m_6229933818003390889es-footer-body" cellspacing="0" cellpadding="0" bgcolor="#ffffff" align="center" style="border-collapse:collapse;border-spacing:0px;background-color:#ffffff;width:600px">
                    <tbody><tr>
                    <td align="left" style="Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px">
                    <table cellspacing="0" cellpadding="0" width="100%" style="border-collapse:collapse;border-spacing:0px">
                    <tbody><tr>
                    <td align="left" style="padding:0;Margin:0;width:560px">
                    <table width="100%" cellspacing="0" cellpadding="0" role="presentation" style="border-collapse:collapse;border-spacing:0px">
                    <tbody><tr>
                    <td align="center" style="padding:0;Margin:0"><span class="m_6229933818003390889es-button-border" style="background:#f14866;border-width:0px 0px 2px 0px;display:inline-block;border-radius:5px;width:auto"><a class="m_6229933818003390889es-button" style="text-decoration:none;color:#ffffff;font-size:18px;display:inline-block;background:#f14866;border-radius:5px;font-family:verdana,geneva,sans-serif;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center;padding:10px 20px 10px 20px;border-color:#f14866">Confirmar email</a></span></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table></td>
                    </tr>
                    </tbody></table>
                    </div>
                    </div>""");
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

        try {
            setSubject("Solicitação de troca de senha LetsBuy!");
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
    public Boolean sendSaleConfirmation(String receiverEmail, String receiverName) {

        try {
            setSubject("Parabéns, você vendeu na LetsBuy!");
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
}

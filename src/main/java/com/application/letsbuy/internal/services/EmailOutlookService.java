package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.Sendable;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.net.URL;

public class EmailOutlookService extends HtmlEmail implements Sendable {

    public EmailOutlookService() {
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
    public Boolean sendChangePassword(String receiverName, String receiverEmail, String receiverId) {
        try {
            setSubject("Solicitação de troca de senha LetsBuy!");
            URL url = new URL("https://i.imgur.com/NNQnocY.png");
            String cid = embed(url, "LetsBuy");

            setHtmlMsg("""
                    <div>
                    <div class="rps_8658">
                    <div>
                    <h1><img alt="" class="Do8Zj" crossorigin="use-credentials" data-custom="AAMkAGNjM2I4ZTk4LWM0ZmMtNGVlMi04YTc3LTZjNDk4NTNlNTJmYgBGAAAAAADxMfB1dgD1QKLeCrPvxUsQBwDWSiaFuQ1VSKKlmNst0nubAAAAAAEMAADWSiaFuQ1VSKKlmNst0nubAAEf0It7AAABEgAQAKkUcq3BzdFHuOpBS1%2Fx44Q%3D" data-imagetype="AttachmentNone" fetchpriority="high" height="48" naturalheight="0" naturalwidth="0" originalsrc="cid:vaoinejbaa" src="""+cid+"""
                     style="cursor: pointer; min-height: auto; min-width: auto;" width="129" /></h1>
                                        
                    <p>Ol&aacute;, """+ receiverName +"""
                    !</p>
                                        
                    <p><span class="x_x_ContentPasted0">Percebemos que voc&ecirc;&nbsp;esqueceu a sua senha de acesso &agrave;&nbsp;LetsBuy, mas n&atilde;o se preocupe, estamos aqui para ajud&aacute;-lo(a)!</span></p>
                                        
                    <p aria-hidden="true">&nbsp;</p>
                                        
                    <div>
                    <p><span class="x_x_ContentPasted0">Para redefinir a sua senha, basta clicar no bot&atilde;o abaixo e preencher os campos necess&aacute;rios. &Eacute;&nbsp;importante lembrar que, por motivos de seguran&ccedil;a, o link tem validade de 24 horas. Caso n&atilde;o consiga redefinir a sua senha nesse prazo, &Eacute;&nbsp;s&oacute;&nbsp;repetir o processo novamente</span></p>
                                        
                    <p aria-hidden="true">&nbsp;</p>
                                        
                    <p><span class="x_x_ContentPasted0">Lembrando que sua nova senha precisa ter pelo menos 8 caracteres e uma combina&ccedil;&atilde;o de letras e n&uacute;meros. Isso garante mais seguran&ccedil;a para voc&ecirc;&nbsp;e para todos os usu&aacute;rios da plataforma.</span></p>
                                        
                    <p aria-hidden="true">&nbsp;</p>
                                        
                    <p><span class="x_x_ContentPasted0">Ah, e se voc&ecirc;&nbsp;n&atilde;o solicitou a redefini&ccedil;&atilde;o da senha, por favor, entre em contato conosco imediatamente. Pode ter havido alguma atividade suspeita na sua conta e precisamos investigar.</span></p>
                                        
                    <p aria-hidden="true">&nbsp;</p>
                                        
                    <p>At&eacute; breve!</p>
                                        
                    <div><span class="x_x_ContentPasted0">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span></div>
                                        
                    <div aria-hidden="true">&nbsp;</div>
                                        
                    <div style="display:flex; justify-content:center; align-items:center"><a style="text-decoration:none; max-width:300px; width:100%; height:50px" target="_blank" href="https://letsbuy-web.vercel.app/alterar-senha/:""" + receiverId + """
                    "><button style="display:flex; justify-content:center; align-items:center; max-width:300px; width:100%; height:50px; color:#FFFF; background-color:#F14866; font-family:">Alterar senha</button></a>
                                        
                    <div aria-hidden="true">&nbsp;</div>
                    </div>
                                        
                    <div aria-hidden="true">&nbsp;</div>
                                        
                    <div style="display:flex; justify-content:center; border-top:1px solid #E6E1DD; padding:10px 0">
                    <p style="font-family:'sans-serif'; font-size:12px; font-weight:500; color:#8F8F8F; text-align:center">LetsBuy - &copy; Todos os direitos reservados</p>
                    </div>
                    </div>
                    </div>
                    </div>
                    </div>
                                        
                    """);
            setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");

            addTo(receiverEmail);
            send();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

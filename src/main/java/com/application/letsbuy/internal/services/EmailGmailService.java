package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.Sendable;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

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
    public Boolean sendChangePassword(String receiverName,String receiverEmail, String receiverId) {

        try {
            setSubject("Solicitação de troca de senha LetsBuy!");
            setHtmlMsg("""
                    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                    <html xmlns="http://www.w3.org/1999/xhtml" xmlns:o="urn:schemas-microsoft-com:office:office" style="font-family:arial, 'helvetica neue', helvetica, sans-serif">
                     <head>
                      <meta content="width=device-width, initial-scale=1" name="viewport">
                      <meta charset="UTF-8">
                      <meta name="x-apple-disable-message-reformatting">
                      <meta http-equiv="X-UA-Compatible" content="IE=edge">
                      <meta content="telephone=no" name="format-detection">
                      <title>Template</title><!--[if (mso 16)]>
                        <style type="text/css">
                        a {text-decoration: none;}
                        </style>
                        <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]>
                    <xml>
                        <o:OfficeDocumentSettings>
                        <o:AllowPNG></o:AllowPNG>
                        <o:PixelsPerInch>96</o:PixelsPerInch>
                        </o:OfficeDocumentSettings>
                    </xml>
                    <![endif]-->
                      <style type="text/css">
                    #outlook a {
                    \tpadding:0;
                    }
                    .es-button {
                    \tmso-style-priority:100!important;
                    \ttext-decoration:none!important;
                    }
                    a[x-apple-data-detectors] {
                    \tcolor:inherit!important;
                    \ttext-decoration:none!important;
                    \tfont-size:inherit!important;
                    \tfont-family:inherit!important;
                    \tfont-weight:inherit!important;
                    \tline-height:inherit!important;
                    }
                    .es-desk-hidden {
                    \tdisplay:none;
                    \tfloat:left;
                    \toverflow:hidden;
                    \twidth:0;
                    \tmax-height:0;
                    \tline-height:0;
                    \tmso-hide:all;
                    }
                    @media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:30px!important; text-align:left } h2 { font-size:24px!important; text-align:left } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:24px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class="gmail-fix"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:18px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } }
                    </style>
                     </head>
                     <body style="width:100%;font-family:arial, 'helvetica neue', helvetica, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0">
                      <div class="es-wrapper-color" style="background-color:#FFFFFF"><!--[if gte mso 9]>
                    \t\t\t<v:background xmlns:v="urn:schemas-microsoft-com:vml" fill="t">
                    \t\t\t\t<v:fill type="tile" color="#ffffff" origin="0.5, 0" position="0.5, 0"></v:fill>
                    \t\t\t</v:background>
                    \t\t<![endif]-->
                       <table class="es-wrapper" width="100%" cellspacing="0" cellpadding="0" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#FFFFFF">
                         <tr>
                          <td valign="top" style="padding:0;Margin:0">
                           <table class="es-header" cellspacing="0" cellpadding="0" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top">
                             <tr>
                              <td align="center" style="padding:0;Margin:0">
                               <table class="es-header-body" cellspacing="0" cellpadding="0" bgcolor="#ffffff" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px">
                                 <tr>
                                  <td align="left" style="padding:0;Margin:0;padding-top:20px;padding-left:20px;padding-right:20px">
                                   <table cellspacing="0" cellpadding="0" width="100%" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                     <tr>
                                      <td class="es-m-p0r" valign="top" align="center" style="padding:0;Margin:0;width:560px">
                                       <table width="100%" cellspacing="0" cellpadding="0" role="presentation" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                         <tr>
                                          <td align="center" style="padding:0;Margin:0;font-size:0px"><img class="adapt-img" src="https://i.imgur.com/NNQnocY.png" alt style="display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic" height="59" width="159"></td>
                                         </tr>
                                       </table></td>
                                     </tr>
                                   </table></td>
                                 </tr>
                               </table></td>
                             </tr>
                           </table>
                           <table class="es-content" cellspacing="0" cellpadding="0" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%">
                             <tr>
                              <td align="center" style="padding:0;Margin:0">
                               <table class="es-content-body" cellspacing="0" cellpadding="0" bgcolor="#ffffff" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px">
                                 <tr>
                                  <td align="left" style="padding:0;Margin:0;padding-top:20px;padding-right:20px;padding-left:30px">
                                   <table width="100%" cellspacing="0" cellpadding="0" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                     <tr>
                                      <td valign="top" align="center" style="padding:0;Margin:0;width:550px">
                                       <table width="100%" cellspacing="0" cellpadding="0" role="presentation" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                         <tr>
                                          <td align="left" bgcolor="#ffffff" style="padding:0;Margin:0"><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px">Olá, """+receiverName+
                                         """
                                         !</p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px">Percebemos que você esqueceu a sua senha de acesso à LetsBuy, mas não se preocupe, estamos aqui para ajudá-lo(a)!</p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px"><br></p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px">Para redefinir a sua senha, basta clicar no botão abaixo e preencher os campos necessários. É importante lembrar que, por motivos de segurança, o link tem validade de 24 horas. Caso não consiga redefinir a sua senha nesse prazo, é só repetir o processo novamente.</p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px"><br></p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px">Lembrando que sua nova senha precisa ter pelo menos 8 caracteres e uma combinação de letras e números. Isso garante mais segurança para você e para todos os usuários da plataforma.</p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px"><br></p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px">Ah, e se você não solicitou a redefinição da senha, por favor, entre em contato conosco imediatamente. Pode ter havido alguma atividade suspeita na sua conta e precisamos investigar.<br></p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px"><br></p><p style="Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;line-height:21px;color:#333333;font-size:14px">Até breve!</p></td>
                                         </tr>
                                       </table></td>
                                     </tr>
                                   </table></td>
                                 </tr>
                               </table></td>
                             </tr>
                           </table>
                           <table class="es-footer" cellspacing="0" cellpadding="0" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top">
                             <tr>
                              <td align="center" style="padding:0;Margin:0">
                               <table class="es-footer-body" cellspacing="0" cellpadding="0" bgcolor="#ffffff" align="center" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;width:600px">
                                 <tr>
                                  <td align="left" style="Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px">
                                   <table cellspacing="0" cellpadding="0" width="100%" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                     <tr>
                                      <td align="left" style="padding:0;Margin:0;width:560px">
                                       <table width="100%" cellspacing="0" cellpadding="0" role="presentation" style="mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px">
                                         <tr>
                                          <td align="center" style="padding:0;Margin:0"><!--[if mso]><a href="https://letsbuy.azurewebsites.net/alterar-senha/:""" + receiverId + """
                    " target="_blank" hidden>
                    \t<v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word" esdevVmlButton href=""\s
                                    style="height:41px; v-text-anchor:middle; width:148px" arcsize="12%" stroke="f"  fillcolor="#f14866">
                    \t\t<w:anchorlock></w:anchorlock>
                    \t\t<a style="text-decoration:none; max-width:300px; width:100%; height:50px" target="_blank" href="https://letsbuy-web.vercel.app/alterar-senha/:""" + receiverId + """
                    <center style='color:#ffffff; font-family:verdana, geneva, sans-serif; font-size:15px; font-weight:400; line-height:15px;  mso-text-raise:1px'>Alterar senha</center></a>
                    \t</v:roundrect></a>
                    <![endif]--><!--[if !mso]><!-- --><span class="msohide es-button-border" style="border-style:solid;border-color:#2cb543;background:#f14866;border-width:0px;display:inline-block;border-radius:5px;width:auto;mso-border-alt:10px;mso-hide:all"><a href="https://letsbuy-web.vercel.app/alterar-senha/:""" + receiverId + """
                                         " class="es-button" target="_blank" style="mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#FFFFFF;font-size:18px;display:inline-block;background:#f14866;border-radius:5px;font-family:verdana, geneva, sans-serif;font-weight:normal;font-style:normal;line-height:22px;width:auto;text-align:center;padding:10px 20px 10px 20px;border-color:#f14866">Alterar senha</a></span><!--<![endif]--></td>
                                         </tr>
                                       </table></td>
                                     </tr>
                                   </table></td>
                                 </tr>
                               </table></td>
                             </tr>
                           </table></td>
                         </tr>
                       </table>
                      </div>
                     </body>
                    </html>""");
            setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");

            addTo(receiverEmail);
            send();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

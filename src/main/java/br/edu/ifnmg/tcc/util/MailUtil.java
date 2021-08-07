//package br.edu.ifnmg.tcc.util;
//
//import static com.oracle.jrockit.jfr.ContentType.Address;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
//import java.io.UnsupportedEncodingException;
//import java.net.Authenticator;
//import java.net.PasswordAuthentication;
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.mail.Address;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import org.hibernate.Session;
//import org.jboss.logging.annotations.Message;
//import sun.rmi.transport.Transport;
//
//public class MailUtil {
//    
//    
//    public static void sendMail(String[] emailsDest, String nomeDest, String emailRemet, String senhaEmail, String nomeRemet, String assunto, String corpo) {
//        try {
//            Properties props = System.getProperties();
//
//            props.put("mail.smtp.host", "smtp.gmail.com"); 
////            props.put("mail.smtp.port", "25"); 
//            props.put("mail.debug", "true"); 
//            props.put("mail.smtp.auth", "true"); 
//            props.put("mail.smtp.starttls.enable","true"); 
//            props.put("mail.smtp.EnableSSL.enable","true");
//
//            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
//            props.setProperty("mail.smtp.socketFactory.fallback", "false");   
////            props.setProperty("mail.smtp.port", "587");   
//            props.setProperty("mail.smtp.port", "465");   
//            props.setProperty("mail.smtp.socketFactory.port", "465"); 
////            props.setProperty("mail.smtp.socketFactory.port", "587"); 
//            
//            final String remetente = emailRemet;
//            final String senha = senhaEmail;
//            
//            Authenticator auth = new Authenticator() {
//
//                @Override
//                public PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(remetente, senha);
//                }
//            };
//            
//            Session session = Session.getDefaultInstance(props, auth);
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(emailRemet, nomeRemet));
//            Address[] addresses = new Address[emailsDest.length];
//            for(int i = 0; i < emailsDest.length; i++){
//                addresses[i] = new InternetAddress(emailsDest[i].toLowerCase().trim());
//            }
//            message.addRecipients(Message.RecipientType.TO, addresses);
//            message.setSubject(assunto);
////            message.setContent(corpo, "text/plain");
//            message.setContent(corpo, "text/html");
//            
////            // connect to the transport
////            Transport transport = session.getTransport("smtps");
////            transport.connect("smtp.gmail.com", 465, "email@gmail.com", "senha"); // host, user, password
////            // send the msg and close the connection
////            transport.sendMessage(message, message.getAllRecipients());
////            transport.close();
//
//            
//            Transport.send(message);
//        } catch (UnsupportedEncodingException | MessagingException ex) {
//            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//
//}

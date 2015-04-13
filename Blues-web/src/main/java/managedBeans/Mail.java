package managedBeans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author DANYAL 
 */
@ManagedBean
@SessionScoped
public class Mail {

    private String to;
    private String from;
    private String message;
    private String subject;
    private String smtpServ;
    private String compte;
    private String motpasse;

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSmtpServ() {
        return smtpServ;
    }

    public void setSmtpServ(String smtpServ) {
        this.smtpServ = smtpServ;
    }

   

    public int sendMail() {
        try {
            Properties props = System.getProperties();
            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.starttls.enable", true);

           this.setFrom("Java.Mail.CA@gmail.com");
           this.setSmtpServ("smtp.gmail.com");
 props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

            props.put("mail.smtp.host", smtpServ);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", true);

            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            // -- We could include CC recipients too --
            // if (cc != null)
            // msg.setRecipients(Message.RecipientType.CC
            // ,InternetAddress.parse(cc, false));
            // -- Set the subject and body text --
            msg.setSubject(subject);
            msg.setText(message);
            // -- Set some other header information --
            msg.setHeader("MyMail", "Java Mail Test");
            msg.setSentDate(new Date());
            // -- Send the message --
            Transport.send(msg);
            System.out.println("Message sent to" + to + " OK.");
          
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mail Successfully Sent", to));
   
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception " + ex);
            return -1;
        }
    }
    public String envoyer()
    {
        System.out.println("1");
        Properties p =new Properties();
        p.put("mail.smtp.host","smtp.gmail.com");
        p.put("mail.smtp.socketFactory.port","465");
        p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth","true");
        p.put("mail.smtp.port","465");
        p.put("mail.smtp.ssl.enable", "true");
        
        Session sess=Session.getDefaultInstance(p, 
                new javax.mail.Authenticator(){
            protected PasswordAuthentication  getPasswordAuthentication(){
                return new PasswordAuthentication("Reclamation.BestTrader@gmail.com","blues2015");
            }
        });
        System.out.println("2");
        try {
            Transport tr = sess.getTransport("smtp");
            Message msg=new MimeMessage(sess);
            System.out.println("3");
            msg.setFrom(new InternetAddress("bouabda.hamza@gmail.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText(message);
            System.out.println("4");
            Transport.send(msg);
            System.out.println("taraji ya dawla");
            tr.close();
            
        } catch (Exception e) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
    }
    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
//            String username = "guelmim4ever@gmail.com";
//            String password = "********";
            return new PasswordAuthentication(getCompte(), getMotpasse());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Authentication;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Phung An
 */
public class SendEmail {

    // pass:hqhg edip bajd foln
    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean sendEmail1(UserC userc) {
        boolean test = false;
        String fromEmail = "anpk2300@gmail.com";
        String password = "hqhg edip bajd foln";
        String toEmail = userc.getEmail();
        try {
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com"); // Thay đổi thành máy chủ SMTP thực tế
            pr.setProperty("mail.smtp.port", "587");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.setProperty("mail.smtp.socketFactory.port", "587");
            pr.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //get sesion
            Session session;
            session = Session.getInstance(pr, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            //mesage
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject("User Email Verification");
            mess.setText("Registered successfully. Please verify your account using this code: "+userc.getCode());
            Transport.send(mess);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
    public boolean sendEmail2(UserC userc) {
        boolean test = false;
        String fromEmail = "anpk2300@gmail.com";
        String password = "hqhg edip bajd foln";
        String toEmail = userc.getEmail();
        try {
            Properties pr = new Properties();// cau hinh cac thuoc tinh gui email
            pr.setProperty("mail.smtp.host", "smtp.gmail.com"); // Thay đổi thành máy chủ SMTP thực tế
            pr.setProperty("mail.smtp.port", "587");// dung de ket noi tls
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.setProperty("mail.smtp.socketFactory.port", "587");
            pr.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //get sesion
            Session session;
            session = Session.getInstance(pr, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            //mesage
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject("User Email Verification");
            mess.setText("Update new password successfully. Please your account using this code: "+userc.getCode());
            Transport.send(mess);
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

}

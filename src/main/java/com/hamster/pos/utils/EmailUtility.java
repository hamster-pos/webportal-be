package com.hamster.pos.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
//@PropertySource(value="classpath:application.properties") 
@Service
public class EmailUtility{
//	@Value("${hostname}")
//	private String myHost;
//	
//	@Value("${server.contextPath}")
//	private String path;
//
//	@Value("${email.Id}")
//	private String emailid;
//	
//	@Value("${email.Password}")
//	private String emailpassword;
	
	
//	public static void main(String[] args) {
//		EmailUtility e = new EmailUtility();
//		e.send();
//	}

	
	public void send(String reciver, String password) {
		
		System.out.println("Email Util:-"+reciver);
		
		// Recipient's email ID to be fetched from database.
		String to = reciver;

		// Sender's email ID needs to be mentioned
		String from = "aniket.deshmukh.fidel@gmail.com";
		

		// Assuming you are sending email from localhost
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.required", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.starttls.auth", true);
		properties.put("mail.smtp.auth", true);
		final String usernameFinal = "aniket.deshmukh.fidel@gmail.com";
		final String passwordFinal = "aniket@gmail@fits";
		

		// Get the default Session object.
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usernameFinal, passwordFinal);
			}
		});
		


				try {
					// Create a default MimeMessage object.
					MimeMessage message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));

					// Set To: header field of the header.
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

					//List<String> content = this.getContentDetails(cl);
					// Set Subject: header field
					message.setSubject("test");
			
			//String shttp = new String("http://"+myHost+path+"/#/"+content.get(2)+"/"+objectId);
			String shttp = new String("http://google.com");
			
//	       message.setContent(" <h5> " + "Thank you for working with Hamster" + " </h5> Your customerId is 1001 and Code is testcode"
//	         		+ " <h5>" + "Please download and install Hamster POS app :  "+" <a href='"+shttp+"'>click here</a>", "text/html" );
			
	       message.setContent(" <h5> " + "Thank you for working with Hamster"
	         		+ " <h5>" + "Your temporary password is : " + password, "text/html" );

			// Send message
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
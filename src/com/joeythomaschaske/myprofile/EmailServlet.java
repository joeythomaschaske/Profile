/**
 * Sends contact info to admin email. 
 *
 * @author Andrew Budziszek
 */
package com.joeythomaschaske.myprofile;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("serial")
public class EmailServlet extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		resp.sendRedirect("index.html");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String msgBody = req.getParameter("message");
		String sendersEmail = req.getParameter("email");
		String sendersName = req.getParameter("name"); 
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("joeythomaschaske@gmail.com", sendersName));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("jptiii@uwm.edu", "Profile Email"));
			msg.setSubject("New message from: " + sendersName);
			msg.setText("From: " + sendersName + " - " + sendersEmail + "\nMessage:\n" + msgBody);
			Transport.send(msg);
			resp.sendRedirect("contact.html");
		} catch (AddressException e) {
			e.printStackTrace(); 
			System.out.println("Error with address format");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Message Error!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("about.html");
	}

}
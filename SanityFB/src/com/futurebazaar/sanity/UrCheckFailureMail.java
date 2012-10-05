package com.futurebazaar.sanity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class UrCheckFailureMail {

	public static void sendMail() throws FileNotFoundException
	{
		// Recipient's email ID needs to be mentioned.
	         String fs = File.separator;    
             // String to[] = {"hemanth.goteti@futuregroup.in"};
                String to[] ={"fb-qa@futuregroup.in"};
         //adding CC
	      String cc[] = {"shabbir.kawari@futuregroup.in","sourabh.gaikwad@futuregroup.in"};
	     //String cc[] ={};
	     //String bcc= "hardikbalar101@gmail.com";
	      
	      // Sender's email ID needs to be mentioned
	      String from = "SmokeTestReport@futuregroup.com";

	      // Assuming you are sending email from localhost
	      String host = "MUMOPBL01.fb.dc";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         for(int i=0;i<to.length;i++)
	         {
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to[i]));
	         }
	         //if you want to add CC of the message. 
	         for(int i=0;i<cc.length;i++)
	         {
	        message.addRecipient(Message.RecipientType.CC,new InternetAddress(cc[i]));
	         }
	         //bcc
	   //     message.addRecipient(Message.RecipientType.BCC,new InternetAddress(bcc));
	         // Set Subject: header field
	         message.setSubject("URL check Smoke Testing HTML Report.");

	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         
	         messageBodyPart.setText("Hi All, \nPlease find the attached 'BuildPush Smoke Testing HTML Report File' ,\n\n This Test runs on:  ");
	       
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         
	         //attaching the file 
	      
	         //attaching Second 
	        /* MimeBodyPart messageBodyPart2 = new MimeBodyPart();
	         String filepath2 = System.getProperty("user.dir")+"\\Reports\\"+result+"\\Email_XLS_Report.zip";
	         String filename2 = "Email_XLS_Report.zip";
	         DataSource source2 = new FileDataSource(filepath2);
	         messageBodyPart2.setDataHandler(new DataHandler(source2));
	         messageBodyPart2.setFileName(filename2);
	         multipart.addBodyPart(messageBodyPart2);
*/
	         File fFile = new File(System.getProperty("user.dir")+fs+"test-output"+fs+"emailable-report.html");
	         String html_trimmed="";
	     Scanner scanner = new Scanner(new FileReader(fFile));
	      try {
	        while ( scanner.hasNextLine() ){
	          String s=scanner.nextLine();
	         // System.out.println(s);
	          String tmp= s.trim();
	          html_trimmed= html_trimmed.concat(tmp);

	        }
	      }
	      finally {         
	        scanner.close();
	      }

	         
	         // Send the complete message parts
	         // Send the actual HTML message, as big as you like
	         
	         message.setContent(html_trimmed,
	                            "text/html" );


	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		UrCheckFailureMail uu = new UrCheckFailureMail();
		uu.sendMail();
	}
	
	
}

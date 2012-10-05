package com.futurebazaar.sanity;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail
{
	public  static void Mailsend(String Orderid,String url){

    String to[] = {"fb-qa@futuregroup.in"};
    //adding CC
     String cc[] = {"shabbir.kawari@futuregroup.in","sourabh.gaikwad@futuregroup.in"};
     
    //String bcc= "hardikbalar101@gmail.com";
     
     // Sender's email ID needs to be mentioned
     String from = "SanityOrder@futuregroup.com";

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
        message.setSubject("Order cancellation for Build Push Sanity");

        // Create the message part 
        BodyPart messageBodyPart = new MimeBodyPart();

        // Fill the message
        
        messageBodyPart.setText( "Order has been placed on "+url+" for the Build Push Sanity Test, So please cancel the order No. "+Orderid+".");
      
        
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
     
        
        // Send the complete message parts
        // Send the actual HTML message, as big as you like
        
        message.setContent(multipart);


        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");

            }

    catch (Exception mex)

    {

        mex.printStackTrace();

                  

    }

			}
	  	
  public static void main(String [] args)
   {
      
	Mail.Mailsend("53721537613","http://www.fuurebazarr.com");
}
}

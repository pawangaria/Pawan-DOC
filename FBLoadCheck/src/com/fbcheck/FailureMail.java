package com.fbcheck;




import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class FailureMail {

	public  static boolean failMailsend(String userName,
    		String passWord,
    		String host,
    		String port,
    		String starttls,
    		String auth,
    		boolean debug,
    		String socketFactoryClass,
    		String fallback,
    		String[] to,
    		String[] cc,
    		String[] bcc,
    		String subject,
    		String text,
    		String attachmentPath,String attachmentName
    		
    		){


            Properties props = new Properties();

            //Properties props=System.getProperties();

    props.put("mail.smtp.user", userName);

    props.put("mail.smtp.host", host);

            if(!"".equals(port))

    props.put("mail.smtp.port", port);

            if(!"".equals(starttls))

    props.put("mail.smtp.starttls.enable",starttls);

    props.put("mail.smtp.auth", auth);
   // props.put("mail.smtps.auth", "true");


            if(debug){

            props.put("mail.smtp.debug", "true");

            }else{

            props.put("mail.smtp.debug", "false");         

            }

            if(!"".equals(port))

    props.put("mail.smtp.socketFactory.port", port);

            if(!"".equals(socketFactoryClass))

    props.put("mail.smtp.socketFactory.class",socketFactoryClass);

            if(!"".equals(fallback))

    props.put("mail.smtp.socketFactory.fallback", fallback);



    try

    {

                    Session session = Session.getDefaultInstance(props, null);

        session.setDebug(debug);

        MimeMessage msg = new MimeMessage(session);

        msg.setText(text);

        msg.setSubject(subject);
        
        
        BodyPart messageBodyPart = new MimeBodyPart();
        String htmlText = "<H1 style="+"color:red;"+ "> FutureBazaar Home Page Failure</H1>" + 
          "<img src=\"cid:memememe\">";
        messageBodyPart.setContent(htmlText, "text/html");

     // Create a related multi-part to combine the parts
        MimeMultipart multipart = new MimeMultipart("related");
        multipart.addBodyPart(messageBodyPart);

        // Create part for the image
        messageBodyPart = new MimeBodyPart();

        // Fetch the image and associate to part
        DataSource fds = new FileDataSource(attachmentPath);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<memememe>");

        // Add part to multi-part
        multipart.addBodyPart(messageBodyPart);
        // Associate multi-part with message
        msg.setContent(multipart);
       // messageBodyPart.setText(text);
        msg.setFrom(new InternetAddress("futurebazaarsanity@gmail.com"));

                    for(int i=0;i<to.length;i++){

        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));

                    }

                    for(int i=0;i<cc.length;i++){

        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));

                    }

                    for(int i=0;i<bcc.length;i++){

        msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));

                    }

        msg.saveChanges();

                    Transport transport = session.getTransport("smtp");

                    transport.connect(host, userName, passWord);

                    transport.sendMessage(msg, msg.getAllRecipients());

                    transport.close();

                    return true;

    }

    catch (Exception mex)

    {

        mex.printStackTrace();

                    return false;

    }

    }

	public static void sendMail()
	{
		 String[] to={"krishna.raghavan@futuregroup.in","hemanth.goteti@futuregroup.in"};
		//String[] to={"pawan garia<pawan.garia@futuregroup.in>"};
      String[] cc={"fb-qa@futuregroup.in","raunak.pilani@futuregroup.in","dipankar.sinha@futuregroup.in"};
		//String[] cc={};
         String[] bcc={};

		 FailureMail.failMailsend("futurebazaarsanity@gmail.com",
		            "qatest123",
		            "smtp.gmail.com",
		            "465",
		            "true",
		            "true",
		            true,
		            "javax.net.ssl.SSLSocketFactory",
		            "false",
		            to,
		            cc,
		            bcc,
		            "FutureBazaar Home page failure(SnapShot Attached)",
		            "Futurebazaar home page download timeout exceeded threshold of 20 seconds.\n\n Regards,\nAutomation Team"
		           ,"C:\\screenshot.png","screenshot.png"
		        	);
		
	   

			}
	  	
  public static void main(String [] args)
   {
      
	  FailureMail.sendMail();
}
}

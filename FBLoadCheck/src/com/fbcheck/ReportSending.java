package com.fbcheck;


//set CLASSPATH=%CLASSPATH%;activation.jar;mail.jar

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;

import javax.mail.internet.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;



public class ReportSending

{

	public static String[] to={"krishna.raghavan@futuregroup.in","hemanth.goteti@futuregroup.in"};
		//String[] to={"pawan.garia@futuregroup.in"};
	public static  String[] cc={"fb-qa@futuregroup.in"};
	//String[] cc={"pawangaria@gmail.com"};
	public static String[] bcc={};
	
	
  public static void main(String[] args) throws Exception
  {      
  	     
              //This is for google

	  ReportSending.Mailsend("231","3213","3123","3123.21");
  }
  
  public static void Mailsend(String toalTESTrUNcount,String passcount,String failcount,String averageloadingtime)
  
  {
  	System.out.println(toalTESTrUNcount);
  	System.out.println(passcount);
  	System.out.println(averageloadingtime);
  	ReportSending.sendMail("futurebazaarsanity@gmail.com",
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
	            "Test Report for Futurebazaar Home Page ",
	            "Futurebazaar Home Page Test Report for Last 24 Hours,\n\n Total Tests Run =   "+toalTESTrUNcount+"\n\n Total Tests Passed = "+passcount+"\n\n Total Tests Failed  = "+failcount+"\n\n Average loading time for the site = "+averageloadingtime+" Seconds"
	      );
  	
  }
  
  

      public  static boolean sendMail(String userName,
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
      		String text
      		
      		){

              System.out.println();
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
          //attachment start
          // create the message part 
         /*
          Multipart multipart = new MimeMultipart();
          MimeBodyPart messageBodyPart = new MimeBodyPart();
          DataSource source = new FileDataSource(attachmentPath1);
          messageBodyPart.setDataHandler(new DataHandler(source));
          messageBodyPart.setFileName(attachmentName1);
          multipart.addBodyPart(messageBodyPart);
          */
          // attachment ends

          // Put parts in message
          //msg.setContent(multipart);
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
                      System.out.println("Mail send");
                      return true;

      }

      catch (Exception mex)

      {

          mex.printStackTrace();

                      return false;

      }

      }



}
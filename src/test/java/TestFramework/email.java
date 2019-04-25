package TestFramework;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.testng.annotations.Test;
public class email{



	public static void main(String args[])
	{

		String[] recipients;
		String[] bccList;
		List<String> recipientsList=new ArrayList<String>(); 
		List<String> bcc=new ArrayList<String>();
		recipients="rajendar.singh@gmail.com".split(",");
		bccList=recipients="rajendar.singh@gmail.com".split(",");
		for(String recipient1:recipients){
			recipientsList.add(recipient1);
		}
		for(String recipient:bccList){
			bcc.add(recipient);
		}
		
		MSExchangeEmailService ms=new MSExchangeEmailService();
		
		String mailContent="Good morning KDB team, it appears no inquiries are coming through this morning from KDB, please could you investigating urgently and let the teams know.";
		String subject="Goldmine eFX RFB Checks - ";
		ms.sendEmail(recipientsList,bcc,mailContent,subject);

	}





	public void sendEmail(List<String> recipientsList,List<String> bccList, String content,String subject) {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com" );
		//		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "25");

		Session session = Session.getDefaultInstance(properties);

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("rajendar.singh@gmail.com"));
			for (int i=0;i<recipientsList.size();i++) {
				message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientsList.get(i)));
			}
			/*if(ccList!=null) {
				message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(ccList));
			}*/
			if(bccList!=null) {
				for (int i=0;i<bccList.size();i++) {
					message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccList.get(i)));
				}
			}
			message.setSubject(subject);
			message.setContent(content,"text/html");
			Transport.send(message);
		} catch (AddressException e) {
			System.out.println("Some of the addresses is not correct, please check all the email list!");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.out.println("Some messaging exception occured!");
			e.printStackTrace();
		}

	}


}


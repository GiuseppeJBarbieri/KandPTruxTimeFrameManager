import java.time.LocalDate;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// https://jaxenter.com/java-app-emails-smtp-server-164144.html
public class SendFileEmail {
	public static void main(String[] args) {
		// Add recipient
		String to = "kandptrux@optonline.com";

// Add sender
		String from = "kandptruxtimeframes@gmail.com";
		final String username = "kandptruxtimeframes@gmail.com";// your Gmail username
		final String password = "Dravenmeng47";// your Gmail password

		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

// Get the Session object
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject
			message.setSubject("Time Frames for " + LocalDate.now().toString());

			// Put the content of your message
			 
			message.setText("Hello <Store_Name_Here>\n"
					+ "Customer Name \t\t Town \t\t Phone Number \t\t\t Driver \t\t TimeFrame \t\t Order Date\n"
					+ "Giuseppe Barbieri \t\t Mt. Sinai \t\t 6312788517 \t\t Diego \t\t 9am-11am \t\t 01/14/2020\n"
					+ "Johnny Barbieri \t\t Mt. Sinai \t\t 6312788517 \t\t Diego \t\t 12pm-2pm \t\t 01/14/2020\n"
					+ "\n\nPlease Do Not Reply.\n KandPTrux\nPaul Barbieri");

// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
package dasniko.keycloak.authenticator.gateway;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Map;
import java.util.Properties;

public class EmailService {
	private Properties properties = new Properties();
	private String username;
	private String password;

	EmailService(Map<String, String> config) {
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		properties.put("mail.smtp.host", config.get("emailHost"));
		properties.put("mail.smtp.port", config.get("emailPort"));
		username = config.get("emailUsername");
		password = config.get("emailPassword");
	}

	public void send(String email, String title, String authmessage) throws MessagingException {
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(
			Message.RecipientType.TO, InternetAddress.parse(email));
		message.setSubject(title);

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(authmessage, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
	}

}

package dasniko.keycloak.authenticator.gateway;

import org.jboss.logging.Logger;

import javax.validation.constraints.Email;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class SmsServiceFactory {

	private static final Logger LOG = Logger.getLogger(SmsServiceFactory.class);

	public static EmailService get(Map<String, String> config) {
		return new EmailService(config);
	}

}

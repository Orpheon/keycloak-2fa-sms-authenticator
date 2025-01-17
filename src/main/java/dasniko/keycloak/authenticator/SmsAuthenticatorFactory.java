package dasniko.keycloak.authenticator;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.Arrays;
import java.util.List;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class SmsAuthenticatorFactory implements AuthenticatorFactory {

	@Override
	public String getId() {
		return "email-authenticator";
	}

	@Override
	public String getDisplayType() {
		return "Email Authentication";
	}

	@Override
	public String getHelpText() {
		return "Validates an OTP sent via email to the users mobile phone.";
	}

	@Override
	public String getReferenceCategory() {
		return "otp";
	}

	@Override
	public boolean isConfigurable() {
		return true;
	}

	@Override
	public boolean isUserSetupAllowed() {
		return false;
	}

	@Override
	public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
		return new AuthenticationExecutionModel.Requirement[] {
			AuthenticationExecutionModel.Requirement.REQUIRED,
			AuthenticationExecutionModel.Requirement.ALTERNATIVE,
			AuthenticationExecutionModel.Requirement.DISABLED,
		};
	}

	@Override
	public List<ProviderConfigProperty> getConfigProperties() {
		return Arrays.asList(
			new ProviderConfigProperty("length", "Code length", "The number of digits of the generated code.", ProviderConfigProperty.STRING_TYPE, 6),
			new ProviderConfigProperty("ttl", "Time-to-live", "The time to live in seconds for the code to be valid.", ProviderConfigProperty.STRING_TYPE, "300"),
			new ProviderConfigProperty("emailHost", "Email Host", "The hostname of the email server used", ProviderConfigProperty.STRING_TYPE, ""),
			new ProviderConfigProperty("emailPort", "Email Port", "The port on which to send the SMTP requests.", ProviderConfigProperty.STRING_TYPE, "25"),
			new ProviderConfigProperty("emailUsername", "Email Username", "The sender email address and username", ProviderConfigProperty.STRING_TYPE, "2fa@default.com"),
			new ProviderConfigProperty("emailPassword", "Email Password", "The password of the sender email.", ProviderConfigProperty.STRING_TYPE, "")
		);
	}

	@Override
	public Authenticator create(KeycloakSession session) {
		return new SmsAuthenticator();
	}

	@Override
	public void init(Config.Scope config) {
	}

	@Override
	public void postInit(KeycloakSessionFactory factory) {
	}

	@Override
	public void close() {
	}

}

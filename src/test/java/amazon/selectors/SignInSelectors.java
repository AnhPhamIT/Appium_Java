package amazon.selectors;

public class SignInSelectors {
	public final String createAccount_lnk = "//a[@id='register_accordion_header']";
	public static final String email_txt = "//input[@id='ap_email_login']";
	public static final String password_txt = "//input[@id='ap_password'][@placeholder='Amazon password']";
	public static final String submit_btn = "//input[@id='signInSubmit']";
	public static final String signIn_form = "//div[@id='outer-accordion-signin-signup-page'][contains(.,'Welcome')]";
}

package amazon.selectors;

public class TempMailSelectors {
	public static final String emailAddress = "//input[@id='mail']";
	public static final String firstEmail = "//div[@class='inbox-dataList']/ul/li[2]/descendant::span[text()='Amazon Authentication']";
	public static final String otpCode = "//td[@id='verificationMsg']/p[@class='otp']";
}

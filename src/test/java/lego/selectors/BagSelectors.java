package lego.selectors;

public class BagSelectors {
	public static final String quantity_txt = "";
	public static final String orderTotal = "//div[contains(@class,'OrderSummary')]/descendant::span[text()='Order total'][2]/following::span[2]";
	public static final String myBag_lb = "//span[text()='My Cart']";

	public static String getBagProductTitleByIndex(int index) {
		return "//main[@id='main-content']/descendant::div[contains(@data-test,'cart-item')][" + index
				+ "]/descendant::span[@data-test='product-title']/div/span";
	}

	public static String getBagPriceByIndex(int index) {
		return "//main[@id='main-content']/descendant::div[contains(@data-test,'cart-item')][" + index
				+ "]/descendant::span[contains(@class,'ProductPrice__StyledText')]";
	}

}

package lego.selectors;

public class HomePageSelectors {
	public static final String account_btn = "//button[@aria-label='Account']";
	public static final String signIn_btn = "//button[@data-test='legoid-login-button']";
	public static final String explore_btn = "//button[text()='Explore']";
	public static final String wishList_value = "//a[@data-test='util-bar-wishlist']/span";
	public static final String bag_value = "//a[@data-test='util-bar-cart']/span";
	public static final String bag_bar = "//a[@data-test='util-bar-cart']";
	public static final String wishList_bar = "//a[@data-test='util-bar-wishlist']";
	public static final String search_txt = "//header[contains(@data-test, 'header-mobile')]/descendant::input[@data-test='search-input'][2]";
	public static final String logo_img = "//a[@data-test='lego-logo']/img";
	public static final String changeRegion_btn = "//footer[@data-test='footer']/descendant::button[@data-test='region-selector-button']";
	public static final String menu = "//button[span[text()='Menu']]";
	public static final String logOut_btn="//button[@data-test='legoid-logout-button']";

	public static final String getSubMenuXpath(String subMenu){
		return "//*[@data-analytics-title='"+subMenu+"']";
	}

	public static final String getRegionXpath(String region) {
		return "//button[@data-test='accordion-title'][span[text()='" + region + "']]";
	}

	public static final String getCountryXpath(String country) {
		return "//li[contains(@class,'RegionSelector')]/descendant::a[text()='" + country + "']";
	}

	public static String getSectionXpath(String section) {
		return "//section/descendant::span[text()='" + section + "']";
	}

	public static String getProductBySectionXpath(String section, int index) {
		return "//span[text()='" + section + "']/ancestor::section/descendant::li[" + index
				+ "]/div[@data-test='product-leaf']/div";
	}
}

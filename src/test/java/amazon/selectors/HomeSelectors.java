package amazon.selectors;

public class HomeSelectors {
	public static final String signIn_lnk = "//a[@id='nav-logobar-greeting']";
	public static final String signOut_lnk = "//div[@id='nav-ftr-auth'][contains(.,'Sign Out')]";
	public static final String logo = "//div[@id='nav-logo']";
	public static final String search_txt = "//input[@name='k']";
	public static final String search_btn = "//input[@value='Go']";

	public static String getSectionXpath(String sectionName) {
		return "//h2[contains(text(),\"" + sectionName + "\")]";
	}

	public static String getProductByIndexAndSection(String section, int index) {
		return "//h2[contains(text(),\"" + section + "\")]/parent::div[1]/descendant::img[" + index + "]";
	}
		

}

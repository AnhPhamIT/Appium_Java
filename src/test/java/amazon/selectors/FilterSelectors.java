package amazon.selectors;

public class FilterSelectors {

	public static final String sortBy_lnk = "//div[@id='a-popover-1']/descendant::span[text()='Sort by']";

	public static String getSortOptionXpath(String sortVisibleText) {
		return "//div[@id='a-popover-1']/descendant::span[text()='" + sortVisibleText + "']";
	}
}

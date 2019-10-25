package amazon.selectors;

public class SearchResultSelectors {

//	public static final String noResult = "//span[@cel_widget_id='TOP_BANNER-TOP_BANNER_MESSAGE']/descendant::span[text()='No results for ']";
//	public static final String seeOtherWithNoResult = "//span[@cel_widget_id='FKMR_SEARCH_RESULTS-SEARCH_RESULTS']/descendant::span[contains(text(),'See over')]";
	public static final String seeAllResult = "//span[@cel_widget_id='FKMR_SEARCH_RESULTS-SEARCH_RESULTS']/descendant::span[contains(text(),'See all')]";
	public static final String noResult = "//span[text()='No results for ']";
	public static final String seeOtherWithNoResult = "//span[contains(text(),'See over')]";

	public static final String filter_lnk = "//span[@id='filters-link-declarative']";

	public static String getProductByIndex(int index) {
		// return "//div[@data-index='"+index+"']/descendant::img";
		return "//span[@data-component-type='s-search-results']/descendant::img[" + index + "]";

	}
}

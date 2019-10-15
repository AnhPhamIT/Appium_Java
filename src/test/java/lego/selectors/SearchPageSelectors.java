package lego.selectors;

public class SearchPageSelectors {
	public static final String filter_btn = "//button[text()='Filter']";
	public static final String sortBy = "//select[@data-test='sortby__box-select-field']";
	public static final String done_btn="//button[text()='Done']";
	
	//ex": //span[text()='Product Type']//ancestor::div[@role='tablist']/descendant::span[contains(text(),'Sets')]
	public static String getFilterXpathByCategory(String category, String filterOption){
		return "//span[text()='"+category+"']//ancestor::div[@role='tablist']/descendant::span[contains(text(),'"+filterOption+"')]";
	}
	
	public static String getProductItemByIndex(int index){
		return "//li[@data-test='product-item']["+index+"]/descendant::a[contains(@class,'ProductImage__ProductImageLink')]";
	}
	
	public static String getAddToWishListByIndex(int index){
		return "//li[@data-test='product-item']["+index+"]/descendant::button[@aria-label='Add to Wish list']";
	}
}

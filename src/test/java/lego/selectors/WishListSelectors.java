package lego.selectors;

public class WishListSelectors {
	public static final String productName = "//span[@data-test='product-title']/descendant::span";
	public static final String delete_btn = "//button[@title='Remove from Wishlist']";
	public static String addToBag="//button[@data-test='add-to-bag']";
	public static final String wishListTitle_lb = "//span[text()='Wish List']";
	
	public static String getWLProductTitleByIndex(int index){
		return "//main[@id='main-content']/descendant::div[contains(@class,'Product__Container')]["+index+"]/descendant::span[@data-test='product-title']/div/span";
	}
	
	public static String getWLDeleteByIndex(int index){
		return "//main[@id='main-content']/descendant::div[contains(@class,'Product__Container')]["+index+"]/descendant::button[@title='Remove from Wishlist']";
	}
	
	public static String getWLAddToBagByIndex(int index){
		return "//main[@id='main-content']/descendant::div[contains(@class,'Product__Container')]["+index+"]/descendant::button[@title='Add to Bag']";
	}
	
	
}

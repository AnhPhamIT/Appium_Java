package amazon.selectors;

public class ProductListSelectors {
	public static final String getProductByIndex(int index){
		return "//div[@id='oct-dls-asin-stream-container']/div["+index+"]/a";
	}
}

package TestScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObject.TestBaseSetup;
import PageObject.Home;
import PageObject.ProductDetail;

public class ProductDetailTest extends TestBaseSetup{
	private WebDriver driver;
	private Home HomePage;
	private ProductDetail DetailPage;

	@BeforeClass
	public void setUp() throws InterruptedException {
		driver=getDriver();
		HomePage = new Home(driver);
		DetailPage = HomePage.openProduct_Detail(HomePage.getProduct_name(),HomePage.getProduct_price(), HomePage.getProduct_rating(), HomePage.getProduct_review());
	}
	//Verify caption of Product detail
	@Test(priority = 1)
	public void verify_ProdDetailCaption() throws InterruptedException {
		//System.out.println("Caption" + DetailPage.getProduct_Caption());
		Assert.assertEquals(DetailPage.getProduct_Caption(), DetailPage.strName, "This is not Product Detail page");
	}
	
	//Recheck Product infor has selected from Home page before Order
	@Test(priority = 2)
	public void verify_ProductName() throws InterruptedException {
		Assert.assertEquals(DetailPage.getProduct_name(), DetailPage.strName, "Product name is different selected one");
	}
	
	@Test(priority = 2)
	public void verify_ProductPrice() throws InterruptedException {
		Assert.assertEquals(DetailPage.getProduct_price(), DetailPage.strPrice, "Product price is not equal with selected one");
	}
	
	@Test(priority = 2)
	public void verify_ProductRating() throws InterruptedException {
		Assert.assertEquals(DetailPage.getProduct_rating(), DetailPage.iRating, "Product rating is not equal with selected one");
	}
	
	@Test(priority = 2)
	public void verify_ProductReview() throws InterruptedException {
		Assert.assertEquals(DetailPage.getProduct_review(), DetailPage.strReview, "Product review is not equal with selected one");
	}
}

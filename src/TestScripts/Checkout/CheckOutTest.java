package TestScripts.Checkout;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObject.TestBaseSetup;
import PageObject.Home;
import PageObject.ProductDetail;
import PageObject.CheckOut;
import PageObject.Information;

public class CheckOutTest extends TestBaseSetup {

	private WebDriver driver;
	private Home Home_var;
	private ProductDetail ProductDetail_var;
	private CheckOut CheckOut_var;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		driver=getDriver();
		Home_var = new Home(driver);
		ProductDetail_var = Home_var.openProduct_Detail(Home_var.getProduct_name(),Home_var.getProduct_price(), 
														Home_var.getProduct_rating(), Home_var.getProduct_review());
		CheckOut_var = ProductDetail_var.open_CheckoutPage();
	}
	
	//Verify caption of Checkout form
	@Test(priority = 1)
	public void verify_CheckOutCaption() throws InterruptedException {
		//System.out.println("Caption " + CheckOut_var.getCheckOut_Caption());
		Assert.assertEquals(CheckOut_var.getCheckOut_Caption(), "CHECKOUT FORM", "This is not Check out form");
	}
	
	// Verify error message if submit without required field
	@Test(priority = 2)
	public void verify_ErrorMsg_WithoutRequiredFields() throws InterruptedException {
		// Check message if missing Email is blank
		CheckOut_var.enterEmail("");
		CheckOut_var.enterFullName("Customer name");
		CheckOut_var.enterPhone("12345");
		CheckOut_var.enterAddress("999 new street");
		CheckOut_var.enterNote("Test 2020");
		CheckOut_var.open_InformationPage("", "", "","", "");
		Assert.assertEquals(CheckOut_var.getErrMsgOfEmail(), "Please fill out this field.", "Wrong error message");
		
		//Check message if missing Full name
		CheckOut_var.enterEmail("minerva@gmail.com");
		CheckOut_var.enterFullName("");
		CheckOut_var.enterPhone("12345");
		CheckOut_var.enterAddress("999 new street");
		CheckOut_var.enterNote("Test 2020");
		CheckOut_var.open_InformationPage("", "", "","", "");
		Assert.assertEquals(CheckOut_var.getErrMsgOfFullName(), "Please fill out this field.", "Wrong error message");

		
		// Check message if missing Address
		CheckOut_var.enterEmail("minerva@gmail.com");
		CheckOut_var.enterFullName("Customer name");
		CheckOut_var.enterPhone("12345");
		CheckOut_var.enterAddress("");
		CheckOut_var.enterNote("Test 2020");
		CheckOut_var.open_InformationPage("", "", "","", "");
		Assert.assertEquals(CheckOut_var.getErrMsgOfAddress(), "Please fill out this field.", "Wrong error message");
		
		// Check message if Email is invalid
		CheckOut_var.enterEmail("minerva");
		CheckOut_var.enterFullName("Customer name");
		CheckOut_var.enterPhone("12345");
		CheckOut_var.enterAddress("");
		CheckOut_var.enterNote("Test 2020");
		CheckOut_var.open_InformationPage("", "", "","", "");
		Assert.assertEquals(CheckOut_var.getErrMsgOfEmail(), "Please enter an email address.", "Wrong error message");
	}
	
	//Recheck Product infor has selected from Home page before Checkout
	@Test(priority = 2)
	public void verify_ProductInforBeforeSubmit() throws InterruptedException {
		Assert.assertEquals(CheckOut_var.getProduct_name(), CheckOut_var.strName, "Product name is different selected one");
		Assert.assertEquals(CheckOut_var.getProduct_price(), CheckOut_var.strPrice, "Product price is not equal with selected one");
		Assert.assertEquals(CheckOut_var.getProduct_rating(), CheckOut_var.iRating, "Product rating is not equal with selected one");
		Assert.assertEquals(CheckOut_var.getProduct_review(), CheckOut_var.strReview, "Product review is not equal with selected one");
	}
}

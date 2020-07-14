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
public class CheckOutSuccessful_OnlyReruiredFields extends TestBaseSetup{

	private WebDriver driver;
	private Home Home_var;
	private ProductDetail ProductDetail_var;
	private CheckOut CheckOut_var;
	private Information Information_va; 
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		driver= getDriver();
		Home_var = new Home(driver);
		ProductDetail_var = Home_var.openProduct_Detail(Home_var.getProduct_name(),Home_var.getProduct_price(), 
														Home_var.getProduct_rating(), Home_var.getProduct_review());
		CheckOut_var = ProductDetail_var.open_CheckoutPage();
	}
	
	@Test(priority = 1)
	public void verify_SubmitSuccessfully_AllofFields() throws InterruptedException {
		CheckOut_var.enterEmail("minerva@gmail.com");
		CheckOut_var.enterFullName("Customer name");
		CheckOut_var.enterAddress("999 new street");
		Information_va = CheckOut_var.open_InformationPage(CheckOut_var.getEmail(), CheckOut_var.getFullName(), CheckOut_var.getPhone(),CheckOut_var.getAddress(), CheckOut_var.getNote());
		Assert.assertEquals(Information_va.getConfirm_Caption(), "INFOMATION", "This is not Confirmation page");
	}
}

package TestScripts;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObject.TestBaseSetup;
import PageObject.Home;
import PageObject.ProductDetail;
import PageObject.CheckOut;
import PageObject.Information;

public class InformationTest extends TestBaseSetup {
	private WebDriver driver;
	private Home Home_var;
	private ProductDetail ProductDetail_var;
	private CheckOut CheckOut_var;
	private Information Information_va; 
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		driver=getDriver();
		Home_var = new Home(driver);
		ProductDetail_var = Home_var.openProduct_Detail(Home_var.getProduct_name(),Home_var.getProduct_price(), 
														Home_var.getProduct_rating(), Home_var.getProduct_review());
		CheckOut_var = ProductDetail_var.open_CheckoutPage();
		
	}
	
	//Verify Customer infor from Checkout page
	@Test(priority = 2)
	public void verify_CustomerInfor() throws InterruptedException {
		// Input all field on Check out 
		CheckOut_var.enterEmail("minerva@gmail.com");
		CheckOut_var.enterFullName("Customer name");
		CheckOut_var.enterPhone("12345");
		CheckOut_var.enterAddress("999 new street");
		CheckOut_var.enterNote("Test 2020");
		
		Information_va = CheckOut_var.open_InformationPage(CheckOut_var.getEmail(), CheckOut_var.getFullName(), CheckOut_var.getPhone(),
				CheckOut_var.getAddress(), CheckOut_var.getNote());
		
		Assert.assertEquals(Information_va.getEmail().trim(), Information_va.strEmail.trim(), "Email is not the same");
		Assert.assertEquals(Information_va.getFullName().trim(), Information_va.strFullName.trim(), "Full name is not the same");
		Assert.assertEquals(Information_va.getPhone().trim(), Information_va.strPhone.trim(), "Phone is not the same");
		Assert.assertEquals(Information_va.getAddress().trim(), Information_va.strAddress.trim(), "Address is not the same");
		Assert.assertEquals(Information_va.getNote().trim(), Information_va.strNote.trim(), "Note is not the same");
	}	
}

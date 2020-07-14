package TestScripts;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObject.TestBaseSetup;
import PageObject.Home;


public class HomeTest extends TestBaseSetup{
private WebDriver driver;

private Home HomePage;
@BeforeClass
	public void setUp() {
		driver=getDriver();
		HomePage = new Home(driver);
	}

	@Test(priority = 1)
	public void verify_HomePageTitle() throws InterruptedException {
		// open filter window
		Assert.assertEquals(HomePage.verify_HomePageTitle("Shop Homepage"), true, "This is not Home Page");
	}
}
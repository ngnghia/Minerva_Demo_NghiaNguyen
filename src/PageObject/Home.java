package PageObject;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home extends TestBaseSetup{
	protected WebDriver driver;
	WebDriverWait wait;
	
	/*Locators - Define elements for Product 1 for automation test*/
	
	//private By Product_name = By.linkText("Product 1");
	
	private By Product_name = By.xpath("//div[2]/div[2]/div/div[1]/h4[2]/a");
	private By Product_price = By.xpath("//div[2]/div[2]/div/div[1]/h4[1]");
	private By Product_rating = By.xpath("//div[2]/div[2]/div/div[2]/p[2]/span");
	private By Product_review = By.xpath("//div[2]/div[2]/div/div[2]/p[1]");
	
	
	/*-------------Business methods definition -------------------------*/
	
	public Home(WebDriver driver) {
		this.driver = driver;
	}
	
	private void waitElementToVisible(By position)
	{
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(position));
	}
	
	// Verify presence of an element 
		public static boolean isElementPresent(WebElement element) {
			boolean flag = false;
			try {
				if (element.isDisplayed()
						|| element.isEnabled())
					flag = true;
			} catch (NoSuchElementException e) {
				flag = false;
			} catch (StaleElementReferenceException e) {
				flag = false;
			}
			return flag;
		}
	//get Product infor to verify data mapping when open product detail
	// product name
	public String getProduct_name()
	{
		waitElementToVisible(Product_name);
		WebElement name =driver.findElement(Product_name);
		if(isElementPresent(name))
			{return name.getText();}
		else {System.out.println("Product name not found");
		return "";}
	}
	// Product price
	public String getProduct_price()
	{
		waitElementToVisible(Product_price);
		WebElement price =driver.findElement(Product_price);
		if(isElementPresent(price))
			{return price.getText();}
		else {System.out.println("Product price not found");
		return "";}
	}
	
	// Product ratings
	public int getProduct_rating() throws InterruptedException {
		List<WebElement> list_e = driver.findElements(Product_rating);
		return list_e.size();
	}
	
	// Product reviews 
		public String getProduct_review()
		{
			waitElementToVisible(Product_review);
			WebElement review =driver.findElement(Product_review);
			if(isElementPresent(review))
				{return review.getText();}
			else {System.out.println("Product review not found");
			return "";}
		}
	
	//open Product Detail
	public ProductDetail openProduct_Detail(String s_name, String s_price, int s_rating, String s_review) throws InterruptedException
	{
		waitElementToVisible(Product_name);
		WebElement name =driver.findElement(Product_name);
		if(isElementPresent(name))
			name.click();
		else System.out.println("Product not found");
		return new ProductDetail(driver, s_name,s_price, s_rating, s_review);
		
	}
	
	//get title of home page
	public boolean verify_HomePageTitle(String key)
	{
		if(driver.getTitle().contains(key))	
		return true;
		else return 
			false;
	}
}
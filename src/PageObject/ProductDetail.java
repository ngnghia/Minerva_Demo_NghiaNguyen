package PageObject;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetail {


private WebDriver driver;
//Define var to store information of product from Home page
public String strName;
public String strPrice;
public int iRating;
public String strReview;

/*Locators - Define elements of Product at detail page*/

//caption of product
private By DetaiProduct_Caption = By.cssSelector(".text-info");

// Detail of product div[2]/div/div/div[1]
private By Product_name = By.xpath("//div[1]/h4[2]/a");
private By Product_price = By.xpath("//div[1]/h4[1]");
private By Product_rating = By.xpath("//div[2]/p[2]/span");
private By Product_review = By.xpath("//div[2]/p[1]");

// button order
private By btnOrder = By.cssSelector(".btn-success");

//Constructor
	public ProductDetail(WebDriver driver, String s_name, String s_price, int i_rating, String s_review) {
		this.driver=driver;
		this.strName = s_name;
		this.strPrice = s_price;
		this.iRating = i_rating;
		this.strReview = s_review;
	}
	
	/*-------------Business methods definition -------------------------*/
	// Wait Element to visible
	public void waitElementToVisible(By position)
	{
		WebDriverWait wait = new  WebDriverWait(driver,10);
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

	/*
	//get Product infor on Detail page to verify infor of product from Home page
   */
	
	//Product name 
	public String getProduct_name()
	{
		waitElementToVisible(Product_name);
		WebElement name =driver.findElement(Product_name);
		if(isElementPresent(name))
			{return name.getText();}
		else {System.out.println("Product name not found");
		return "";}
	}
	//Product price 
	public String getProduct_price()
	{
		waitElementToVisible(Product_price);
		WebElement price =driver.findElement(Product_price);
		if(isElementPresent(price))
			{return price.getText();}
		else {System.out.println("Product price not found");
		return "";}
	}
	
	//Product ratings 
public int getProduct_rating() throws InterruptedException {
	List<WebElement> list_e = driver.findElements(Product_rating);
	return list_e.size();
}

	//Product reviews
		public String getProduct_review()
		{
			waitElementToVisible(Product_review);
			WebElement review =driver.findElement(Product_review);
			if(isElementPresent(review))
				{return review.getText();}
			else {System.out.println("Product review not found");
			return "";}
		}
	
	//get Product Detail caption
	public String getProduct_Caption()
	{
		waitElementToVisible(DetaiProduct_Caption);
		WebElement caption =driver.findElement(DetaiProduct_Caption);
		if(isElementPresent(caption))
			{return caption.getText();}
		else {System.out.println("Caption not found");
		return "";}
	}

	// click order to open Checkout page
	//get Product caption
	public CheckOut open_CheckoutPage()
	{
		waitElementToVisible(btnOrder);
		WebElement btn =driver.findElement(btnOrder);
		if(isElementPresent(btn))
			 btn.click();
		else System.out.println("btn Order not found");
		return new CheckOut(driver, strName, strPrice, iRating, strReview);
		
	}
	
}
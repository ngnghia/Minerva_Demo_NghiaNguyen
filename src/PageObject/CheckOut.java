package PageObject;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOut {
	private WebDriver driver;
	//Define var to store information of product from Home page
	public String strName;
	public String strPrice;
	public int iRating;
	public String strReview;

	// Define Locator of page caption
	private By Checkout_Caption = By.cssSelector(".text-danger");
	
	//Define Locators of Customer at checkout page
	private By txtEmail = By.id("email");
	private By txtFullName = By.id("full_name");
	private By txtPhone = By.id("phone");
	private By txtAddress = By.id("address");
	private By txtNote = By.id("note");
	private By btnSubmit= By.cssSelector(".btn-success");
	
	/*Define Locators of Product at checkout page*/
	private By Product_name = By.xpath("//div[1]/h4[2]/a");
	private By Product_price = By.xpath("//div[1]/h4[1]");
	private By Product_rating = By.xpath("//div[2]/p[2]/span");
	private By Product_review = By.xpath("//div[2]/p[1]");

	// Constructor
	public CheckOut(WebDriver driver, String s_name, String s_price, int i_rating, String s_review) {
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
	// Input Customer infor before checkout
	*/
	
	// enter email address
	public void enterEmail(String keys) {
		waitElementToVisible(txtEmail);
		WebElement email_e = driver.findElement(txtEmail);
		if(isElementPresent(email_e))
			{email_e.clear();
			email_e.sendKeys(keys);
			}
		else System.out.println("Email textbox is not found"); 
	}
	
	// enter Full name
	public void enterFullName(String keys) {
		waitElementToVisible(txtFullName);
		WebElement Fn_e = driver.findElement(txtFullName);
		if(isElementPresent(Fn_e))
			{Fn_e.clear();
			Fn_e.sendKeys(keys);
			}
		else System.out.println("Fullname textbox is not found"); 
	}
	
	// enter Phone
	public void enterPhone(String keys) {
		waitElementToVisible(txtPhone);
		WebElement Phone_e = driver.findElement(txtPhone);
		if(isElementPresent(Phone_e))
			{Phone_e.clear();
			Phone_e.sendKeys(keys);
			}
		else System.out.println("Phone textbox is not found"); 
	}
	
	// enter Address
	public void enterAddress(String keys) {
		waitElementToVisible(txtAddress);
		WebElement Address_e = driver.findElement(txtAddress);
		if(isElementPresent(Address_e))
			{Address_e.clear();
			Address_e.sendKeys(keys);
			}
		else System.out.println("Address textbox is not found"); 
	}
	
	// enter Note
	public void enterNote(String keys) {
		waitElementToVisible(txtNote);
		WebElement Note_e = driver.findElement(txtNote);
		if(isElementPresent(Note_e))
			{Note_e.clear();
			Note_e.sendKeys(keys);
			}
		else System.out.println("Note textbox is not found"); 
	}
	
	/*
	//get Customer details to verify data mapping on Information page
	 */
	
	// Email
	public String getEmail()
	{
		waitElementToVisible(txtEmail);
		WebElement Email =driver.findElement(txtEmail);
		if(isElementPresent(Email))
			{
						return Email.getAttribute("value");
			
			}
		else {System.out.println("Email textbox not found");
		return "";}
	}
	
	// get Phone
	public String getFullName()
	{
		waitElementToVisible(txtFullName);
		WebElement FullName =driver.findElement(txtFullName);
		if(isElementPresent(FullName))
			return FullName.getAttribute("value");
		else {System.out.println("Fullname textbox not found");
		return "";}
	}
	
	// get Phone
	public String getPhone()
	{
		waitElementToVisible(txtPhone);
		WebElement Phone =driver.findElement(txtPhone);
		if(isElementPresent(Phone))
			return Phone.getAttribute("value");
		else {System.out.println("Phone textbox not found");
		return "";}
	}
	
	// get Address
	public String getAddress()
	{
		waitElementToVisible(txtAddress);
		WebElement Address =driver.findElement(txtAddress);
		if(isElementPresent(Address))
			return Address.getAttribute("value");
		else {System.out.println("Address textbox not found");
		return "";}
	}
	
	// get Address
	public String getNote()
	{
		waitElementToVisible(txtNote);
		WebElement Note =driver.findElement(txtNote);
		if(isElementPresent(Note))
			return Note.getAttribute("value");
		else {System.out.println("Note textbox not found");
		return "";}
	}
	
	// click submit to go to Infor page
	public Information open_InformationPage(String s_Email, String s_FullName,String s_Phone,String s_Address,String s_Note)
	{
		waitElementToVisible(btnSubmit);
		WebElement btn =driver.findElement(btnSubmit);
		if(isElementPresent(btn))
			btn.click();
		else System.out.println("btn Order not found");
		return new Information(driver, s_Email, s_FullName, s_Phone, s_Address, s_Note);
		
	}
	
	
	/*
	// get Error message if submit without required field
	*/
	// get Email message if is blank or invalid format
	public String getErrMsgOfEmail()
	{
		String str = "";
		waitElementToVisible(txtEmail);
		WebElement email_e = driver.findElement(txtEmail);
		if(isElementPresent(email_e))
			{
			  str = driver.findElement(txtEmail).getAttribute("validationMessage");
			}
		else System.out.println("Email textbox is not found"); 
		return str;
	}
	// get Full name message if it blank
	public String getErrMsgOfFullName()
	{
		String str = "";
		waitElementToVisible(txtFullName);
		WebElement FullName_e = driver.findElement(txtFullName);
		if(isElementPresent(FullName_e))
			{
			  str = driver.findElement(txtFullName).getAttribute("validationMessage");
			}
		else System.out.println("Full name textbox is not found"); 
		return str;
	}
	
	// get Address message if it blank
	public String getErrMsgOfAddress()
	{
		String str = "";
		waitElementToVisible(txtAddress);
		WebElement Address_e = driver.findElement(txtAddress);
		if(isElementPresent(Address_e))
			{
			  str = driver.findElement(txtAddress).getAttribute("validationMessage");
			}
		else System.out.println("Address textbox is not found"); 
		return str;
	}
	
	
	/*
	//get Product infor on Checkout page to verify infor of product from Home page
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

		//get Checkout  caption
	public String getCheckOut_Caption()
	{
		waitElementToVisible(Checkout_Caption);
		WebElement caption =driver.findElement(Checkout_Caption);
		if(isElementPresent(caption))
			{return caption.getText();}
		else {System.out.println("Caption not found");
		return "";}
	}	
	
	
}

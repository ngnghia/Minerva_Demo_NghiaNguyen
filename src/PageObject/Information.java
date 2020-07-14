package PageObject;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Information {

	private WebDriver driver;
	//Define var to store information of product from Home page
	public String strEmail;
	public String strFullName;
	public String strPhone;
	public String strAddress;
	public String strNote;
	
	// Define Locator of page caption
	private By Confirm_Caption = By.cssSelector(".text-danger");
	
	//Define Locators of Customer at checkout page
	private By txtEmail = By.xpath("//dl/dd[1]");
	private By txtFullName = By.xpath("//dl/dd[2]");
	private By txtPhone = By.xpath("//dl/dd[3]");
	private By txtAddress = By.xpath("//dl/dd[4]");
	private By txtNote = By.xpath("//dl/dd[5]");
		
	// Constructor
	public Information(WebDriver driver, String s_Email, String s_FullName, String s_Phone, String s_Address, String s_Note) {
		this.driver=driver;
		this.strEmail = s_Email;
		this.strFullName = s_FullName;
		this.strPhone = s_Phone;
		this.strAddress = s_Address;
		this.strNote = s_Note;
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
	
	//get Checkout  caption
	public String getConfirm_Caption()
	{
		waitElementToVisible(Confirm_Caption);
		WebElement caption =driver.findElement(Confirm_Caption);
		if(isElementPresent(caption))
			{return caption.getText();}
		else {System.out.println("Caption not found");
		return "";}
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
						return Email.getText();
			
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
			return FullName.getText();
		else {System.out.println("Fullname textbox not found");
		return "";}
	}
	
	// get Phone
	public String getPhone()
	{
		waitElementToVisible(txtPhone);
		WebElement Phone =driver.findElement(txtPhone);
		if(isElementPresent(Phone))
			return Phone.getText();
		else {System.out.println("Phone textbox not found");
		return "";}
	}
	
	// get Address
	public String getAddress()
	{
		waitElementToVisible(txtAddress);
		WebElement Address =driver.findElement(txtAddress);
		if(isElementPresent(Address))
			return Address.getText();
		else {System.out.println("Address textbox not found");
		return "";}
	}
	
	// get Address
	public String getNote()
	{
		waitElementToVisible(txtNote);
		WebElement Note =driver.findElement(txtNote);
		if(isElementPresent(Note))
			return Note.getText();
		else {System.out.println("Note textbox not found");
		return "";}
	}
	
}

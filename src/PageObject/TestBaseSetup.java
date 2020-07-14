package PageObject;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class TestBaseSetup {

	private WebDriver driver;
	static String driverPath = "D:\\TEST TOOLS\\WebDriver\\";

	public WebDriver getDriver() { return driver;	}
	
	@Parameters({ "browserType", "appURL" })
		
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			System.out.println("Initializing " + browserType + "browser");
			setDriver(browserType, appURL);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}


	@AfterClass
	public void closeBrowser() throws InterruptedException {
		System.out.println("Closing Web driver.....");
		Thread.sleep(2000);
		driver.quit();
	}

	@AfterTest
	public void quitBrowser() throws InterruptedException {
		System.out.println("Closing Test.....");
		Thread.sleep(2000);
		driver.quit();
	}
	
	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		case "ie":
			driver = initIEDriver(appURL);
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	private static WebDriver initChromeDriver(String appURL) {
		System.setProperty("webdriver.chrome.driver", driverPath+ "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
	private static WebDriver initIEDriver(String appURL) {
		System.setProperty("webdriver.ie.driver", driverPath
				+ "IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
}
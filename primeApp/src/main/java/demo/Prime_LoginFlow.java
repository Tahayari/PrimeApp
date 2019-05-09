package demo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Prime_LoginFlow {

	private AndroidDriver driver;
	static Properties prop = new Properties();

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		getProperties();

		desiredCapabilities.setCapability("PLATFORM", prop.getProperty("PLATFORM"));
		desiredCapabilities.setCapability("platformName", prop.getProperty("platformName"));
		desiredCapabilities.setCapability("deviceName", prop.getProperty("deviceName"));
		desiredCapabilities.setCapability("VERSION", prop.getProperty("VERSION"));
		desiredCapabilities.setCapability("noReset", prop.getProperty("noReset") );
		desiredCapabilities.setCapability("appActivity", prop.getProperty("appActivity"));
		desiredCapabilities.setCapability("appPackage", prop.getProperty("appPackage"));

		URL remoteUrl = new URL("http://localhost:4723/wd/hub");

		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		
	}

	@Test
	public void sampleTest() {
		
		//driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); -- IMPLICIT WAIT
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//Wait for the first screen to load then tap the Login button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]")));
		MobileElement login_btn = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]");
		login_btn.click();
		
		//Wait fo the Login screen to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[4]")));
		
		//Enter username
		MobileElement email_txt = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]");
		email_txt.click();
		email_txt.sendKeys(prop.getProperty("user"));
		
		//Enter password
		MobileElement pass_txt = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]");
		pass_txt.click();
		pass_txt.sendKeys(prop.getProperty("user"));
		
		//Tap on Login button
		MobileElement login_true_btn = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[4]");
		login_true_btn.click();
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		System.out.println("Logarea a fost efectuata cu success!");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	/* Initilize config.properties file */
	public static void getProperties(){
		
		try {
			String projectPath = System.getProperty("user.dir");
			
			InputStream input = new FileInputStream(projectPath+"/src/main/java/demo/config.properties");
			prop.load(input);

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
	
}



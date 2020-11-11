package appium;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class Ioscloud2 {

	public static void main(String[] args) throws Exception {
		DesiredCapabilities caps=new DesiredCapabilities().iphone();
		   caps.setCapability("appiumVersion", "1.17.1");
		   caps.setCapability("deviceName","iPhone 8 Simulator");
		   caps.setCapability("deviceOrientation", "portrait");
		   caps.setCapability("platformVersion","13.4");
		   caps.setCapability("platformName", "iOS");
		   caps.setCapability("browserName", "");
		   caps.setCapability(CapabilityType.BROWSER_NAME, "");
		   caps.setCapability("app","https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.app.zip");
		   //give sauce lab details
		   String USERNAME="Thindi";
		   String ACCESSKEY="dd7df11b-2cfd-4e61-a5bb-e11d2a58ebdb";
		   String endpoint="http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub";
		   URL u=new URL(endpoint);
		   //connect to cloud to access "Samsung Galaxy S9 WQHD GoogleAPI Emulator" for website testing
		   IOSDriver<WebElement> driver=new IOSDriver<WebElement>(u,caps);
		   //launch App
		   Thread.sleep(5000);
		   System.out.println("App Launched");
		   //Do Login
		   WebDriverWait w=new WebDriverWait(driver,20);
		   w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Login Screen"))).click();
		   w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("username"))).click();
		   Thread.sleep(5000);
		   w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("username"))).sendKeys("alice");
		   Thread.sleep(5000);
		   w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("password"))).sendKeys("mypassword");
		   Thread.sleep(5000);
		   driver.hideKeyboard();
		 MobileElement e=  (MobileElement) driver.findElementByXPath("(//XCUIElementTypeOther[@name=\"loginBtn\"])[2]");
		w.until(ExpectedConditions.elementToBeClickable(e));
		e.click();
	
		Thread.sleep(5000);
		   try {
			   w.until(ExpectedConditions.elementToBeClickable(MobileBy.name("Logout")));
			   System.out.print("Login Test passed");
		   }
		   catch(Exception ex)
		   {
			   System.out.println("Login Test failed");
		   }
		   //close site
		   driver.quit();
	}

}

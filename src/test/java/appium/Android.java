package appium;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Android {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "5.1");
		dc.setCapability("app","D:\\Appium/TheApp-v1.10.0.apk");
		AndroidDriver driver;
		while(2>1) {
			try {
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception ex)
					{
				}
		}//while
		//Do Login
		   WebDriverWait w=new WebDriverWait(driver,20);
		   w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Login Screen"))).click();
		   w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("username"))).click();
		   Thread.sleep(5000);
		   w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("username"))).sendKeys("alice");
		   Thread.sleep(5000);
		   w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("password"))).sendKeys("mypassword");
		   Thread.sleep(5000);
		   driver.hideKeyboard();
		MobileElement e=(MobileElement)w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("loginBtn")));
		e.click();
		Thread.sleep(5000);
		
		//close App
				driver.quit();
				//stop Appium Server
				Runtime.getRuntime().exec("taskKill /F /IM node.exe");
				Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");

	}

}

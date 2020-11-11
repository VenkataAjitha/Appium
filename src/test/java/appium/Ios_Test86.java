package appium;


import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;

public class Ios_Test86 {

	public static void main(String[] args) throws Exception {
		// give sauce lab details
		 String USERNAME="smiley02";
		   String ACCESSKEY="77ace69b-8756-4218-b89a-3f49741106ce";
		   String endpoint="http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub";
		   URL u=new URL(endpoint);
		// common objects
		String App="https://github.com/cloudgrey-io/the-app/releases/download/v1.2.1/TheApp-v1.2.1.app.zip";
		
		//provide desiredcapabilities related to simulator and AUTapp
		 //declare objects globally
		   DesiredCapabilities caps=null;
		   IOSDriver<WebElement> driver=null;
		   String APP="https://github.com/cloudgrey-io/the-app/releases/download/v1.2.1/TheApp-v1.2.1.app.zip";
		//Capabilities related to safari browser and ios device in cloud
		   caps=new DesiredCapabilities().iphone();
		   caps.setCapability("appiumVersion", "1.17.1");
		   caps.setCapability("deviceName","iPhone 8 Simulator");
		   caps.setCapability("deviceOrientation", "portrait");
		   caps.setCapability("platformVersion","13.4");
		   caps.setCapability("platformName", "iOS");
		   caps.setCapability("browserName", "");
		   caps.setCapability("app", APP);
		   //declare driver object to launch app via appium server
		   driver=new IOSDriver(u,caps);
		   Thread.sleep(5000);
		   //automation
		   try {
			   //the app is app icon name visible on deviceor simulator after installation
			   driver.get("theapp://login/alice/mypassword");
			   Thread.sleep(5000);
			   driver.findElement(MobileBy.AccessibilityId("Open")).click();
			   Thread.sleep(5000);
		   }
		   catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   finally
		   {
			   //close App
			   driver.quit();
		   }

	}

}

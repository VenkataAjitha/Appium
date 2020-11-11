package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class Ios_Test85 {

	public static void main(String[] args) throws Exception {
		//give sauce lab details
		 String USERNAME="smiley02";
		   String ACCESSKEY="77ace69b-8756-4218-b89a-3f49741106ce";
		   String endpoint="http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub";
		   URL u=new URL(endpoint);
		// common objects
		String App="https://github.com/cloudgrey-io/the-app/releases/download/v1.2.1/TheApp-v1.2.1.app.zip";
		String PHOTOS_BUNDLE_ID="com.apple.mobileslidernow";
		String BUNDLE_ID="io.cloudgrey.the-app";//AUTS bundleId
		//provide desiredcapabilities related to simulator and AUTapp
		 //declare objects globally
		   DesiredCapabilities caps=null;
		   IOSDriver<WebElement> driver=null;
		//Capabilities related to safari browser and ios device in cloud
		   caps=new DesiredCapabilities().iphone();
		   caps.setCapability("appiumVersion", "1.17.1");
		   caps.setCapability("deviceName","iPhone 8 Simulator");
		   caps.setCapability("deviceOrientation", "portrait");
		   caps.setCapability("platformVersion","13.4");
		   caps.setCapability("platformName", "iOS");
		   caps.setCapability("browserName", "");
		   caps.setCapability("app","com.apple.springboard");
		   //declare driver object to launch app via appium server
		   driver=new IOSDriver(u,caps);
		   Thread.sleep(5000);
		   try
		   {
			   //now launch photos app
			   HashMap<String,Object> hm=new HashMap<String,Object>();
			   hm.put("bundleId", PHOTOS_BUNDLE_ID);
			   driver.executeScript("mobile:launchApp", hm);
			   Thread.sleep(1000);
			   //now back to our AUT app
			   hm.put("bundleId", BUNDLE_ID);
			   driver.executeScript("mobile:activateApp", hm);
			   Thread.sleep(5000);
			   //now reactivate the photos app and close that app
			   hm.put("BundleId", PHOTOS_BUNDLE_ID);
			   driver.executeScript("mobile:terminateApp", hm);
			   Thread.sleep(5000);
			   //now reactivate AUT app
			   hm.put("bundleId",BUNDLE_ID);
			   driver.executeScript("mobile:activateApp", hm);
			   Thread.sleep(5000);
		   }
		   catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   finally
		   {
			   //close App
			   driver.closeApp();
		   }

	}

}

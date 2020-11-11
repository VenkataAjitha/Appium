package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Ioscloud1 {

	public static void main(String[] args) throws Exception {
			//Take platform details
		   Scanner sc=new Scanner(System.in);
		   System.out.println("Android or IOS");
		   String platform=sc.nextLine();
		   sc.close();
		   //give sauce lab details
		   String USERNAME="smiley02";
		   String ACCESSKEY="77ace69b-8756-4218-b89a-3f49741106ce";
		   String endpoint="http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub";
		   URL u=new URL(endpoint);
		   //declare objects globally
		   DesiredCapabilities caps=null;
		   AppiumDriver<WebElement> driver=null;
		   if(platform.equalsIgnoreCase("ios"))
		   {
			   //Capabilities related to safari browser and ios device in cloud
			   caps=new DesiredCapabilities().iphone();
			   caps.setCapability("appiumVersion", "1.17.1");
			   caps.setCapability("deviceName","iPhone 8 Simulator");
			   caps.setCapability("deviceOrientation", "portrait");
			   caps.setCapability("platformVersion","13.4");
			   caps.setCapability("platformName", "iOS");
			   caps.setCapability("browserName", "Safari");
			   //connect to cloud to access "iPhone 8 Simulator" for website testing
			   driver=new IOSDriver<WebElement>(u,caps);
			  }
		   else {
			   caps = DesiredCapabilities.android();
			   caps.setCapability("appiumVersion", "1.11.1");
			   caps.setCapability("deviceName","Samsung Galaxy S9 WQHD GoogleAPI Emulator");
			   caps.setCapability("deviceOrientation", "portrait");
			   caps.setCapability("browserName", "Chrome");
			   caps.setCapability("platformVersion", "9.0");
			   caps.setCapability("platformName","Android");
			   //connect to cloud to access "Samsung Galaxy S9 WQHD GoogleAPI Emulator" for website testing
			   driver=new AndroidDriver<WebElement>(u,caps);
		   }
		   //launch site
		   driver.get("https://www.google.com");
		   Thread.sleep(5000);
		   //validate
		   if(driver.getTitle().equals("Google"))
		   {
			   System.out.println("Test passed");
		   }
		   else
		   {
			   System.out.println("Test failed");
		   }
		   //close site 
		   driver.quit();
		   
		   
		   
		   
	}

}

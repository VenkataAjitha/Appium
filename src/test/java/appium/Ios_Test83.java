package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class Ios_Test83 {

	public static void main(String[] args) throws Exception {
		 //give sauce lab details
		   String USERNAME="smiley02";
		   String ACCESSKEY="77ace69b-8756-4218-b89a-3f49741106ce";
		   String endpoint="http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub";
		   URL u=new URL(endpoint);
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
		   caps.setCapability("autoLaunch", false);
		   //declare driver object to launch app via appium server
		   driver=new IOSDriver(u,caps);
		   Thread.sleep(5000);
		   try
		   {
			   //close if any other app was opened previously
			   driver.executeScript("mobile:pressButton",ImmutableMap.of("name","home"));
			   Thread.sleep(5000);
			   //go to home screen
			   driver.executeScript("mobile:pressButton", ImmutableMap.of("name","home"));
			   Thread.sleep(5000);
			   //collect screens or home screen 
			   List<WebElement> screens=driver.findElements(By.xpath("//*[@name='Home Screen icons']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeIcon"));
			   int count=screens.size();
			   System.out.println("no of pages in home screen"+count);
			   //go to each page from first page and count no of apps 
			   int totalappscount=0;
			   for(int i=0;i<count;i++)
			   {
				   int screenappscount=screens.get(i).findElements(By.xpath("child::XCUIElementTypeIcon")).size();
				   totalappscount=totalappscount+screenappscount;
				   HashMap<String,String> hm=new HashMap<String,String>();
				   hm.put("direction", "left");
				   driver.executeScript("mobile:swipe", hm);
				   
			   }
			   System.out.println("no of apps on homescreen is"+totalappscount);
			   //go to home and click on contacts apps icon
			   driver.executeScript("mobile:pressButton", ImmutableMap.of("name","home"));
			   Thread.sleep(5000);
			   driver.executeScript("mobile.pressButton", ImmutableMap.of("name","home"));
			   Thread.sleep(5000);
			   for(int i=0;i<count;i++)
			   {
				   MobileElement e=(MobileElement) driver.findElements(By.xpath("//XCUIElementTypeIcon[@name='Contacts']"));
				   if(e.isDisplayed())
				   {
					   e.click();
					   break;
				   }
				   else
				   {
					   HashMap<String,Object> hm=new HashMap<String,Object>();
					   hm.put("direction", "left");
					   driver.executeScript("mobile:swipe", hm);
					   
				   }
			   }
			   Thread.sleep(5000);
			   driver.terminateApp("com.apple.MobileAddressBook");
			   
		   }
		   catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }		   
	}

}
	
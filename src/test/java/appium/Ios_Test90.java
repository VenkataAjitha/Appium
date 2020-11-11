package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Ios_Test90 {

	public static void main(String[] args) throws Exception {
		//give sauce lab details
		   String USERNAME="Thindi";
		   String ACCESSKEY="dd7df11b-2cfd-4e61-a5bb-e11d2a58ebdb";
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
		   caps.setCapability(MobileCapabilityType.APP," https://github.com/cloudgrey.io/the-app/releases/download/v1.6.1/TheApp-v1.6.1.app.zip");
		   //declare driver object  to launch an app via appium server
		   driver=new IOSDriver(u,caps);
		   WebDriverWait wait=new WebDriverWait(driver,20);
		   
		   try
		   {
			   Thread.sleep(5000);
			   wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("list Demo"))).click();
			   wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("cirrostratus"))).click();
			   wait.until(ExpectedConditions.alertIsPresent());
			   //collect label of all buttons in alert
			   HashMap<String,Object> hm=new HashMap();
			   hm.put("action", "getButtons");
			   List<String> buttons=(List<String>)driver.executeScript("mobile:alert", hm);
			   //find the label of the buttons which isn't 'OK' and 'CANCEL'
			   String buttonlabel=null;
			   for(String button:buttons)
			   {
				   if(button.equalsIgnoreCase("OK") || button.equalsIgnoreCase("CANCEL"))
				   {
					   continue;
					   //continue loop
				   }
				   else
				   {
					   buttonlabel=button;
					   break;//terminate from the loop
				   }
				   
			   }
			   if(buttonlabel==null)
			   {
				   System.out.println("no extra labels");
			   }
			   else
			   {
				   //here we could verify that the new button press worked
				   hm.put("action", "accept");
				   hm.put("buttonlabel", buttonlabel);
				   driver.executeScript("mobile:alert", hm);
				   wait.until(ExpectedConditions.alertIsPresent());
				   String alertText=driver.switchTo().alert().getText();
				   System.out.println(alertText);
				   driver.switchTo().alert().accept();
			   }
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		//closeApp
		   driver.close();

	}
	

}

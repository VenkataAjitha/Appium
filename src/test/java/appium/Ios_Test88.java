package appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Ios_Test88 {

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
		   caps.setCapability(MobileCapabilityType.APP,"");
		   caps.setCapability("simulatorTracePointer","true");
		   //declare driver object to launch app via appium server
		  driver=new IOSDriver(u,caps);
		  try {
			  Thread.sleep(5000);
			  String desktop=System.getenv("HOME")+"/Destop";
			  driver.rotate(ScreenOrientation.LANDSCAPE);
			  File regularScreenshot=driver.getScreenshotAs(OutputType.FILE);
			  driver.setSetting("screenshotOrientation","landscapeRight");
			  File adjustedScreenshot=driver.getScreenshotAs(OutputType.FILE);
			  FileHandler.copy(regularScreenshot,new File(desktop+"/screen1.png"));
			  FileHandler.copy(adjustedScreenshot,new File(desktop+"/screen2.png"));
			  Thread.sleep(5000);
		  }
		  catch(Exception ex)
		  {
			  System.out.println(ex.getMessage());
		  }
		  //close App
		  driver.quit();
		  
		   

	}

}

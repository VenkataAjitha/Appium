package appium;


import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;

import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;



import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.remote.MobileCapabilityType;

public class Ios_Test89 {

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
		   caps.setCapability(MobileCapabilityType.APP,"");
		   //declare driver object  to launch an app via appium server
		   driver=new IOSDriver(u,caps);
		   try
		   {
			   IOSStartScreenRecordingOptions iossr=new IOSStartScreenRecordingOptions();
			   iossr.withTimeLimit(Duration.ofSeconds(200));
			   iossr.withVideoType("1280*720");
			   driver.startRecordingScreen(iossr);
			   Thread.sleep(5000);
			   driver.findElementByAccessibilityId("login").click();
			   Thread.sleep(5000);
			   driver.findElementByAccessibilityId("slider1").click();
			   Thread.sleep(5000);
			   WebElement el=driver.findElementByAccessibilityId("slider");
			   //get location of slider
			   Rectangle rect=el.getRect();
			   Point start=new Point(rect.x+15,rect.y+20);
			   Point end=new Point(rect.x+rect.width-15,rect.y+20);
			   System.out.println(start.x+"  "+start.y);
			   System.out.println(end.x+"  "+end.y);
			   //swipe to right (using IOS specific automation code,specific for reactnative app)
			   HashMap<String,Object> hm1=new HashMap();
			   hm1.put("duration",1.5);
			   hm1.put("fromX", start.x);
			   hm1.put("fromY", start.y);
			   hm1.put("toX",end.x);
			   hm1.put("toY", end.y);
			   driver.executeScript("dragFromToForDuration", hm1);
			   Thread.sleep(5000);
			   //Swipe left (using IOS specific automation code,specific for reactnative app)
			   HashMap<String,Object> hm2=new HashMap();
			   hm2.put("duration", 1.5);
			   hm2.put("fromX", end.x);
			   hm2.put("fromY", end.y);
			   hm2.put("toX", start.x);
			   hm2.put("toY", start.y);
			   driver.executeScript("drahFromToForDuration", hm2);
			   Thread.sleep(5000);
			   String videostring=driver.stopRecordingScreen();
			   byte[] data=Base64.getDecoder().decode(videostring);
			   String destinationPath="target/filename.mp4";
			   Path path=Paths.get(destinationPath);
			   Files.write(path,data);
			   Thread.sleep(5000);
			   
		   }
		   catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   //close app
		   driver.closeApp();
	}

}

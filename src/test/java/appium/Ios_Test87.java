package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Ios_Test87 {

	public static void main(String[] args) throws Exception {
		//give sauce lab details
		   String USERNAME="smiley02";
		   String ACCESSKEY="77ace69b-8756-4218-b89a-3f49741106ce";
		   String endpoint="http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub";
		   URL u=new URL(endpoint);
		// common objects
		   DesiredCapabilities caps=null;
		   IOSDriver<WebElement> driver=null;
		   String APP="https://github.com/cloudgrey-io/the-app/releases/download/v1.2.1/TheApp-v1.2.1.app.zip";
		String BUNDLE_ID="io.cloudgrey.the-app";//AUTS bundleId
		//provide desiredcapabilities related to simulator and AUTapp
		   caps=new DesiredCapabilities().iphone();
		   caps.setCapability("appiumVersion", "1.17.1");
		   caps.setCapability("deviceName","iPhone 8 Simulator");
		   caps.setCapability("deviceOrientation", "portrait");
		   caps.setCapability("platformVersion","13.4");
		   caps.setCapability("platformName", "iOS");
		   caps.setCapability("browserName", "");
		   caps.setCapability("app", APP);
		   driver=new IOSDriver(u,caps);
		   HashMap<String,Object> hm=new HashMap();
		   hm.put("bundleId", BUNDLE_ID);
		   try {
			   Thread.sleep(5000);
			   //close App which is opened by default
			   driver.executeScript("mobile:terminateApp", hm);
			   //create top and bottom cordinates on mobile home screen
			   Dimension screensize=driver.manage().window().getSize();
			   int yMargin=5;
			   int xMid=screensize.width/2;
			   PointOption top=PointOption.point(xMid,yMargin);
			   PointOption bottom=PointOption.point(xMid,screensize.height-yMargin);
			   //perform top to bottom
			   TouchAction ta=new TouchAction(driver);
			   ta.press(top).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(bottom).perform();
			   Thread.sleep(5000);
			   //perform bottom to top
			   ta.press(bottom).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(top).perform();
			   Thread.sleep(5000);
		   }
		   catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   //close app
		   driver.quit();
	}

}

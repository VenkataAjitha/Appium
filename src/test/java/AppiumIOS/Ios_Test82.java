package AppiumIOS;

import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;

public class Ios_Test82 {

	public static void main(String[] args) throws Exception {
		//desired capabilities related to simulator
		DesiredCapabilities caps=new DesiredCapabilities().iphone();
		   caps.setCapability("appiumVersion", "1.17.1");
		   caps.setCapability("deviceName","iPhone 8 Simulator");
		   caps.setCapability("deviceOrientation", "portrait");
		   caps.setCapability("platformVersion","13.4");
		   caps.setCapability("platformName", "iOS");
		   caps.setCapability("browserName", "");
		   caps.setCapability(CapabilityType.BROWSER_NAME, "");
		   caps.setCapability("app","D:\\Appium\\ui-catalogIOS.");
		   //give sauce lab details
		   String USERNAME="Thindi";
		   String ACCESSKEY="dd7df11b-2cfd-4e61-a5bb-e11d2a58ebdb";
		   String endpoint="http://"+USERNAME+":"+ACCESSKEY+"@ondemand.saucelabs.com:80/wd/hub";
		   URL u=new URL(endpoint);
		   //connect to cloud to access "Samsung Galaxy S9 WQHD GoogleAPI Emulator" for website testing
		   IOSDriver<WebElement> driver=new IOSDriver<WebElement>(u,caps);
		   Thread.sleep(5000);
		   //automation 
		   try
		   {
			   //get current contexts
			   Set<String> cs1=driver.getContextHandles();
			   System.out.println(cs1);
			   //click on webview link
			   driver.findElement(By.name("Web View")).click();
			   Thread.sleep(5000);
			   //get current context
			   Set cs2=driver.getContextHandles();
			   System.out.println(cs2);
			   ArrayList<String> l=new ArrayList<String>(cs2);
			   driver.context(l.get(1));//shift to Webview context
			   driver.findElement(By.linkText("continue")).click();
			   Thread.sleep(5000);
			   driver.context(l.get(0));//Back to NATIVE_APP context
			   driver.findElement(By.xpath("//XCUIElementTypeButton[@name='UICatalog']")).click();
			   Thread.sleep(5000);
		   }
		   catch(Exception e)
		   {
			   System.out.println(e.getMessage());
		   }
		   finally
		   {
			   //close app
			   driver.quit();
			   
		   }
	}

}

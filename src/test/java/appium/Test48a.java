package appium;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;

public class Test48a {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "7.1.1");
		dc.setCapability("appPackage", "com.vodqareactnative");
		dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
		//launch App in device through Appium server by creating driver object
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
		try {
			WebDriverWait w=new WebDriverWait(driver,20);
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='slider1']"))).click();
			Thread.sleep(5000);
			MobileElement e=(MobileElement) driver.findElementByAccessibilityId("slider");
			Dimension size=e.getSize();
			Point p=e.getLocation();//left top coordinates of an element in app screen
			swipe(driver,p.getX(),p.getY()+size.getHeight()/2,p.getX()+size.getWidth()-10,p.getY()+size.getHeight()/2);
			Thread.sleep(5000);
			swipe(driver,p.getX()+size.getWidth()-10,p.getY()+size.getHeight()/2,p.getX(),p.getY()+size.getHeight()/2);
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//close App
		driver.quit();
		//stop Appium Server
		Runtime.getRuntime().exec("taskKill /F /IM node.exe");
		Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}
	public static void swipe(AndroidDriver driver,int x1,int y1,int x2,int y2)
	{
		TouchAction ta=new TouchAction(driver);
		PointOption po=new PointOption();
		PointOption po1=po.withCoordinates(x1,y1);
		PointOption po2=po.withCoordinates(x2, y2);
		ta.press(po1).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(po2).release().perform();
	}

}

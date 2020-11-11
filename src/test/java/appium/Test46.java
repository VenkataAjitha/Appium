package appium;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test46 {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "7.1.1");
		dc.setCapability("appPackage", "com.android.vending");
		dc.setCapability("appActivity", "com.google.android.finsky.activities.MainActivity");
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
			//vertical swipe
		try {
			WebDriverWait w=new WebDriverWait(driver,30);
			Thread.sleep(10000);
			MobileElement panel=(MobileElement) driver.findElementByXPath("//*[@resource-id='com.android.vending:id/cluster_content'][1]");
			Point center=panel.getCenter();
			while(2>1)
			{
				try
				{
					w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@content-desc,'Minion Rush')]")));
					break;
				}
				catch(Exception ex)
				{
					myswipe(driver,center.x+400,center.y,center.x-400,center.y);
				}
			}
			Thread.sleep(5000);
			//left to Right
			while(2>1)
			{
				try {
					w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@content-desc,'Hill Climb')]")));
					break;
				}
				catch(Exception ex)
				{
					myswipe(driver,center.x-400,center.y,center.x+400,center.y);
				}
			}
			Thread.sleep(5000);

	}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			}
		//close App
				//driver.quit();
				//stop Appium Server
				Runtime.getRuntime().exec("taskKill /F /IM node.exe");
				Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}
	public static void myswipe(AndroidDriver driver,int x1,int y1,int x2,int y2)
	{
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence s=new Sequence(finger,1);
		s.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),x1,y1));
		s.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		s.addAction(finger.createPointerMove(Duration.ofMillis(600),PointerInput.Origin.viewport(),x2,y2));
		s.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(s));
	}

}

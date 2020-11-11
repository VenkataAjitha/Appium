package appium;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test45 {

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
			//vertical swipe
		try {
			WebDriverWait w=new WebDriverWait(driver,30);
			
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']"))).click();
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Vertical swiping']"))).click();
			//bottom to top
			while(2>1)
			{
				try {
					w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Karma')]")));
					break;
				}
				catch(Exception ex)
				{
					PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
					Sequence s= new Sequence(finger,1);
					Dimension size=driver.manage().window().getSize();
					Point source=new Point(size.getWidth()-400,size.getHeight()-400);
					s.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),source.x,source.y));
					s.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
					s.addAction(new Pause(finger,Duration.ofMillis(100)));
					s.addAction(finger.createPointerMove(Duration.ofMillis(600),PointerInput.Origin.viewport(),source.x,source.y/2));
					s.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					driver.perform(Arrays.asList(s));
				}
			}
			Thread.sleep(5000);
			
			//top to bottom
			while(2>1)
			{
				try {
					w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='C']")));
					break;
				}
				catch(Exception ex)
				{
					PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
					Sequence s= new Sequence(finger,1);
					Dimension size=driver.manage().window().getSize();
					Point source=new Point(400,400);
					s.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),source.x,source.y));
					s.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
					s.addAction(new Pause(finger,Duration.ofMillis(100)));
					s.addAction(finger.createPointerMove(Duration.ofMillis(600),PointerInput.Origin.viewport(),source.x,source.y*2));
					s.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
					driver.perform(Arrays.asList(s));
				}
			}
			Thread.sleep(2000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//close App
		//driver.quit();
		//stop Appium Server
		Runtime.getRuntime().exec("taskKill /F /IM node.exe");
		Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");

	}

}

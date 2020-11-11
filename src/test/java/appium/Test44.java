package appium;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Test44 {

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
		//long press
		try {
			
			WebDriverWait w= new WebDriverWait(driver,20);
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='doubleTap']"))).click();
			Thread.sleep(5000);
			MobileElement e=(MobileElement) w.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("doubleTapMe")));
			Point Source=e.getCenter();
			PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
			Sequence s= new Sequence(finger,1);
			s.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),Source.x,Source.y));
			s.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			s.addAction(new Pause(finger,Duration.ofMillis(200)));
			s.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			//delay between taps
			s.addAction(new Pause(finger,Duration.ofMillis(40)));
			s.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			s.addAction(new Pause(finger,Duration.ofMillis(200)));
			s.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			driver.perform(Arrays.asList(s));
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Double tap successful!']")));
			System.out.println("Double tap success");
			driver.findElement(By.xpath("//*[@text='OK']")).click();
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

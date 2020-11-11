package appium;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Test52 {

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
			Thread.sleep(10000);
			WebDriverWait w=new WebDriverWait(driver,20);
			MobileElement e=(MobileElement) driver.findElement(By.xpath("//*[@content-desc='password']"));
			e.clear();
			e.click();
			driver.hideKeyboard();
			Map<String, String> input1=ImmutableMap.of("text","admin");
			driver.executeScript("mobile:type", input1);
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			Thread.sleep(5000);
			MobileElement e1=(MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().textStartsWith(\"Slide your number\")");
			String eid1=e1.getId();
			Thread.sleep(5000);
			MobileElement e2=(MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().textStartsWith(\"Demo Long press button\")");
			String eid2=e2.getId();
			Map<String, String> input2=ImmutableMap.of("elementId",eid2,"elementToId",eid1);
			driver.executeScript("mobile:scrollBackTo",input2);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//close App
//		driver.quit();
//		//stop Appium Server
//		Runtime.getRuntime().exec("taskKill /F /IM node.exe");
//		Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");

	}

}

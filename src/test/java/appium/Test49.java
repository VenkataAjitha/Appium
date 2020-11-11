package appium;


import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class Test49 {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "5.1");
//		dc.setCapability("appPackage", "com.vodqareactnative");
//		dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
		dc.setCapability("app","D:\\Appium\\VodQA.apk");
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
			MobileElement e1=(MobileElement) driver.findElementByAccessibilityId("slider");
			MobileElement e2=(MobileElement) driver.findElementByAccessibilityId("slider1");
			Dimension size1=e1.getSize();
			Dimension size2=e2.getSize();
			TouchAction ta1=new TouchAction(driver);
			TouchAction ta2=new TouchAction(driver);
			ta1.press(ElementOption.element(e1,0,size1.getHeight()/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(ElementOption.element(e1,size1.getWidth()-10,size1.getHeight()/2)).release().perform();
			ta2.press(ElementOption.element(e2,0,size2.getHeight()/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(ElementOption.element(e2,size2.getWidth()-10,size2.getHeight()/2)).release().perform();
			Thread.sleep(5000);
			ta1.press(ElementOption.element(e1,size1.getWidth()-10,size1.getHeight()/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(ElementOption.element(e1,0,size1.getHeight()/2)).release().perform();
			ta2.press(ElementOption.element(e2,size2.getWidth()-10,size2.getHeight()/2)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(ElementOption.element(e2,0,size2.getHeight()/2)).release().perform();
			MultiTouchAction ma=new MultiTouchAction(driver);
			ma.add(ta1).add(ta2).perform();
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

}

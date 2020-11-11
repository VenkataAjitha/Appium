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
import io.appium.java_client.touch.offset.PointOption;

public class Test49b {

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
		dc.setCapability("appPackage", "com.vodqareactnative");
		dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
		//dc.setCapability("app","D:\\Appium\\VodQA.apk");
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
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='photoView']"))).click();
			Thread.sleep(5000);
			Dimension size=driver.manage().window().getSize();
			
			int scrWidth=size.width;
			int scrHeight=size.height;
			TouchAction ta1=new TouchAction(driver);
			TouchAction ta2=new TouchAction(driver);
			 PointOption pointFrom = PointOption.point(scrWidth / 2,  scrHeight /2);
			    PointOption pointTo = PointOption.point(0,  scrHeight/2);
			    PointOption point3 = PointOption.point(scrWidth-50,  scrHeight/2-50);
			    TouchAction t1 =new TouchAction(driver)
			            .longPress(pointFrom)
			            .waitAction(WaitOptions.waitOptions(java.time.Duration.ofMillis(500)))
			            .moveTo(pointTo)
			            .release();
			   
			    Thread.sleep(5000);
			    TouchAction t2 =new TouchAction(driver)
			            .longPress(PointOption.point(scrWidth / 2-20,  scrHeight /2-20))
			            .waitAction(WaitOptions.waitOptions(java.time.Duration.ofMillis(500)))
			            .moveTo(point3)
			            .release();
			    MultiTouchAction multiTouch=new MultiTouchAction(driver);
			            multiTouch.add(t1).add(t2).perform();
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

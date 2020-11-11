package appium;


import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
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

public class Test49a {

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
			WebDriverWait w= new WebDriverWait(driver,20);
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='LOG IN']"))).click();
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='photoView']"))).click();
			Thread.sleep(5000);
			Dimension size=driver.manage().window().getSize();
	
			int scrWidth=size.width;
			int scrHeight=size.height;
			System.out.println(scrWidth+"    "+scrHeight);
			 PointOption pointFrom = PointOption.point(scrWidth/ 2,  scrHeight/2);
			    PointOption pointTo = PointOption.point(scrWidth/3,  scrHeight/3);
			    PointOption point3 = PointOption.point(scrWidth*3/4,  scrHeight*3/4);
			    MultiTouchAction multiTouch=new MultiTouchAction(driver);
			    
			    //zoom out
			    TouchAction t1 =new TouchAction(driver);
			            t1.press(pointFrom)
			            .waitAction(WaitOptions.waitOptions(java.time.Duration.ofMillis(500)))
			            .moveTo(pointTo);
			     			   
			    Thread.sleep(5000);
			    TouchAction t2 =new TouchAction(driver);
			            t2.press(pointFrom)
			            .waitAction(WaitOptions.waitOptions(java.time.Duration.ofMillis(500)))
			            .moveTo(point3);
			           
			
			   
			//zoom in
		    TouchAction t3 =new TouchAction(driver);
		            t3.press(pointTo).waitAction(WaitOptions.waitOptions(java.time.Duration.ofMillis(500)))
		            .moveTo(pointFrom);
		            
		      
		    Thread.sleep(5000);
		    TouchAction t4 =new TouchAction(driver);
		           t4.press(point3)
		            .waitAction(WaitOptions.waitOptions(java.time.Duration.ofMillis(500)))
		            .moveTo(pointFrom).release();
		          
		    multiTouch.add(t1).add(t2).add(t3).add(t4).perform();
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

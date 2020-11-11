package appium;


import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Tesy40 {

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
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='longPress']"))).click();
			Thread.sleep(5000);
			MobileElement e=(MobileElement) driver.findElement(By.xpath("//*[@text='Long Press Me']"));
			Actions a=new Actions(driver);
			a.clickAndHold(e).perform();
			try {
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='you pressed me hard :P']")));
			System.out.println("long press successful");
			driver.findElement(By.xpath("//*[@text='OK']")).click();
			
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			}	
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//close App
		driver.quit();
		//stop Appium Server
		Runtime.getRuntime().exec("taskKill /F /IM node.exe");
		Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
		
	}

}

package appium;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Test57 {

	public static void main(String[] args) throws Exception{
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
			driver.setClipboardText("admin");// store data in clipboard
			MobileElement e=(MobileElement) driver.findElement(By.xpath("//*[@content-desc='password']"));
			e.clear();
			e.sendKeys(driver.getClipboardText());// set clipboarddata
			Thread.sleep(5000);
		}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			//close App
//			driver.quit();
//			//stop Appium Server
//			Runtime.getRuntime().exec("taskKill /F /IM node.exe");
//			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");

	}

}

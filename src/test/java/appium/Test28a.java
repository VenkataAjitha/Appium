package appium;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test28a {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ESPRESSO);
		dc.setCapability("forceEspressRebuild","true");
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
				Thread.sleep(5000);
				driver.findElementByXPath("//*[@text='LOG IN']").click();
				Thread.sleep(5000);
				String x=driver.getPageSource();
				System.out.println(x);
				
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

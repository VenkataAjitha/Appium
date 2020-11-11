package appium;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test25 {

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
		dc.setCapability("appPackage", ""); // APP having Adapter view tag to locate visible or hidden elements
		dc.setCapability("appActivity", "");
			
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
			//Common code for Both drivers
			try { //test25
				Thread.sleep(10000);
				driver.findElementByXPath("//*[@text='LOGIN']").click();
				Thread.sleep(2000);
				//locate visible views(scrollview,list view,grid view,textview...etc)using view tag
				driver.findElementByAndroidDataMatcher("{\"name\":\"hasEntry\",\"args\":[\"content-desc\",\"wheelpicker\"]}").click();
				Thread.sleep(5000);
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

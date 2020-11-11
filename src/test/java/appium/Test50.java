package appium;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test50 {

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
			Map<String,Object> resmap=(Map<String, Object>) driver.executeScript("mobile:getNotifications");
			List<Map<String,Object>> maplist= (List<Map<String, Object>>) resmap.get("StatusBarNotifications");
			for(Map<String, Object> eachmap:maplist)
			{
				Map<String,String> ncontent=(Map<String, String>) eachmap.get("notifications");
				//display title in notification
				if(ncontent.get("bigTitle")!=null)
				{
					System.out.println(ncontent.get("bigTitle"));
				}
				else {
					System.out.println(ncontent.get("title"));
				}
				//display text in notification
				if(ncontent.get("bigText")!=null)
				{
					System.out.println(ncontent.get("bigText"));
				}
				else {
					System.out.println(ncontent.get("text"));
				}
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//close App
//				driver.quit();
//				//stop Appium Server
//				Runtime.getRuntime().exec("taskKill /F /IM node.exe");
//				Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");

	}

}

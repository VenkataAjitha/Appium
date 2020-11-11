package appium;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test51 {

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
			Map<String,Object> res=(Map<String, Object>) driver.executeScript("mobile: listSms");
			long t=(Long) res.get("total");
			System.out.println(t);
			List<Map<String,Object>> msgs= (List<Map<String, Object>>) res.get("items");
			//get and display latest sms details
			System.out.println(msgs.get(0).get("address"));
			System.out.println(msgs.get(0).get("date"));
			System.out.println(msgs.get(0).get("subject"));
			System.out.println(msgs.get(0).get("body"));
			for(Map<String, Object> m:msgs)
			{
				System.out.println(m.get("address"));
				System.out.println(m.get("date"));
				System.out.println(m.get("subject"));
				System.out.println(m.get("body"));
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

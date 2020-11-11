package appium;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Test34 {

	public static void main(String[] args) throws Exception {
		//start Appium server
		  Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		  URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device and app
		  DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME, "");
//			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ESPRESSO);
//			dc.setCapability("forceEspressRebuild","true");
			dc.setCapability("deviceName", "emulator-5554");
			dc.setCapability("plateformName", "android");
			dc.setCapability("plateformVersion", "10");
			dc.setCapability("appPackage", "com.vodqareactnative");
			dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
		//launch app in device through Appium server by creating driver object
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
		//test Automation
			try {
				Thread.sleep(5000);
//				KeyEvent k=new KeyEvent(AndroidKey.HOME);
//				driver.pressKey(k);
//				Thread.sleep(5000);
				//get connection details (WIFI,DATA,AIRPLANE MODE) related to device
				ConnectionState con=driver.getConnection();
				//about AIRPLANE
				if(con.isAirplaneModeEnabled())
				{
					System.out.println("AIRPLANE mode is ON ,going to OFF AIRPLANE mode");
					//off AIRPLANE MODE
					driver.toggleAirplaneMode();
					Thread.sleep(5000);
				}
				else {
					System.out.println("AIRPLANE mode is OFF");
				}
				//about wifi
				if(con.isWiFiEnabled())
				{
					System.out.println("WIFI is ON ,going to OFF ");
					//off wifi
					driver.toggleWifi();
					Thread.sleep(5000);
				}
				else
				{
					System.out.println("WIFI is OFF");
				}
				if(con.isDataEnabled())
				{
					System.out.println("Data is ON");
				}
				else
				{
					System.out.println("Data is OFF");
					//ON DATA NETWORK
					driver.toggleData();
					Thread.sleep(5000);
				}
				
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			//close App
			driver.closeApp();
			//stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");

	}

}

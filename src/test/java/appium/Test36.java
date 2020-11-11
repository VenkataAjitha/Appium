package appium;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test36 {

	public static void main(String[] args) throws Exception {
		//start Appium server
		  Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		  URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device and app
		  DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME, "");
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ANDROID_UIAUTOMATOR2);
			//dc.setCapability("forceEspressRebuild","true");
			dc.setCapability("deviceName", "emulator-5554");
			dc.setCapability("plateformName", "android");
			dc.setCapability("plateformVersion", "7.1.1");
			dc.setCapability("locationServiceEnabled", true);
			dc.setCapability("locationServiceAuthorized", true);
			dc.setCapability("noReset", true);
			dc.setCapability("fullReset", false);
			
			dc.setCapability("appPackage", "com.google.android.apps.maps");
			dc.setCapability("appActivity", "com.google.android.apps.maps.MapsActivity");
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
			try {
				Thread.sleep(5000);
				//get location
				Location l=driver.location();
				System.out.println(l.getLatitude());
				System.out.println(l.getLongitude());
				System.out.println(l.getAltitude());
				//change location
				Location l2=new Location(27.16544,78.05452,100);
				System.out.println(l.getLatitude());
				System.out.println(l.getLongitude());
				System.out.println(l.getAltitude());
				}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			//close app
			driver.closeApp();
			//stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}

}

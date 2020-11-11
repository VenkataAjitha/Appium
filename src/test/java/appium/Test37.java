package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test37 {

	public static void main(String[] args) throws Exception {
		//start Appium server
		  Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium --chromedriver-executable D:\\SDETWorkspace\\chromedriver_win32\\chromedriver.exe\"");
		  URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device and app
		  DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ESPRESSO);
//			dc.setCapability("forceEspressRebuild","true");
			dc.setCapability("deviceName", "emulator-5554");
			dc.setCapability("plateformName", "android");
			dc.setCapability("plateformVersion", "7.1.1");
//			dc.setCapability("appPackage", "com.vodqareactnative");
//			dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
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
		//test Automation context for hybrid app
			try {
			Thread.sleep(5000);
			Set s1=driver.getContextHandles();//get all possbilities
			System.out.println(s1);
			String x=driver.getContext();//current context
			System.out.println(x);
			driver.context("NATIVE_APP");//change context
			String y=driver.getContext();
			Thread.sleep(5000);
			System.out.println(y);
			if(driver.isBrowser())
			{
				driver.close();
			}
			else {
				driver.closeApp();
			}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			//stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}

}

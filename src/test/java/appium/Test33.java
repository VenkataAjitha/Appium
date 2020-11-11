package appium;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.HasAndroidDeviceDetails;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Test33 {

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
			dc.setCapability("plateformVersion", "7.1.1");
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
			try
			{
				Thread.sleep(5000);
				//come to Home
				KeyEvent k=new KeyEvent(AndroidKey.HOME);
				driver.pressKey(k);
				Thread.sleep(5000);
				//open notifications
				driver.openNotifications();
				Thread.sleep(5000);
				try {
					WebElement e=driver.findElement(By.xpath("//*[@text='CLEAR ALL"));
					e.click();
					Thread.sleep(5000);
					driver.pressKey(k); //back to home
				}
				catch(Exception ex)
				{
					driver.pressKey(k);
				}
				Thread.sleep(5000);
				//set details of device
				String x= driver.getRemoteAddress().getProtocol();
				System.out.println(x);
				String y= driver.getRemoteAddress().getPath();
				System.out.println(y);
				String z= driver.getRemoteAddress().getHost();
				System.out.println(z);
				int w= driver.getRemoteAddress().getPort();
				System.out.println(w);
				long a= driver.getDisplayDensity();
				System.out.println(a);
				String b=driver.getDeviceTime();
				System.out.println(b);
				String c=driver.getPlatformName();
				System.out.println(c);
				String an=driver.getAutomationName();
				System.out.println(an);
				double bc=driver.getBatteryInfo().getLevel();
				System.out.println(bc);
				//get device lock details
				if(driver.isDeviceLocked()==false)
				{
					driver.lockDevice();
					Thread.sleep(2000);
					driver.unlockDevice();
				}
				//Back to work with app specified in desired capabilities
				driver.launchApp();
				Thread.sleep(5000);
				
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
				
			}
			//close App
			driver.closeApp();
			//stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}

}

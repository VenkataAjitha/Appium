package appium;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class Test30 {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
//		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ESPRESSO);
//		dc.setCapability("forceEspressRebuild","true");
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
				String x=driver.getOrientation().name();
				System.out.println(x);
				if(x.equalsIgnoreCase("PORTRAIT"))
				{
					driver.rotate(ScreenOrientation.LANDSCAPE);
				}
				String y=driver.getOrientation().name();
				if(y.equalsIgnoreCase("LANDSCAPE"))
				{
				driver.rotate(ScreenOrientation.PORTRAIT);
				}
				WebDriverWait w= new WebDriverWait(driver,20);
				w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='username']"))).click();
				if(driver.isKeyboardShown())
				{
					Thread.sleep(2000);
					driver.hideKeyboard();
					Thread.sleep(2000);
				}
				
				KeyEvent k=new KeyEvent(AndroidKey.HOME);
				driver.pressKey(k);
				Thread.sleep(5000);
				driver.launchApp();
				Thread.sleep(5000);
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			//close App
			//driver.closeApp();
			//stop Appium Server
			Runtime.getRuntime().exec("taskKill /F /IM node.exe");
			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}

}

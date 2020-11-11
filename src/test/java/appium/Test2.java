package appium;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test2 {

	public static void main(String[] args) throws Exception
	{
		// start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "10");
		dc.setCapability("appPackage", "com.vodqareactnative");
		dc.setCapability("appActivity", ".MainActivity");
		
		AndroidDriver driver;
		while(2>1) {
			try {
				driver=new AndroidDriver(u,dc);
				String x=driver.getPageSource();
				driver.findElement(By.xpath("//*[@text='Continue']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@text='OK']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@text='LOG IN']")).click();
				Thread.sleep(2000);
				break;
			}
			catch(Exception ex) {
			}
		
		}
		//stop appium server
		
		Runtime.getRuntime().exec("taskKill /f /IM node.exe");
		Runtime.getRuntime().exec("taskKill /f /IM cmd.exe");
}
}

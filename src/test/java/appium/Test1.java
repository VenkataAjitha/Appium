package appium;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test1 {

	public static void main(String[] args) throws Exception
	{
		// start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		Thread.sleep(5000);
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "10");
		dc.setCapability("appPackage", "com.google.android.apps.messaging");
		dc.setCapability("appActivity", ".ui.ConversationListActivity");
		AndroidDriver driver=new AndroidDriver(u,dc);
		//stop appium server
		Runtime.getRuntime().exec("taskKill /f /IM node.exe");
		Runtime.getRuntime().exec("taskKill /f /IM cmd.exe");
}
}

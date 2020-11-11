package appium;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Test60 {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "10");
		dc.setCapability("app", "APPV1");
		dc.setCapability("uninstallOtherPackages", "io.cloudgrey.the_app");
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
		//app upgrade
		String APPV1="D:\\Appium\\TheApp-v1.0.0.apk";
		String appUpgradeVersion="D:\\Appium/TheApp-v1.0.2.apk";
		String APPPKG="io.cloudgrey.the_app";
		String APPACT="com.theapp.MainActivity";
		String msg="Hello Steve Jobs";
		By msgInput=MobileBy.AccessibilityId("messageInput");
		By savedmsg=MobileBy.AccessibilityId(msg);
		By saveMsgBtn=MobileBy.AccessibilityId("messageSaveBtn");
		By echoBox=MobileBy.AccessibilityId("EchoBox");
		try {
			Thread.sleep(5000);
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.presenceOfElementLocated(echoBox)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(msgInput)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(saveMsgBtn)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(savedmsg));
			driver.installApp(appUpgradeVersion);
			Activity activity=new Activity(APPPKG,APPACT);
			driver.startActivity(activity);
			wait.until(ExpectedConditions.presenceOfElementLocated(echoBox)).click();
			String savedtext=wait.until(ExpectedConditions.presenceOfElementLocated(savedmsg)).getText();
			if(savedtext.equalsIgnoreCase(msg))
			{
				System.out.println("Test passed");
			}
			else {
				System.out.println("test failed");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//close App
//		driver.quit();
//		//stop Appium Server
//		Runtime.getRuntime().exec("taskKill /F /IM node.exe");
//		Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");

	}

}

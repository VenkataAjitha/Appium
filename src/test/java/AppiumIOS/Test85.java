package AppiumIOS;

import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;

public class Test85 {

	public static void main(String[] args) {
		//start appium server programmatically
		//common objects
		String APP="https://github.com/cloudgrey-io/the-app/releases/download/v1.2.1/TheApp-v1.2.1.app.zip";
		String PHOTOS_BUNDLE_ID="com.apple.mobileslidesnow";//photos buildin apps bundleID
		String BUNDLE_ID="io.cloudgrey.the-app";//AUT's BundleID
		DesiredCapabilities dc=new DesiredCapabilities();
		
		
		
		//declare driver object to launch app via appium sevrer
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		Thread.sleep(5000);
		//automation
		try {
			//now launch photos app
			HashMap<String,Object> hm=new HashMap();
			hm.put("bundleId", PHOTOS_BUNDLE_ID);
			driver.executeScript("mobile:launchApp", hm);
			Thread.sleep(5000);
			hm.put("bundleId", BUNDLE_ID);
			driver.executeScript("mobile:activateApp",hm);
			Thread.sleep(5000);
			//now reactivate the photos app and close that app
			hm.put("bundleId", PHOTOS_BUNDLE_ID);
			driver.executeScript("mobile:activate", hm);
			Thread.sleep(5000);
			driver.executeScript("mobile:terminateApp", hm);
			Thread.sleep(5000);
			//now reactivate out AUT app
			hm.put("bundleId", BUNDLE_ID);
			driver.executeScript("mobile:activateApp", hm);
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			//close app
			driver.quit();
			//stop appium server
			as.stop();
		}
			
	}
}

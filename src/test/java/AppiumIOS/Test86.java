package AppiumIOS;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;

public class Test86 {

	public static void main(String[] args) {
		// start appium server programmatically
		//common objects 
		String APP="https://github.com/cloudgrey-io/the-app/releases/download/v1.2.1/TheApp-v1.2.1.app.zip";
		//provide desiredcapabilitites related to simulator and app
		//declare driver object to launch app via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		Thread.sleep(5000);
		//automation
		try
		{
			//the App is app icon name visible in device or simulator after installation
			driver.get("theApp:login/alice/mypassword");
			Thread.sleep(5000);
			driver.findElement(MobileBy.AccessibilityId("Open")).click();
			Thread.sleep(5000);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		finally {
			//close App
			driver.closeApp();
		}

	}

}

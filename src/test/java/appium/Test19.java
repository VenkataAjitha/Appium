package appium;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;

public class Test19 {

	public static void main(String[] args) throws Exception {
		// convert image file to Base64 String
		File f= new File("D:\\Appium\\AppiumWorkspace\\dial.png");
		Path p=f.toPath();
		String x=Base64.getEncoder().encodeToString(Files.readAllBytes(p));
	    System.out.println(x);
	    //start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//set desire capabilities
				DesiredCapabilities dc=new DesiredCapabilities();
				dc.setCapability(CapabilityType.BROWSER_NAME, "");
				dc.setCapability("deviceName", "emulator-5556");
				dc.setCapability("platformName", "android");
				dc.setCapability("platformVersion","7.1.1");
				dc.setCapability("appPackage", "com.android.dialer");
				dc.setCapability("appActivity", "com.android.dialer.main.impl.MainActivity");
				// launch object
				AndroidDriver driver;
				while(2>1) {
					try {
						driver=new AndroidDriver(u,dc);
						break;
					}
					catch(Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
				//test automation
				try {
					Thread.sleep(2000);
					driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD,0.3);
					if(driver.findElementByImage(x).isDisplayed());
					{
						int xco=driver.findElementByImage(x).getLocation().getX();
						int yco=driver.findElementByImage(x).getLocation().getY();
						int w=driver.findElementByImage(x).getSize().getWidth();
						int h=driver.findElementByImage(x).getSize().getHeight();
						System.out.println(xco+" "+yco);
					
						System.out.println(w+" "+h);
						//automate operations on match element using "TouchAction" class
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				//close app
				driver.closeApp();
				//close Appium server
				Runtime.getRuntime().exec("taskkill /F /IM node.exe");
				Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}

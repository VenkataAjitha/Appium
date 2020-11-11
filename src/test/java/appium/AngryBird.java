package appium;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;

import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.Setting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class AngryBird {

	public static void main(String[] args) throws Exception {
		// Download app from https://apkpure.com/angry-birds-classic.com.rovio.angrybirds
			//Install app into AVD via "adb -s XXXX install path of apk file
			//Capture images of required elements via snipping tool or short keys
			//save images in project folrder with ".png" extension
			//start appium server
			//start appium server
		    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
			URL u= new URL("http://0.0.0.0:4723/wd/hub");
			
			//Desired capabilities
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME,"");
			dc.setCapability("deviceName","emulator-5554");
			dc.setCapability("platformName","android");
			dc.setCapability("platformVersion","5.1.1");
			dc.setCapability("appPackage","com.rovio.angrybirds");
			dc.setCapability("appActivity","com.rovio.fusion.App");
			dc.setCapability("orientation","LANDSCAPE");
			
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
			try //Game automation
			{
				driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS,false);
				driver.setSetting(Setting.FIX_IMAGE_FIND_SCREENSHOT_DIMENSIONS,false);
				Thread.sleep(15000);
				driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD,0.2);
				File f=new File("checkmark.png");
				Path refImgPath=f.toPath();
				byte[] b=Files.readAllBytes(refImgPath);
				String imageData=Base64.getEncoder().encodeToString(b);
				MobileElement e=(MobileElement) driver.findElementByImage(imageData);
				e.click();
				Thread.sleep(5000);
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			//close App
			//driver.quit();
			//stop Appium server
			Runtime.getRuntime().exec("taskkill /f /IM node.exe");
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}

}

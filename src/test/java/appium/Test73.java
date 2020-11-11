package appium;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;

public class Test73 {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "8.1.1");
		dc.setCapability("appPackage", "com.google.android.apps.photos");
		dc.setCapability("appActivity", "com.google.android.apps.photos.home.HomeActivity");
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
		By backupswitch=By.id("com.google.android.apps.photos:id/auto_backup_switch");
		By touchOutside=By.id("com.google.android.apps.photos:id/touch_outside");
		By keepOff=By.xpath("//*[@text='KEEP OFF']");
		
		
		WebDriverWait wait=new WebDriverWait(driver,20);
	
		wait.until(ExpectedConditions.presenceOfElementLocated(backupswitch)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(touchOutside)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(keepOff)).click();
			try {
				driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS,false);
				Thread.sleep(5000);
				//check on all photos using regular locator
//				driver.findElementByAccessibilityId("All photos").click();
//				Thread.sleep(5000);
				//click on a specific photo using image
				
				findImageWithOptimization(driver,"ajitha.png").click();
				//clickImage(driver,e);

				Thread.sleep(10000);
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			//close App
			driver.closeApp();
			//stop Appium server
//			Runtime.getRuntime().exec("taskkill /f /IM node.exe");
//			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
	/*private static void clickImage(AndroidDriver driver, WebElement e) throws Exception {
		//using interations class in swd getting click effect
		if(e.isDisplayed())
		{
		System.out.println("element is  displayed");
		Rectangle rect=e.getRect();
		Point start=new Point(rect.x+rect.width/2,rect.y+rect.height/2);
		PointerInput finger=new PointerInput(Kind.TOUCH,"finger");
		Sequence shoot=new Sequence(finger,0);
		shoot.addAction(finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(),start.x,start.y));
		shoot.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));
		shoot.addAction(new Pause(finger,Duration.ofMillis(100)));
		shoot.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(shoot));
		Thread.sleep(5000);
		}
		else
		{
			System.out.println("element is not displayed");
		}
	}*/
	private static WebElement findImageWithOptimization(AndroidDriver driver,String ImageName)throws Exception
	{
		File f=new File(ImageName);
		Path refImgPath=f.toPath();
		byte[] b=Files.readAllBytes(refImgPath);
		String imageData=Base64.getEncoder().encodeToString(b);
		WebElement el=null;
		double max=1.0;
		double min=0.0;
		double stopsearch=0.05;//zeno's paradox
		double check=0;
		NotFoundException notFound=null;// declared object to exception
		while(Math.abs(max-min)>stopsearch)
		{
			check=(max+min)/2;
			driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD,check);
			try
			{
				el=driver.findElementByImage(imageData);
				min=check;
			}
			catch(NotFoundException ex)
			{
				max=check;
				notFound=ex;
				
			}
		}
		if(el!=null)
		{
			System.out.println(ImageName+" was found at the highest threashold at "+check);
			return el;
		}
		else
		{
			System.out.println(ImageName+" could not found even at the lowest threashold at "+check);
			throw (notFound);
			
		}
	}

}

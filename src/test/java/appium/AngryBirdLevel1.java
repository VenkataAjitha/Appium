package appium;


import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.Setting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;


public class AngryBirdLevel1 {

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
		dc.setCapability("platformVersion","8.1.1");
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
			Thread.sleep(12000);
			findImageWithOptimization(driver,"checkmark.png").click();
			Thread.sleep(3000);
			WebElement birdE1=findImageWithOptimization(driver,"red-bird-in-slingshot.png");
			shootBird(driver,birdE1,-250,120);
			Thread.sleep(14000);
			findImageWithOptimization(driver,"level-cleared-three-stars.png");
			Thread.sleep(3000);
			findImageWithOptimization(driver,"next-level.png").click();
			Thread.sleep(5000);
			WebElement birdE2=findImageWithOptimization(driver,"red-bird-in-slingshot.png");
			shootBird(driver,birdE2,-200,160);
			Thread.sleep(14000);
			findImageWithOptimization(driver,"level-cleared-three-stars.png");
			Thread.sleep(3000);
			findImageWithOptimization(driver,"next-level.png").click();
			Thread.sleep(5000);
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//close App
		driver.quit();
		//stop Appium server
		//Runtime.getRuntime().exec("taskkill /f /IM node.exe");
		//Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
					
	}
	

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
		
			System.out.println(ImageName+" could not found even at the lowest threashold at "+check);
			throw (notFound);
			
		}
		private static void shootBird(AndroidDriver driver,WebElement birdE1,int xOffset, int yOffset)
		{
				Rectangle  rect=birdE1.getRect();
				Point start=new Point(rect.x+rect.width/2,rect.y+rect.height/2);
				Point ends=start.moveBy(xOffset,yOffset);
				Duration dragDuration=Duration.ofMillis(700);
				PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
				Sequence shoot=new Sequence(finger,0);
				shoot.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),start.x,start.y));
				shoot.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				shoot.addAction(finger.createPointerMove(dragDuration,PointerInput.Origin.viewport(),ends.x,ends.y));
				shoot.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Arrays.asList(shoot));
		}
	
	

	}


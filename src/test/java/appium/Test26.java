package appium;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Test26 {
	public static void main(String[] args) throws Exception {
		 //take radius value from keyboard
//		Scanner sc=new Scanner(System.in);
//		System.out.println("enter radius value");
//		int r=sc.nextInt();
		// start appium server
				Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
				Thread.sleep(5000);
				URL u=new URL("http://0.0.0.0:4723/wd/hub");
				DesiredCapabilities dc=new DesiredCapabilities();
				dc.setCapability(CapabilityType.BROWSER_NAME, "");
				dc.setCapability("deviceName", "emulator-5554");
				dc.setCapability("plateformName", "android");
				dc.setCapability("plateformVersion", "10");
				dc.setCapability("appPackage","io.appium.android.apis");
				dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
				AndroidDriver driver;
				while(2>1) {
					try {
						driver=new AndroidDriver(u,dc);
						break;
					}
					catch(Exception ex)
							{
						}
				}
				//Test Automation script
//				 int w=driver.manage().window().getSize().getWidth();
//				 int h=driver.manage().window().getSize().getHeight();
//				 int x=driver.manage().window().getPosition().getX();
//				 int y=driver.manage().window().getPosition().getY();
//				 
//				 System.out.println("width "+w+"heigth "+h+"x axis "+x+" y axis "+y);
				
				 driver.findElementByXPath("//*[@content-desc='Graphics']").click();
				 Thread.sleep(2000);
				 driver.findElementByXPath("//*[@content-desc='FingerPaint']").click();
				 Thread.sleep(2000);
				 MobileElement ele= (MobileElement) driver.findElementById("android:id/content");
				 
		try {
				 //Identify center of the screen
				 Point source=ele.getCenter();
				 System.out.println(source);
				 //define finger based element
				 //create finger touch
				 PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
				 //create sequence of finger events
				 Sequence dragNDrop=new Sequence(finger,0);
				
				 //event-1 (move mouse to specific point)
				 Interaction i1=finger.createPointerMove(Duration.ofSeconds(0), PointerInput.Origin.viewport(),source.x,source.y);
				 dragNDrop.addAction(i1);
				 //event -2 (press finger)
				 Interaction i2=finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg());
				 dragNDrop.addAction(i2);
				
				
					 //Event -3(move pressed finger to other location
					 Interaction i3=finger.createPointerMove(Duration.ofSeconds(6),PointerInput.Origin.viewport(), source.x, source.y-100);
					
					 dragNDrop.addAction(i3);
				 
				
				 //Event -4(release finger)
				 Interaction i4=finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg());
				 dragNDrop.addAction(i4);
				 //perform 4 events of sequence
				 driver.perform(Arrays.asList(dragNDrop));
				
				
				
			}
		catch(Exception e)
		{
			 System.out.println(e.getMessage());
		}
				 
				Runtime.getRuntime().exec("taskKill /F /IM node.exe");
				Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}

}

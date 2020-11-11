package appium;


import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Test39 {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter environment like mobile/computer");
		String en=sc.nextLine();
		
		if(en.equalsIgnoreCase("mobile"))
		{
			//start Appium server
			  Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium --chromedriver-executable D:\\SDETWorkspace\\chromedriver_win32\\chromedriver.exe\"");
			  URL u= new URL("http://0.0.0.0:4723/wd/hub");
			//define desired capabilities related to device and app
			  DesiredCapabilities dc=new DesiredCapabilities();
				dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				dc.setCapability("deviceName", "emulator-5554");
				dc.setCapability("plateformName", "android");
				dc.setCapability("plateformVersion", "7.1.1");
				//launch app in device through Appium server by creating driver object
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
				Thread.sleep(5000);
				//launch site
				driver.get("https://www.youtube.com");
				//change current context "CHROMIUM " to required context="NATIVE_APP"
				driver.context("NATIVE_APP");
				//search for video
				WebDriverWait w=new WebDriverWait(driver,20);
				w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@content-desc='Search']"))).click();
				try {
					w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.EditText']"))).sendKeys("abdul kalam ");
				}
				catch(Exception e)
				{
					driver.findElement(By.xpath("//*[@resource-id='com.android.chrome:id/tab-switcher_button']")).click();
					w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Search youtube']"))).click();
					w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.EditText']"))).sendKeys("abdul kalam ");
				}
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@content-desc='Search']")).click();
				//start video
				w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title,\"Memories: Dr. APJ Abdul  Kalam's speech at European Union\")]"))).click();
				Thread.sleep(10000);
				//pause the video via double tap on that video body 
				MobileElement e=(MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"player-container-id\")");
				Point source=e.getCenter();
				PointerInput finger= new PointerInput(PointerInput.Kind.TOUCH,"finger");
				Sequence s=new Sequence(finger,1);
				s.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x,source.y));
				s.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				s.addAction(new Pause(finger,Duration.ofMillis(200)));
				s.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				s.addAction(new Pause(finger,Duration.ofMillis(40)));
				s.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				s.addAction(new Pause(finger,Duration.ofMillis(200)));
				s.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Arrays.asList(s));
				Thread.sleep(5000);
				//resume video
				driver.findElement(By.xpath("//*[contains(@text,'play video']")).click();
				Thread.sleep(5000);
				//change to context to CHROMIUM driver and close browser
				driver.context("CHROMIUM");
				driver.close();
				//stop appium server
				Runtime.getRuntime().exec("taskkill /F /IM node.exe");
				Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
		}

	}

}

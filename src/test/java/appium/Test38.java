package appium;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.android.AndroidDriver;

public class Test38 {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter environment like mobile/computer");
		String en=sc.nextLine();
		RemoteWebDriver driver; //common concrete class to swd and appium
		//specify code as per environment
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
//				dc.setCapability("appPackage", "com.vodqareactnative");
//				dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
			//launch app in device through Appium server by creating driver object
				
				while(2>1) {
					try {
						driver=new AndroidDriver(u,dc);
						break;
					}
					catch(Exception ex)
							{
						}
				}//while
		}
		else {
				// open browser
				System.setProperty("webdriver.chrome.driver","D:\\SDETWorkspace\\chromedriver_win32\\chromedriver.exe\\");
				System.setProperty("webdriver.chrome.silentOutput", "True");
				driver=new ChromeDriver();
		}
		//common code for both environments(site in mobile and site in computer)
		try {
			Thread.sleep(10000);
			//launch site
			driver.get("http://newtours.demoaut.com/");
			Thread.sleep(5000);
			//click register link
			driver.findElementByPartialLinkText("REGISTER").click();
			Thread.sleep(5000);
			//fill field
			driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys("abdul");
			driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys("kalam");
			Thread.sleep(5000);
			//automate dropdown
			WebElement e=driver.findElementByTagName("select");
			Select s=new Select(e);
			s.selectByVisibleText("INDIA");
			Thread.sleep(5000);
			//fill remaining fields
			//submit data to server
			//close site
			}
		catch(Exception ex)
		{
			//screenshot
			
		}
		//specific code for mobile device
		if(en.equalsIgnoreCase("mobile"))
		{
			//stop appium server			
		}

	}

}

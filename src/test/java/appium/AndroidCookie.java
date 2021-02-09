package appium;


import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class AndroidCookie {

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium --relaxed-security\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "7.1.1");
		
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
		try {
			Thread.sleep(5000);
			//launch site and do login via form filling
			WebDriverWait wait=new WebDriverWait(driver,10);
			driver.get("http://expenseus.com/user/login");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name("user[email]"))).sendKeys("xxxxx");
			driver.findElement(By.name("user[password]")).sendKeys("xxxxx");
			driver.findElement(By.xpath("//input[@value='Log In']")).click();
			Thread.sleep(5000);
			try
			{
				if(driver.findElement(By.linkText("logout")).isDisplayed())
				{
					System.out.println("successfull login");
				}
			}
			catch(Exception exc)
			{
				System.out.println("unsuccessful login");
			}
			//get and display all cookies
			Set<Cookie> cl=driver.manage().getCookies();
			ArrayList<Cookie> ca=new ArrayList(cl);
			for(Cookie c:ca)
			{
				System.out.println(c.getName());
			}
			//Remember session Cookie via cookies object and delete for browser
			Cookie loginCookie=driver.manage().getCookieNamed("PHPSESSID");
			driver.manage().deleteCookieNamed("PHPSESSID");
			Thread.sleep(5000);
			//launch site and do login by adding session cookie to browser
			System.out.println("using cookie");
			driver.manage().addCookie(loginCookie);
			driver.get("http://expenseus.com");
			try
			{
				if(driver.findElement(By.linkText("logout")).isDisplayed());
				{
					System.out.println("Successful login");
				}
			}
			catch(Exception ex)
			{
				System.out.println("unsuccessful login");
			}
			Thread.sleep(20000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//close App
		driver.quit();
//		//stop Appium Server
		Runtime.getRuntime().exec("taskKill /F /IM node.exe");
		Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}

}

package AppiumIOS;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class Test83 {

	public static void main(String[] args) {
		// start appium server programmatically
		//provide desired capabilititesrelated to IOS simulator and HomeScreen
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability("app", "com.apple.springboard");
		dc.setCapability("autoLaunch", false);
		//Declare object to launch App via appium server
		IOSDriver driver=new IOSDriver(as.getUrl(),dc);
		Thread.sleep(5000);
		try
		{
			//close if any other app was opened previously
			driver.executeScript("mobile:pressButto",ImmutableMap.of("name","home"));
			Thread.sleep(5000);
			//go to home screen
			driver.executeScript("mobile:pressButton", ImmutableMap.of("name","home"));
			Thread.sleep(5000);
			//collect screens on home Screen
				List<WebElement> screens=(List<WebElement>) driver.findElement(By.xpath("//*[@name='Home Screen icons']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeIcon"));
				int count=screens.size();
				System.out.println("no of pages on homescreen is"+count);
				//go to each page from first page and count no of apps in each screen
				int totalappscount=0;
				for(int i=0;i<count;i++)
				{
					int screenappscount=screens.get(i).findElements(By.xpath("child::XCUIElementTypeIcon")).size();
					totalappscount=totalappscount+screenappscount;
					HashMap<String,Object> hm=new HashMap<String,Object>();
					hm.put("direction", "left");
					driver.executeScript("mobile:swipe",hm);
				}
				System.out.println("no of apps on home screen is"+totalappscount);
		//go to home and click on contacts apps icon
				driver.executeScript("mobile:pressHome", ImmutableMap.of("name","home"));
				Thread.sleep(5000);
				driver.executeScript("mobile:pressHome", ImmutableMap.of("name","home"));
				Thread.sleep(5000);
				for(int i=0;i<count;i++)
				{
					MobileElement e=(MobileElement) driver.findElement(By.xpath("//XCUIElementTypeIcon[@name='Contacts']"));
					if(e.isDisplayed())
					{
						e.click();
						break;
					}
					else
					{
						HashMap<String,Object> hm=new HashMap();
						hm.put("direction", "left");
						driver.executeScript("mobile:swipe", hm);
					}
				}
				Thread.sleep(5000);
				driver.terminateApp("com.apple.MobileAddressBook");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//no need to use closeApp() method because springboard app(homescreen) cannot terminate
		//stop appium server
		as.stop();
	}

}

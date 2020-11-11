package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test20 {

	public static void main(String[] args) throws Exception {
		//get environment 
		Scanner sc=new Scanner(System.in);
		System.out.println("enter environment");
		String x=sc.nextLine();
		RemoteWebDriver driver;
		//specific code as per environment
		if(x.equalsIgnoreCase("mobile"))
		{
			//start appium server
			Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium --chromedriver-executable D:\\Appium\\chromedriver.exe\" ");
			URL u=new URL("http://0.0.0.0:4723/wd/hub");
			//define desired capabilitites
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
			dc.setCapability("deviceName", "emulator-5554");
			dc.setCapability("plateformName", "android");
			dc.setCapability("plateformVersion", "10");
			//launch browser in device through appium server by creating driver object
			while(2>1) {
				try {
					driver=new AndroidDriver(u,dc);
					break;
				}
				catch(Exception ex)
						{
					}
			}//while
		}//if block
		else
		{
			System.out.println("enter browser name");
			String bn=sc.nextLine();
			if(bn.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(bn.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else if(bn.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			}
			else if(bn.equalsIgnoreCase("opera"))
			{
				WebDriverManager.operadriver().setup();
				driver=new OperaDriver();
			}
			else {
				WebDriverManager.iedriver().setup();
				driver=new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			Thread.sleep(5000);
		}
		//common code
		try {
			//launch site
			driver.get("http://newtours.demoaut.com");
			Thread.sleep(2000);
			driver.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//stop appium server
		if(x.equalsIgnoreCase("mobile"))
		{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		}
	}

}

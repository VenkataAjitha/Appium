package appium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Test80 {

	public static void main(String[] args) throws Exception {
		// start appium server programmatically for IOS
		/*AppiumServiceBuilder sb=new AppiumServiceBuilder();
		sb.usingAnyFreePort();
		sb.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node"));
		sb.withAppiumJS(new File("C:\\Users\\Sandeep\\AppData\\Roaming\\npm\\appium"));
		HashMap <String,String> ev=new HashMap();
		ev.put("PATH","/usr/local/bin:"+System.getenv("PATH"));
		sb.withEnvironment(ev);*/
		//start appium server programmatically in android device
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//Desired capabilities
				DesiredCapabilities dc=new DesiredCapabilities();
				dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
				dc.setCapability("deviceName", "emulator-5554");
				dc.setCapability("platformName", "android");
				dc.setCapability("platformVersion", "8.1");
			AndroidDriver driver;
			while(2>1)
			{
				try
				{
				driver=new AndroidDriver(u,dc);
				break;
				}
				catch(Exception ex)
				{
					
				}
			}//while
			//Automation code
			try
			{
				Thread.sleep(5000);
				//create a text file to store browser logs
				File f=new File("chromelogs");
				FileWriter fw=new FileWriter(f);
				BufferedWriter bw=new BufferedWriter(fw);
				//launch site
				WebDriverWait wait=new WebDriverWait(driver,20);
				driver.get("https://www.gmail.com");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identifier")));
				LogEntries ls=driver.manage().logs().get("gmail console");
				for(LogEntry l:ls)
				{
					//messages in log entries is a Json content , that content converted as hashmap
					Json js=new Json();
					HashMap hp=js.toType(l.getMessage(), Json.MAP_TYPE);
					bw.write(l.getTimestamp()+"--------"+l.getLevel()+"-----"+hp.get("text"));
				}
				Thread.sleep(5000);
				bw.close();
				fw.close();
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			//close App
			driver.quit();
			//stop Appium Server
			Runtime.getRuntime().exec("taskKill /F /IM node.exe");
			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");

	}

}

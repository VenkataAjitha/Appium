package appium;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;

public class Test59 {

	

	public static void main(String[] args) throws Exception {
		//start appium server
	    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		
		//Desired capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "5.1");
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
			try {
				Thread.sleep(5000);
				By backupswitch=By.id("com.google.android.apps.photos:id/auto_backup_switch");
				By touchOutside=By.id("com.google.android.apps.photos:id/touch_outside");
				By keepOff=By.xpath("//*[@text='KEEP OFF']");
				By photo=By.xpath("//android.view.ViewGroup[contains(@content-desc,'photo taken')]");
				By trash=By.id("com.google.android.apps.photos:id/trash");
				By moveToTrash=By.xpath("//*[@text='MOVE TO TRASH']");
				By menu=By.xpath("//*[@content-desc='Show Navigation Drawer']");
				By settings=By.xpath("//*[@text='Settings']");
				By bo=By.xpath("//*[@text='Backup & Sysc']");
				By onoff=By.xpath("//*[@text='Off' or @text='On']");
				
				WebDriverWait wait=new WebDriverWait(driver,20);
				WebDriverWait shortwait=new WebDriverWait(driver,3);
				wait.until(ExpectedConditions.presenceOfElementLocated(backupswitch)).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(touchOutside)).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(keepOff)).click();
				//delete all existing pictures using infinite loop,break when can't find any more pictures
				try {
					while(2>1)
					{
						shortwait.until(ExpectedConditions.presenceOfElementLocated(photo)).click();
						shortwait.until(ExpectedConditions.presenceOfElementLocated(trash)).click();
						shortwait.until(ExpectedConditions.presenceOfElementLocated(moveToTrash)).click();
					}
				}
				catch(Exception ex)
				{
					System.out.println("all existing pics deleted");
				}
				File img=new File("D:\\SDETWorkspace\\Selenuimworkspace\\SeleniumFramworks\\play.png");
				driver.pushFile("/mnt/sdcard/pictures/"+img.getName(),img);
				Thread.sleep(2000);
				
				System.out.println("given pic upload successfully");
				wait.until(ExpectedConditions.presenceOfElementLocated(menu)).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(settings)).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(bo)).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(onoff)).click();
			}
			catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
			//close App
//			driver.quit();
//			//stop Appium Server
//			Runtime.getRuntime().exec("taskKill /F /IM node.exe");
//			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}

}

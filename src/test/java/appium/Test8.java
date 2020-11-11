package appium;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.zeroturnaround.zip.ZipUtil;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import io.appium.java_client.android.AndroidDriver;

public class Test8 {

	public static void main(String[] args) throws Exception {
		//Folder details
		File f=new File("D:\\Appium\\Test8Results");
		f.mkdir();
		
		//ATUTest Recorder
		SimpleDateFormat sf=new SimpleDateFormat("DD-MMM-YYYY-mm-ss");
		Date dt=new Date();
		ATUTestRecorder rec=new ATUTestRecorder(f.getAbsolutePath(),"videoon"+sf.format(dt), false);
		rec.start();
		// get phone number from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("enter phone number");
		String phonenum= sc.nextLine();
		//Start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//set desire capabilities
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion","10");
		dc.setCapability("appPackage", "com.android.dialer");
		dc.setCapability("appActivity", "com.android.dialer.main.impl.MainActivity");
		// launch object
		AndroidDriver driver;
		while(2>1) {
			try {
				driver=new AndroidDriver(u,dc);
				break;
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		//test Automation
		try {
			try {
				driver.findElement(By.xpath("//*[@content-desc='key pad']")).click();
				
			}
			catch(Exception e) {
				driver.findElement(By.xpath("//*[@content-desc='Addcall'")).click();
			}
			Thread.sleep(2000); 
			
			for(int i=0;i<phonenum.length();i++) {
				char d=phonenum.charAt(i);
				driver.findElement(By.xpath("//*[contains(@content-desc,'"+d+"')]")).click();
			}
			
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@content-desc='dial']")).click();
			Thread.sleep(5000);
			
		}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}		
	//close app
	driver.closeApp();
	//close Appium server
	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	
	rec.stop();
	//creating zip file of Test8Results
	File fo=new File("D:\\Appium\\Test8Results.zip");
	ZipUtil.pack(f, fo);
}
}

package appium;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test47_Face {
	public static void main(String[] args)throws Exception
	{
		//install "apidemo" app into device
		//path of C:\Users\Sandeep\AppData\Local\Android\Sdk\system-images\android-25\google_apis_playstore\x86\data\app\ApiDemos.apk
		//start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u=new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device and apidemo app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("plateformName", "android");
		dc.setCapability("plateformVersion", "7.1.1");
		dc.setCapability("appPackage", "com.example.android.apis");
		dc.setCapability("appActivity", "com.example.android.apis.graphics.FingerPaint");
		AndroidDriver driver;
		while(2>0)
		{
			try 
			{
			driver=new AndroidDriver(u,dc);
			break;
			}
			catch(Exception ex)
			{
				
			}
		}
		Thread.sleep(5000);
		int xs[]= {220,650,220,650,220,650,220,650};
		int ys[]={450,450,800,800,1150,1150,1500,1500};
		int altAngle;
		for(int i=0;i<8;i++)
		{
			if(i%2==0)
			{
				altAngle=-1;
			}
			else
			{
				altAngle=1;
			}
			Point head=new Point(xs[i],ys[i]);
			Point lefteye=head.moveBy(-50,-50);
			Point righteye=head.moveBy(50,-50);
			Point mouth=head.moveBy(0,50);
			Point sticker=head.moveBy(0,-70);
			drawCircle(driver,head,150,30,"full",altAngle);
			drawCircle(driver,lefteye,20,20,"full",altAngle);
			drawCircle(driver,righteye,20,20,"full",altAngle);
			drawCircle(driver,mouth,40,20,"half",altAngle);
			if(i%2!=0)
			{
			drawCircle(driver,sticker,5,20,"full",altAngle);
			}
		}
		//close app
		driver.closeApp();
		//stop appium server
		Runtime.getRuntime().exec("taskKill /f /IM node.exe");
		Runtime.getRuntime().exec("taskKill /f /IM cmd.exe");
	}

	private static void drawCircle(AndroidDriver driver, Point origin, double radius, int steps,String option,int angle)
	{
		Point firstPoint=new Point((int) (origin.x+radius),origin.y);
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence circle=new Sequence(finger,1);
		circle.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),firstPoint.x, firstPoint.y));
		circle.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		int nop=steps;
		if(option.equalsIgnoreCase("half"))
		{
			nop=steps/2;
		}
		
		for(int i=1;i<=nop;i++)
		{

			double theta=2*Math.PI*((double)i/steps)*angle;
			int x=(int) Math.floor(Math.cos(theta)*radius);
			int y=(int) Math.floor(Math.sin(theta)*radius);
			Point point=new Point(origin.x+x,origin.y+y);
			
			circle.addAction(finger.createPointerMove(Duration.ofMillis(20),PointerInput.Origin.viewport(),point.x,point.y));
			
		}
		circle.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(circle));
		
	}

}

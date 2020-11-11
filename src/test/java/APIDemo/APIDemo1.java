package APIDemo;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Interaction;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class APIDemo1 extends JPanel {

	private static final Graphics2D g = null;

	public static void main(String[] args) throws Exception {
		
		 //take radius value from keyboard
		Scanner sc=new Scanner(System.in);
		System.out.println("enter radius");
		int r=sc.nextInt();
	
	   
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
				 driver.findElementByXPath("//*[@content-desc='Graphics']").click();
				 Thread.sleep(2000);
				 driver.findElementByXPath("//*[@content-desc='FingerPaint']").click();
				 Thread.sleep(2000);
				 MobileElement ele= (MobileElement) driver.findElementById("android:id/content");
				 Point source=ele.getCenter();
				 System.out.println(source);
				
				
			      int width=ele.getSize().getWidth();
			      int heigth=ele.getSize().getHeight();
			      System.out.println("Width  "+width+"heigth  "+heigth);
			    
			      
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
	 				 
	 				 
				 //draw circle
			      // Consider a rectangle of size N*N 
			      int N = 2*r+1;
			      int x, y; // Coordinates inside the rectangle 
			      
			      // Draw a square of size N*N. 
			      for (int i = 0; i < N; i++) 
			      { 
			          for (int j = 0; j < N; j++) 
			          { 
			              // Start from the left most corner point 
			              x = i-r; 
			              y = j-r; 
			    
			              // If this point is inside the circle, print it 
			              if (x*x + y*y == r*r) {
			            	  	//Event -3(move pressed finger to other location
			            	  	Interaction i3=finger.createPointerMove(Duration.ofSeconds(1),PointerInput.Origin.viewport(), 540+x*10,1120+y*10);
		 				 		dragNDrop.addAction(i3);
		 				 		System.out.println((540+x*10)+"    " +(1120+y*10));
			              }
			               
			          } 
			    
			      } 
			      
			    //Event -4(release finger)
					 Interaction i7=finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg());
					 dragNDrop.addAction(i7);
					 //perform  events of sequence
					 driver.perform(Arrays.asList(dragNDrop));
				 
				 
				
				 
				 
				 
				Runtime.getRuntime().exec("taskKill /F /IM node.exe");
				Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}


}

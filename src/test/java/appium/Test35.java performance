package appium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test35 {

	public static void main(String[] args) throws Exception {
		//start Appium server
		  Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		  URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device and app
		  DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME, "");
//			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,AutomationName.ESPRESSO);
//			dc.setCapability("forceEspressRebuild","true");
			dc.setCapability("deviceName", "emulator-5554");
			dc.setCapability("plateformName", "android");
			dc.setCapability("plateformVersion", "10");
			dc.setCapability("appPackage", "com.vodqareactnative");
			dc.setCapability("appActivity", "com.vodqareactnative.MainActivity");
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
		//test Automation
			try {
				Thread.sleep(5000);
				//get details of related App
				System.out.println("About APP info into text file");
				Map<String,String> m1=driver.getAppStringMap();
				File f1=new File("appinfo.txt");
				FileWriter fw1=new FileWriter(f1);
				BufferedWriter bw1=new BufferedWriter(fw1);
				for(Map.Entry e:m1.entrySet())
				{
					bw1.write(e.getKey()+"="+e.getValue());
					bw1.newLine();
				}
				bw1.close();
				fw1.close();
				
				//get app session details in device
				System.out.println("About session details info in text file");
				Map<String,Object> m2=driver.getSessionDetails();
				File f2=new File("sessioninfo.txt");
				FileWriter fw2=new FileWriter(f2);
				BufferedWriter bw2=new BufferedWriter(fw2);
				for(Map.Entry e:m2.entrySet())
				{
					bw2.write(e.getKey()+"="+e.getValue());
					bw2.newLine();
				}
				bw2.close();
				fw2.close();
				
				//get settings stored for this test session
				
				System.out.println("About settings info");
				Map<String,Object> m3=driver.getSettings();
				File f3=new File("setting.txt");
				FileWriter fw3=new FileWriter(f3);
				BufferedWriter bw3= new BufferedWriter(fw3);
				for(Map.Entry e:m3.entrySet())
				{
					bw3.write(e.getKey()+"="+e.getValue());
					bw3.newLine();
				}
				bw3.close();
				fw3.close();
				//get performance types
				System.out.println("About performance types");
				List<String> l1=driver.getSupportedPerformanceDataTypes();
				for(String v:l1)
				{
					System.out.println(v);
				}
				//get Specific performance type details
					for(int i=0;i<l1.size();i++)
					{
						System.out.println("About"+l1.get(i)+"into text file");
						File f=new File(l1.get(i)+".txt");
						FileWriter fw=new FileWriter(f);
						BufferedWriter bw=new BufferedWriter(fw);
						List<List<Object>> l2=driver.getPerformanceData("com.vodqareactnative",l1.get(i),1000);
						for(int j=0;j<l2.size();j++)
						{
							for(int k=0;k<l2.get(j).size();k++)
							{
								try 
								{
								bw.write(l2.get(j).get(k).toString()+"\t");
								}
								catch(Exception e)
								{
									bw.write("null\t");
								}
							}
							bw.newLine();
						}
						bw.close();
						fw.close();
						
					}
					Thread.sleep(10000);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			//close App
			driver.closeApp();
			//stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskKill /F /IM cmd.exe");
	}

}

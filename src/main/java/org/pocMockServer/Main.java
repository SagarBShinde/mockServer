package org.pocMockServer;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\DevEnv\\testFramework\\workspace\\sampleCodes\\chromedriver.exe");
		
		MockHelper m= new MockHelper();
		String PROXY = "localhost:1090";
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY)
		.setSocksProxy(PROXY);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);
		
		WebDriver D;
		
		try{
			
			m.startProxy();
			ChromeOptions options = new ChromeOptions();
			D= new ChromeDriver(cap);
			D.manage().window().maximize();
			//D.get("https://login.salesforce.com/?locale=in");
			D.get("http://new2stg.mta.info/");
			System.out.println("Hello World");
			
			
			
		}finally{
			
			Thread.sleep(10000);
			m.stopProxy();
			
		}
		
		
		

	}

}

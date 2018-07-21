package org.pocMockServer;
import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.mockserver.client.proxy.ProxyClient;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpRequest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class TestProgram {
	
	private ClientAndProxy proxy;
	private ClientAndServer mockServer;
	
	
	public void startProxy() {
	    mockServer = startClientAndServer(1080);
	    proxy = startClientAndProxy(1090);
	}
	
	
	public void stopProxy() {
	    proxy.stop();
	    mockServer.stop();
	}


	
	
    public static void main( String[] args ) throws AWTException, InterruptedException, IOException
    {
    	TestProgram t= new TestProgram();
    	System.setProperty("webdriver.chrome.driver", "C:\\DevEnv\\testFramework\\workspace\\mockserverPoc\\src\\test\\resources\\chromedriver.exe");
    	t.startProxy();
    	String PROXY = "127.0.0.1:1090";
		Proxy proxy = new Proxy();
		  proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY)
		    .setSocksProxy(PROXY);
		  DesiredCapabilities cap = new DesiredCapabilities();
		  cap.setCapability(CapabilityType.PROXY, proxy);
    	
		  //WebDriver D= new ChromeDriver(cap);
		  WebDriver D= new ChromeDriver();
    	try{
    		
    		System.out.println( "Hello World!" );
    		WebDriverWait wait = new WebDriverWait(D, 120);
    		D.manage().window().maximize();
    		D.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
    		D.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    		D.get("http://new2stg.mta.info/");
    		//D.get("https://www.quora.com/");
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='edit-origin']")));
    		Thread.sleep(5000);
    		D.findElement(By.xpath("//*[@id='edit-origin']")).sendKeys("br");
    		Thread.sleep(2000);
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ui-id-3']/li[7]")));
    		D.findElement(By.xpath("//*[@id='ui-id-3']/li[7]")).click();
    		Thread.sleep(2000);
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='edit-dest']")));
    		D.findElement(By.xpath("//*[@id='edit-dest']")).sendKeys("abr");
    		Thread.sleep(2000);
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ui-id-4']/li[4]")));
    		D.findElement(By.xpath("//*[@id='ui-id-4']/li[4]")).click();
    		Thread.sleep(2000);
    		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='edit-submit']")));
    		D.findElement(By.xpath("//*[@id='edit-submit']")).click();;
    		
    		Expectation[] recordedExp = t.proxy.retrieveRecordedExpectations(HttpRequest.request());
    		System.out.println("Exp Length is:"+recordedExp.length); 
    		
    		
    	} finally 
    		{
    		
    		t.stopProxy();
    		D.quit();
    		}
    	
    	
    	
    	
    }
}

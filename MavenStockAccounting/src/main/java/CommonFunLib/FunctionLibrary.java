package CommonFunLib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import Utilities.PropertyFilesUtil;
import junit.framework.Assert;

public class FunctionLibrary 
{
	WebDriver dr;
	//startBrowser
	public static WebDriver startBrowser(WebDriver dr) throws Throwable
	{
		if(PropertyFilesUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\royal.a\\workspace\\MavenStockAccounting\\ExecutableFiles\\geckodriver.exe");
			dr=new FirefoxDriver();
		}else
			if(PropertyFilesUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\ExecutableFiles\\chromedriver.exe");
				dr=new ChromeDriver();
			}else
				if(PropertyFilesUtil.getValueForKey("Browser").equalsIgnoreCase("ie"))
				{
					System.setProperty("webdriver.ie.driver", "C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\ExecutableFiles\\IEDriverServer.exe");
					dr=new ChromeDriver();
				}
		return dr;
	}
	
	//Open Application
	public static void openApplication(WebDriver dr) throws Throwable
	{
		dr.get(PropertyFilesUtil.getValueForKey("url"));
		dr.manage().window().maximize();
	}
	
	// clickAction
	public static void clickAction(WebDriver dr, String locatorType,String locatorValue)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			dr.findElement(By.id(locatorValue)).click();
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				dr.findElement(By.name(locatorValue)).click();
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					dr.findElement(By.xpath(locatorValue)).click();
				}
	}
	
	
	//typeAction
	public static void typeAction(WebDriver dr, String locatorType,String locatorValue,String data)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			dr.findElement(By.id(locatorValue)).clear();
			dr.findElement(By.id(locatorValue)).sendKeys(data);
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				dr.findElement(By.name(locatorValue)).clear();
				dr.findElement(By.name(locatorValue)).sendKeys(data);
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					dr.findElement(By.xpath(locatorValue)).clear();
					dr.findElement(By.xpath(locatorValue)).sendKeys(data);
				}
	}
	
	//closeBrowser
	public static void closeBrowser(WebDriver dr)
	{
		dr.close();
	}
	
	//waitForElement
	public static void waitForElement(WebDriver dr, String locatorType,String locatorValue,String waittime)
	{
		WebDriverWait myWait=new WebDriverWait(dr, Integer.parseInt(waittime));
		
		if (locatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));	
				}
	}
	
	
	//scroll down page
	
	public static void pageDown(WebDriver dr)
	{
		Actions action=new Actions(dr);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
	
	// Move to Element
		
	public static void mouseclick(WebDriver dr)
	{
		Actions mouse=new Actions(dr);
		System.out.println();
		mouse.moveToElement(dr.findElement(By.id("mi_a_stock_items"))).build().perform();
		mouse.moveToElement(dr.findElement(By.id("mi_a_stock_categories"))).click().build().perform();
		
	}

	
	
	//CaptureData
	
	public static void captureData(WebDriver dr,String locatorType,String locatorValue) throws Throwable
	{
		String data="";
		if (locatorType.equalsIgnoreCase("id")) 
		{
			data=dr.findElement(By.id(locatorValue)).getAttribute("value");
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				data=dr.findElement(By.id(locatorValue)).getAttribute("value");
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					data=dr.findElement(By.id(locatorValue)).getAttribute("value");
				}
		
		FileWriter fw=new FileWriter("C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\CaptureData\\Data.txt");
				
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(data);
		bw.flush();
		bw.close();
	}
	
	//Table Validation
	
	public static void tableValidation(WebDriver dr,String column) throws Throwable
	{
		FileReader fr=new FileReader("C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\CaptureData\\Data.txt");
		
		BufferedReader br=new BufferedReader(fr);
		String exp_data=br.readLine();
		
		int colNum=Integer.parseInt(column);
		if (dr.findElement(By.xpath(PropertyFilesUtil.getValueForKey("Search.Panel"))).isDisplayed())
		{
			dr.findElement(By.xpath(PropertyFilesUtil.getValueForKey("Search.Panel"))).click();
			Thread.sleep(2000);
			dr.findElement(By.id(PropertyFilesUtil.getValueForKey("Search.Box"))).clear();
			dr.findElement(By.id(PropertyFilesUtil.getValueForKey("Search.Box"))).sendKeys(exp_data);
			dr.findElement(By.id(PropertyFilesUtil.getValueForKey("Search.Button"))).click();
		}else
		{
			dr.findElement(By.id(PropertyFilesUtil.getValueForKey("Search.Box"))).clear();
			dr.findElement(By.id(PropertyFilesUtil.getValueForKey("Search.Box"))).sendKeys(exp_data);
			dr.findElement(By.id(PropertyFilesUtil.getValueForKey("Search.Button"))).click();
		}
		
		WebElement webtable=dr.findElement(By.xpath(PropertyFilesUtil.getValueForKey("webtable")));
		List<WebElement> rows=webtable.findElements(By.tagName("tr"));
		for (int i = 1; i <= rows.size(); i++) 
		{
			String act_data=dr.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum+"]/div/span")).getText();
			System.out.println(act_data);
			
			Assert.assertEquals(exp_data, act_data);
			
			break;
		}
	}
	
	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
		
	}
	
	
	public static void main(String[] args) throws Throwable 
	{
		WebDriver dr=null;
		dr=FunctionLibrary.startBrowser(dr);
		FunctionLibrary.openApplication(dr);
		
		
	}
	
}

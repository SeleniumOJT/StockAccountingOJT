package CommonFunLib;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class StockLibs 
{
	WebDriver dr;
	String res;
	
	
	public String appLaunch(String url)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\ExecutableFiles\\chromedriver.exe");
		dr= new ChromeDriver();
		dr.get(url);
		dr.manage().window().maximize();
		dr.manage().deleteAllCookies();
		if (dr.findElement(By.id("username")).isDisplayed()) 
		{
		res="Pass";	
		}else
		{
		res="Fail";
		}
		return res;
	}
	public String appLogin(String uname,String pword) 
	{
	dr.findElement(By.id("username")).clear();
	dr.findElement(By.id("username")).sendKeys(uname);
	dr.findElement(By.id("password")).clear();
	dr.findElement(By.id("password")).sendKeys(pword);
	dr.findElement(By.id("btnsubmit")).click();
	if (dr.findElement(By.id("logout")).isDisplayed())
	{
	res="pass";	
	}else
	{
	res="fail";
	}
	return res;
}
	
	public String supplierCreation(String sName,String addr,String city,String count,String cPerson,String pNumber,String eMail,String mNumber,String notes) throws Throwable
	{
		
		dr.findElement(By.id("mi_a_suppliers")).click();
		dr.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]")).click();
		String exp_data=dr.findElement(By.id("x_Supplier_Number")).getAttribute("value");
		System.out.println(exp_data);
		Thread.sleep(2000);
		dr.findElement(By.id("x_Supplier_Name")).sendKeys(sName);
		dr.findElement(By.id("x_Address")).sendKeys(addr);
		dr.findElement(By.id("x_City")).sendKeys(city);
		dr.findElement(By.id("x_Country")).sendKeys(count);
		dr.findElement(By.id("x_Contact_Person")).sendKeys(cPerson);
		dr.findElement(By.id("x_Phone_Number")).sendKeys(pNumber);
		dr.findElement(By.id("x__Email")).sendKeys(eMail);
		dr.findElement(By.id("x_Mobile_Number")).sendKeys(mNumber);
		dr.findElement(By.id("x_Notes")).sendKeys(notes);
		Actions pageDown=new Actions(dr);
		pageDown.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
		dr.findElement(By.id("btnAction")).click();
		Thread.sleep(2000);
		dr.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(2000);
		dr.findElement(By.xpath("//*[@class='ajs-button btn btn-primary']")).click();
		
		if (dr.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed()) 
		{
			dr.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
			Thread.sleep(3000);
			dr.findElement(By.id("psearch")).clear();
			dr.findElement(By.id("psearch")).sendKeys(exp_data);
			dr.findElement(By.id("btnsubmit")).click();
		}else
		{
			Thread.sleep(3000);
			dr.findElement(By.id("psearch")).clear();
			dr.findElement(By.id("psearch")).sendKeys(exp_data);
			dr.findElement(By.id("btnsubmit")).click();
		}
		
		String act_data=dr.findElement(By.id("el1_a_suppliers_Supplier_Number")).getText();
		System.out.println(act_data);
		if (exp_data.equals(act_data)) 
		{
			res="Pass";	
		}else
		{
			res="Fail";
		}
			dr.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
			return res;
	}
		
		public void stockCateg(String name) throws Throwable
		{
			Actions mouse=new Actions(dr);
			mouse.moveToElement(dr.findElement(By.id("mi_a_stock_items"))).build().perform();
			mouse.moveToElement(dr.findElement(By.id("mi_a_stock_categories"))).click().build().perform();
			
			dr.findElement(By.xpath("//*[@class='btn btn-default ewAddEdit ewAdd btn-sm']")).click();
			dr.findElement(By.id("x_Category_Name")).sendKeys(name);
			dr.findElement(By.id("btnAction")).click();
			Thread.sleep(2000);
			dr.findElement(By.xpath("//*[@class='ajs-button btn btn-primary']")).click();
			Thread.sleep(2000);
			dr.findElement(By.xpath("//*[@class='ajs-button btn btn-primary']")).click();
			
			if (dr.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed()) 
			{
				dr.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
				dr.findElement(By.id("psearch")).sendKeys(name);
			}
		}
	
	public String appLogout() throws Throwable
	{
		dr.findElement(By.id("logout")).click();
		Thread.sleep(2000);
		dr.findElement(By.xpath("//*[text()='OK!']")).click();
		if (dr.findElement(By.id("username")).isDisplayed())
		{
			res="Pass";
		}else
		{
			res="Fail";
		}
			return res;
	}
	
	
	public void appClose()
	{
		dr.close();
	}
		
	public static void main(String[] args)
	{
		StockLibs app=new StockLibs();
		app.appLaunch("http://webapp.qedge.com");
	}
		
}

	  
	

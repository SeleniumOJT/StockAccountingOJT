package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadingData
{
	public static void main(String[] args) throws Throwable 
	{
		
	Properties cp=new Properties();
	FileInputStream fis= new FileInputStream("C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\PropertiesFile\\Environment.properties");
	cp.load(fis);
	
	System.out.println(cp.getProperty("Broswer"));
	System.out.println(cp.getProperty("url"));
	System.out.println(cp.getProperty("uname"));
	System.out.println(cp.getProperty("pwd"));
	}
}

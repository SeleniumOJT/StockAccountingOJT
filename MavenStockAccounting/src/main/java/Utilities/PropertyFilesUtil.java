package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFilesUtil 
{
	public static String getValueForKey(String key) throws Throwable
	{
		Properties cp=new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\PropertiesFile\\Environment.properties");
		
		cp.load(fis);
		return cp.getProperty(key);
	}
}

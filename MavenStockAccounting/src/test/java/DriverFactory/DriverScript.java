package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLib.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript 
{
	WebDriver dr;
	ExtentReports report;
	ExtentTest logger;
	
	
	public void startTest() throws Throwable
		
{
	ExcelFileUtil xl=new ExcelFileUtil();
	
	for (int i = 1; i <=xl.rowCount("MasterTestCases"); i++) 
	{
		String ModuleStatus="";
		
		if (xl.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
		{
			String TCModule=xl.getData("MasterTestCases", i, 1);
			
			report=new ExtentReports("C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\Reports\\"+TCModule+FunctionLibrary.generateDate()+".html");
			logger=report.startTest(TCModule);
			
			int rowCount=xl.rowCount(TCModule);
			
			for (int j = 1; j <=rowCount; j++) 
			{
			
				String Description=xl.getData(TCModule, j, 0);
				String Object_Type=xl.getData(TCModule, j, 1);
				String Locator_Type=xl.getData(TCModule, j, 2);
				String Locator_Value=xl.getData(TCModule, j, 3);
				String Test_Data=xl.getData(TCModule, j, 4);
				
				try
				{
				if (Object_Type.equalsIgnoreCase("startBrowser")) 
				{
					dr=FunctionLibrary.startBrowser(dr);
					logger.log(LogStatus.INFO, Description);
				}
				if (Object_Type.equalsIgnoreCase("openApplication"))
				{
					FunctionLibrary.openApplication(dr);
					logger.log(LogStatus.INFO, Description);
				}
				if (Object_Type.equalsIgnoreCase("typeAction"))
				{
					FunctionLibrary.typeAction(dr, Locator_Type, Locator_Value, Test_Data);
					logger.log(LogStatus.INFO, Description);
				}
				if (Object_Type.equalsIgnoreCase("clickAction")) 
				{
					FunctionLibrary.clickAction(dr, Locator_Type, Locator_Value);
					logger.log(LogStatus.INFO, Description);
				}
				if (Object_Type.equalsIgnoreCase("closeBrowser")) 
				{
					FunctionLibrary.closeBrowser(dr);
					logger.log(LogStatus.INFO, Description);
				}
				if (Object_Type.equalsIgnoreCase("waitForElement"))
				{
					FunctionLibrary.waitForElement(dr, Locator_Type, Locator_Value, Test_Data);
					logger.log(LogStatus.INFO, Description);
				}
				if(Object_Type.equalsIgnoreCase("pageDown"))
				{
					FunctionLibrary.pageDown(dr);
					logger.log(LogStatus.INFO, Description);
				}
				if(Object_Type.equalsIgnoreCase("mouseClick"))
				{
					FunctionLibrary.mouseclick(dr);
					logger.log(LogStatus.INFO, Description);
				}
				if(Object_Type.equalsIgnoreCase("captureData"))
				{
					FunctionLibrary.captureData(dr, Locator_Type, Locator_Value);
					logger.log(LogStatus.INFO, Description);
				}
				if (Object_Type.equalsIgnoreCase("tableValidation")) 
				{
					FunctionLibrary.tableValidation(dr, Test_Data);
					logger.log(LogStatus.INFO, Description);
				}
				
				xl.setData(TCModule, j, 5, "PASS");
				logger.log(LogStatus.PASS, Description+"           PASS");
				ModuleStatus="true";
				}catch (Exception e)
				{
					xl.setData(TCModule, j, 5, "FAIL");
					logger.log(LogStatus.FAIL, Description+"           FAIL");
					ModuleStatus="false";
		File srcFile=((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\ScreenShots\\"+TCModule+"  "+FunctionLibrary.generateDate()+".png"));		
					break;
				}
			}
			if (ModuleStatus.equalsIgnoreCase("true"))
			{
				xl.setData("MasterTestCases", i, 3, "PASS");
			}else
				if(ModuleStatus.equalsIgnoreCase("false"))
			{
				xl.setData("MasterTestCases", i, 3, "FAIL");
			}
			
			report.endTest(logger);
			report.flush();
		}
		else
		{
			xl.setData("MasterTestCases", i, 3, "Not Executed");
		}
	}
	
}
	}


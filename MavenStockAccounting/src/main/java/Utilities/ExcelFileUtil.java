package Utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{

	Workbook wb;
	
	//it will load Excel sheet
	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\TestInputs\\InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
	}
	
	//row Count
	
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	
	// column count
	
	public int colCount(String sheetname, int row)
	{
		
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	}

	@SuppressWarnings("deprecation")
	public String getData(String sheetname,int row,int column)
	{
		
		String data=" ";
		if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
			
		}else
		{
			
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
			return data;
	}
	
	//storing data into excel sheet and validation
	public void setData(String sheetname,int row,int column,String status) throws Throwable
	{
		
		Sheet sh=wb.getSheet(sheetname);
		Row rownum=sh.getRow(row);
		Cell cell=rownum.createCell(column);
		cell.setCellValue(status);
		
		if (status.equalsIgnoreCase("Pass")) 
		{
		// create Cell style
			CellStyle style=wb.createCellStyle();
			//create font
			Font font=wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.GREEN.index);
			//apply bold
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
		}else 
			if (status.equalsIgnoreCase("Fail")) 
		{
			CellStyle style=wb.createCellStyle();
			
			Font font=wb.createFont();
			
			font.setColor(IndexedColors.RED.index);
			
			font.setBold(true);
			
			style.setFont(font);
			
			rownum.getCell(column).setCellStyle(style);
		}else 
			if (status.equalsIgnoreCase("Not Executed")) 
		{
			
			CellStyle style=wb.createCellStyle();
		
			Font font=wb.createFont();
		
			font.setColor(IndexedColors.BLUE.index);
		
			font.setBold(true);
		
			style.setFont(font);
		
			rownum.getCell(column).setCellStyle(style);
			
		}
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\royal.a\\workspace\\MavenStockAccounting\\TestOutput\\OutputSheet.xls");
		wb.write(fos);
		fos.close();
	}

	public static void main(String[] args) throws Throwable 
	{
		ExcelFileUtil xl=new ExcelFileUtil();
		System.out.println(xl.rowCount("Sheet1"));
		System.out.println(xl.colCount("Sheet1", 1));
		System.out.println(xl.getData("Sheet1", 1, 1));
		
		xl.setData("Sheet1", 1, 2, "Pass");
		xl.setData("Sheet1", 2, 2, "Fail");
		xl.setData("Sheet1", 3, 2, "Not Executed");
		
	}
}



package test;

import com.futurebazaar.testing.util.*;

 import java.io.File;  
import java.io.FileNotFoundException;
 import java.io.FileOutputStream;  
 import java.io.IOException;  
   

 import org.apache.poi.hssf.usermodel.HSSFCell;  
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
 import org.apache.poi.hssf.usermodel.HSSFRichTextString;  
 import org.apache.poi.hssf.usermodel.HSSFRow;  
 import org.apache.poi.hssf.usermodel.HSSFSheet;  
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.hssf.util.HSSFColor;  
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

   
 // The following example code demonstrates how to create an Excel    
 // file using the org.apache.poi library and style the cell by setting  
 // its background color and adding a border.  
   
 public class TestDataExtract {  
   
     public static void main(String[] args) throws IOException {  
   
    	 Workbook wb = new XSSFWorkbook();
    	    Sheet sheet = wb.createSheet("new sh");

    	    // Create a row and put some cells in it. Rows are 0 based.
    	    Row row = sheet.createRow((short) 1);

    	    // Aqua background
    	    CellStyle style = wb.createCellStyle();
    	   //style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
    	   // style.setFillPattern(CellStyle.BIG_SPOTS);
    	    Cell cell = row.createCell((short) 1);
    	    cell.setCellValue("X");
    	    cell.setCellStyle(style);

    	    // Orange "foreground", foreground being the fill foreground not the font color.
    	    style = wb.createCellStyle();
    	    style.setFillForegroundColor(IndexedColors.RED.getIndex());
    	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    	    cell = row.createCell((short) 2);
    	    cell.setCellValue("X");
    	    cell.setCellStyle(style);

    	    // Write the output to a file
    	    FileOutputStream fileOut = new FileOutputStream("E:\\workbook.xlsx");
    	    wb.write(fileOut);
    	    fileOut.close();

     }
 }  

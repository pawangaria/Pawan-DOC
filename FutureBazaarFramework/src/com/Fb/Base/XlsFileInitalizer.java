package com.Fb.Base;

import com.Fb.UtilityFiles.Xls_Reader;

public class XlsFileInitalizer {
	
	public static Xls_Reader suiteBseXls=null;
	
	public void Initalize_XlsTestCase()
	{
		suiteBseXls = new Xls_Reader(System.getProperty("user.dir")+"\\TestCaseXLSfile\\Suite.xlsx");
		
		
		
		
	}

}

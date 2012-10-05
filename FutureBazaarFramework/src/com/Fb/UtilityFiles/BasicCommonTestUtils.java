package com.Fb.UtilityFiles;

import java.util.ArrayList;
import java.util.Map;

public class BasicCommonTestUtils {
	
	
	 public static String comparison(ArrayList arraylist,String value)
	 {
		 
		 
		 for(int i=0;i<arraylist.size();i++)
    	 {
			 
    		if(!arraylist.get(i).equals(value))
    			
    		{
    			return "Price Values does not match for the "+arraylist.get(i);
    		}
    	 }
		 return "All Price Values matched";
	 }

	 
	  
     public static String removeCharacters(String text, String charsToKeep) {  
         StringBuffer buffer = new StringBuffer();  
         for(int i = 0; i < text.length(); i++) {  
             char ch = text.charAt(i);  
             if(charsToKeep.indexOf(ch) > -1) {  
                buffer.append(ch);  
             }  
         }  
         return buffer.toString();  
     }  
       
     public static String comparisonmap( Map<String, String> m1,String value)
 	{
 	for (Map.Entry<String, String> entry : m1.entrySet()) {
 	//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

 	if(!entry.getValue().equals(value))

 	{
 	return "Price Values does not match for the "+entry.getKey();
 	}
 	}
 	return "All Price Values matched";
 	}
     
     
}

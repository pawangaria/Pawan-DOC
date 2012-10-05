package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


 public class Remove {  
	 public static int aa;
	 public static int bb;
 	public static int cc;
	 
  //public static  int[] arrayyy;
	 public static ArrayList arrayyy;
	 public static Map map;
	 
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
	 
	 public static String comparisonmap( Map<String, String> m1,String value)
	 {
		 for (Map.Entry<String, String> entry : m1.entrySet()) {
             System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
             
        		if(!entry.getValue().equals(value))
    			
    		{
    			return "Price Values does not match for the "+entry.getKey();
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
       
     public static void main(String[] args) {  
        // System.out.println(removeCharacters("Congrats on your purchase. Your Order ID is 5040407259.", "0123456789"));  
     
    	 //arrayyy = new ArrayList();
    	  	
    	
    	
    	 //arrayyy.add(aa);
    	 //System.out.println(arrayyy.size());
    	
    	 //System.out.println(map.get(aa));
    	
    	 Map<String, String> m1 = new HashMap<String, String>();
         
    	 
    	 //Map m1 = new HashMap();
         m1.put("Ankit", "8");
         m1.put("Kapil", "5");
         m1.put("Saurabh", "8");
         m1.put("Apoorva", "8");
         //System.out.println();
         System.out.println("Elements of Map");
         //System.out.print(m1);
         System.out.println(m1.size());
         System.out.println(m1.keySet());
       
         
         
         
    	System.out.println(comparisonmap(m1,"8"));
    	//System.out.println(comparison(arrayyy));
    	 
     }
    	 
     }  
 
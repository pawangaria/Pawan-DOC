package com.futurebazaar.sanity;

import java.sql.*;
public class ConnectToDB {
	
	public String DBurl="jdbc:mysql://172.16.114.2:3306/tinla";
	public String DBUserName="readuser";
	public String DBUserPassword="tin14s14v3";
	
	public Connection con =null; 
	public Statement statement;
	public ResultSet result;
	
	
	public Statement ConnectToProductionDB() throws ClassNotFoundException, SQLException
	{
		System.out.println("Connecting to database...");
		Class.forName("com.mysql.jdbc.Driver");
		
		 con = DriverManager.getConnection(DBurl,DBUserName,DBUserPassword);
		 statement = con.createStatement();
		 
		 return statement;
	}
	
	
	public Integer ReturnVerificationCode(String sqlQuesry) throws SQLException, ClassNotFoundException
	{  Integer VerificationCode=null;
	
		try{
		
		Statement statement = ConnectToProductionDB();
		// Getting the Result By the Given Query
		result = statement.executeQuery(sqlQuesry);
		while(result.next()){
		VerificationCode =result.getInt("verification_code");
		
		//System.out.println(VerificationCode);
		}
		
	}catch (Exception e) {
	     e.getMessage();	
  	}
     finally{
    	 
    	 //Closing the Resources one by one in the Finally Block
    	 result.close();
    	 statement.close();
    	 con.close();
    	 
    	 
     }
		//Return Verification Code 
		return VerificationCode;
	
	   
	
	}
	
	public String ReturnStatusCode(String sqlQuesry) throws SQLException, ClassNotFoundException
	{  String StatusCode=null;
	
		try{
		
		Statement statement = ConnectToProductionDB();
		// Getting the Result By the Given Query
		result = statement.executeQuery(sqlQuesry);
		while(result.next()){
			StatusCode =result.getString("support_state");
		
		//System.out.println(StatusCode);
		}
		
	}catch (Exception e) {
	     e.getMessage();	
  	}
     finally{
    	 
    	 //Closing the Resources one by one in the Finally Block
    	 result.close();
    	 statement.close();
    	 con.close();
    	 
    	 
     }
		//Return Verification Code 
		return StatusCode;
	
	   
	
	}
	
	
	public String ReturnSAPStatusCode(String sqlQuesry) throws SQLException, ClassNotFoundException
	{  String SAPStatusCode=null;
	
		try{
		
		Statement statement = ConnectToProductionDB();
		// Getting the Result By the Given Query
		result = statement.executeQuery(sqlQuesry);
		while(result.next()){
			SAPStatusCode =result.getString("status");
		
		//System.out.println(StatusCode);
		}
		
	}catch (Exception e) {
	     e.getMessage();	
  	}
     finally{
    	 
    	 //Closing the Resources one by one in the Finally Block
    	 result.close();
    	 statement.close();
    	 con.close();
    	 
    	 
     }
		//Return Verification Code 
		return SAPStatusCode;
	
	   
	
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		
		ConnectToDB conne = new ConnectToDB();
		
		//String SQL= "select verification_code  from orders_codorderverification where reference_order_id=5067724661";
		//System.out.println(conne.ReturnVerificationCode(SQL));
		String SQL2= "select status from orders_saporderitem where order_id=";
		System.out.println(conne.ReturnSAPStatusCode(SQL2));
		//String SQL="select support_state from orders_order where reference_order_id=5057165097";
		//System.out.println(conne.ReturnStatusCode(SQL));
		String aa = "5434532";
		System.out.println(aa.substring(3));
		
		
	}

}

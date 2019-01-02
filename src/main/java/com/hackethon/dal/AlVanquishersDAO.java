package com.hackethon.dal;

import java.util.HashMap;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;

public class AlVanquishersDAO {
	
	private static AlVanquishersDAO l_AlVanquishersDAO = null;
	
	public static AlVanquishersDAO getInstance() {
		
		if (null == l_AlVanquishersDAO) {
			l_AlVanquishersDAO = new AlVanquishersDAO();
		}
		return l_AlVanquishersDAO;
	}
	
	/**
	 * get the database connection & account information
	 * @return Account Resultset.
	 */
	 public static StringBuilder getAccount(String p_input) {
	    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
	    	
	        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
	        key.put("AccountNumber", new AttributeValue().withS(p_input));

	        GetItemRequest request = new GetItemRequest()
	            .withTableName("Account")
	            .withKey(key);
	        GetItemResult result = null;
	        StringBuilder l_builder = new StringBuilder();
	        
	        try {
	            result = client.getItem(request);
	            if (result != null && result.getItem() != null) {
	            	l_builder = new StringBuilder();
	            	l_builder.append(result.getItem().get("Name"))
	            			.append(result.getItem().get("AccountNumber"))	
	            			.append(result.getItem().get("Country"))
	            			.append(result.getItem().get("Currency"))
	            			.append(result.getItem().get("Status"))
	            			.append(result.getItem().get("Create Date"));
	                
	            } else {
	                System.out.println("No Account was found");
	            }
	        } catch (Exception e) {
	            System.err.println("Unable to retrieve data: ");
	            System.err.println(e.getMessage());
	        }
	        return l_builder;
	    }
	 
		/**
		 * get the database connection & account information
		 * @return Account Resultset.
		 */
		 public static StringBuilder getSecurity(String p_input) {
		    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		    	
		        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
		        key.put("SecurityNumber", new AttributeValue().withS(p_input));

		        GetItemRequest request = new GetItemRequest()
		            .withTableName("Security")
		            .withKey(key);
		        GetItemResult result = null;
		        StringBuilder l_builder = new StringBuilder();
		        
		        try {
		            result = client.getItem(request);
		            if (result != null && result.getItem() != null) {
		            	l_builder = new StringBuilder();
		            	l_builder.append(result.getItem().get("Create Date"))
		            			.append(result.getItem().get("CUSIP"))
		            			.append(result.getItem().get("Eligibility"))
		            			.append(result.getItem().get("ISIN"))
		            			.append(result.getItem().get("Name"))
		            			.append(result.getItem().get("SecurityNumber"));
		                
		            } else {
		                System.out.println("No Security was found");
		            }
		        } catch (Exception e) {
		            System.err.println("Unable to retrieve data: ");
		            System.err.println(e.getMessage());
		        }
		        return l_builder;
		    }
		 
			/**
			 * get the database connection & account information
			 * @return Account Resultset.
			 */
			 public static StringBuilder getTrade(String p_input) {
			    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
			    	
			        HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
			        key.put("ReferenceNumber", new AttributeValue().withS(p_input));

			        GetItemRequest request = new GetItemRequest()
			            .withTableName("Trade")
			            .withKey(key);
			        GetItemResult result = null;
			        StringBuilder l_builder = new StringBuilder();
			        
			        try {
			            result = client.getItem(request);
			            if (result != null && result.getItem() != null) {
			            	l_builder = new StringBuilder();
			            	l_builder.append(result.getItem().get("Account"))
			            			.append(result.getItem().get("Amount"))
			            			.append(result.getItem().get("Clearance Date"))
			            			.append(result.getItem().get("Location"))
			            			.append(result.getItem().get("Security"))
			            			.append(result.getItem().get("Settlement Date"))
			            			.append(result.getItem().get("Status"))
			            			.append(result.getItem().get("Trade Date"))
			            			.append(result.getItem().get("Units"))
			            			.append(result.getItem().get("ReferenceNumber"));
			                
			            } else {
			                System.out.println("No Trade was found");
			            }
			        } catch (Exception e) {
			            System.err.println("Unable to retrieve data: ");
			            System.err.println(e.getMessage());
			        }
			        return l_builder;
			    }		 
	 /**
	  * For local test
	  * @param args
	  */
	 public static void main(String args[]) {
		 System.out.println("Account details : " + AlVanquishersDAO.getInstance().getAccount("12345").toString());
		 System.out.println("Security details : " + AlVanquishersDAO.getInstance().getSecurity("12345").toString());
		 System.out.println("Trade details : " + AlVanquishersDAO.getInstance().getTrade("12345").toString());
		 
	 }

}

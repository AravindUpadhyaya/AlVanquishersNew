package com.amazonaws.lambda;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.hackethon.dal.AlVanquishersDAO;

public class AlVanquishersAccountLambda implements RequestHandler<JSONObject, Object> {

	/**
	 * Sample Input JSON from Lex
	 {
		messageVersion=1.0, 
		invocationSource=FulfillmentCodeHook, 
		userId=4aljyokb8jvz1x6u8c1usdaortzkcqsy, 
		sessionAttributes={}, 
		requestAttributes=null, 
		bot=
		{
			name=SampleBot, 
			alias=$LATEST, 
			version=$LATEST
		}, 
		outputDialogMode=Text, 
		currentIntent=
		{
			name=Account, 
			slots={}, 
			slotDetails={}, 
			confirmationStatus=Confirmed
		}, 
		inputTranscript=Account details
	}
	 */
	@Override
	public Object handleRequest(JSONObject input, Context context) {
		
/*		if(input.get("intentName").equals("Menu")) {
			JSONObject object = new JSONObject();
	        object.put("type", "ElicitIntent");
	        //object.put("intentName", ((HashMap) input.get("slots")).get("MenuDetails").toString());
	        object.put("intentName", "AccountDetails");
	        
	        JSONObject l_objectDialogAction = new JSONObject();      
	        l_objectDialogAction.put("dialogAction", object);
	        
	        return l_objectDialogAction; 
		}*/
		
			String inputValue = (String) input.get("inputTranscript");
			
			StringBuilder accountDetails = AlVanquishersDAO.getInstance().getAccount(inputValue);
			
			String contentType = "PlainText";
	        String full = "Fulfilled";
	        
	        JSONObject object = new JSONObject();
	        object.put("type", "ConfirmIntent");
	        object.put("intentName", "AccountDetails");
	        
	        
	        
	        JSONObject slots = new JSONObject();
	        slots.put("AccountNumber", inputValue);
	        
	        object.put("slots", slots);
	        //object.put("fulfillmentState", full);
	        
	        JSONObject message = new JSONObject();
	        message.put("contentType", contentType);
	        message.put("content", accountDetails);
	        
	        object.put("message", message);
	        //object.put("responseCard", "{}");
	        
	        JSONObject objectDialogAction = new JSONObject();      
	        //objectDialogAction.put("sessionAttributes", "");
	        objectDialogAction.put("dialogAction", object);
	       
	        return objectDialogAction;
	}

}

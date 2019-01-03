package com.amazonaws.lambda;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.hackethon.dal.AlVanquishersDAO;

/**
 * 
 * @author aravind
 *
 */
public class AlVanquishersTradeSettlementLambda implements RequestHandler<JSONObject, Object> {

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
	 *
	 */
	@Override
	public Object handleRequest(JSONObject input, Context context) {
		
			String inputValue = (String) input.get("inputTranscript");
			
			/**
			 * Call the MQ put code or any Integration API for connecting to legacy systems.
			 */
			
			String contentType = "PlainText";
	        String full = "Fulfilled";
	        
	        JSONObject object = new JSONObject();
	        object.put("type", "ConfirmIntent");
	        object.put("intentName", "TradeSettlements");
	        
	        
	        
	        JSONObject slots = new JSONObject();
	        slots.put("TransactionNumber", inputValue);
	        
	        object.put("slots", slots);
	        //object.put("fulfillmentState", full);
	        
	        JSONObject message = new JSONObject();
	        message.put("contentType", contentType);
	        message.put("content", "Trade with reference number " + inputValue + " has been sent for manual settlement.");
	        
	        object.put("message", message);
	        //object.put("responseCard", "{}");
	        
	        JSONObject objectDialogAction = new JSONObject();      
	        //objectDialogAction.put("sessionAttributes", "");
	        objectDialogAction.put("dialogAction", object);
	       
	        return objectDialogAction;
	}

}

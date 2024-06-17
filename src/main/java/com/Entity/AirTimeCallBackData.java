package com.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirTimeCallBackData {
	
	  private String username;
	    private String password;
	    private String amount;
	    private String currency;
	    private String narration;
	    private int final_status;
	    private String order_id;
	    private String transaction_id;
	    private String payer_number;
	    private String account_number;
	    private String response_code;
	    private String response_message;

}

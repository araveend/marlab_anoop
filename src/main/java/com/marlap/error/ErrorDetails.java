package com.marlap.error;

import java.util.Date;

public class ErrorDetails {
	  private Date timestamp;
	  private String message;
	  private String details;

	   ErrorDetails(Date timestamp, String message, String details) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	  }

	
	  
	  
}

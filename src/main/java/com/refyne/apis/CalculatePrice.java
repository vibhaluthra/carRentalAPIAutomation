package com.refyne.apis;

import com.refyne.core.API;

public class CalculatePrice extends API{

	public CalculatePrice(String carId, String fromDateTime, String toDateTime) {
		methodType = MethodType.GET;
		basePath="/car/calculate-price/"+carId+"/"+fromDateTime+"/"+toDateTime;
		
	}
	
	public CalculatePrice updateRequest(String authToken) {
		headers.put("Authorization", "Bearer "+authToken);
		body="";
		return this;
	}
}

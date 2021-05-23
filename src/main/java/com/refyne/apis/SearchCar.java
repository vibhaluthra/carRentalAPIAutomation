package com.refyne.apis;

import com.refyne.core.API;

public class SearchCar extends API{
	
	public SearchCar(String fromDate, String toDate){
		methodType = MethodType.GET;
		basePath = "/car/search-cars/"+fromDate+"/"+toDate;
		
	}
	
	public SearchCar updateRequest(String authToken) {
		headers.put("Authorization", "Bearer "+authToken);
		body="";
		return this;
	}
}

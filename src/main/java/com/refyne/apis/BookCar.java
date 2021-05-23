package com.refyne.apis;

import com.refyne.core.API;

public class BookCar extends API{

	public BookCar() {
		methodType = MethodType.POST;
		basePath = "/car/book";
			}
	
	public BookCar updateRequest(String authToken,String car_id, String car_license_number, 
			int total_rent_bill, String from_date_time, String to_date_time) {
		body = "{ \"car_id\": \""+car_id+"\", \"car_license_number\": \""+car_license_number+"\", \"total_rent_bill\": "+total_rent_bill+", \"from_date_time\": \""+from_date_time+"\", \"to_date_time\": \""+to_date_time+"\"}";
		headers.put("Authorization", "Bearer "+authToken);
		return this;
	}
}

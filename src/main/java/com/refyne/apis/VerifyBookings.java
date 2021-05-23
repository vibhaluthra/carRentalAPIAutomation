package com.refyne.apis;

import com.refyne.core.API;

public class VerifyBookings extends API{

	public VerifyBookings() {
		
		methodType=MethodType.GET;
		basePath="/user/bookings";
	}
	
	public VerifyBookings updateRequest(String authToken) {
		headers.put("Authorization", "Bearer "+authToken);
		body="";
		return this;
	}
}

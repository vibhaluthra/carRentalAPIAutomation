package com.refyne.apis;

import com.refyne.core.API;

public class Login extends API{

	public Login() {
		methodType = MethodType.POST;
		basePath="/auth/login";
	}
	public Login updateRequest(String mobile, String password) {
		
		body = "{\"mobile\": \""+mobile+"\", \"password\" : \""+password+"\"}";
		return this;
		
	}
	
}

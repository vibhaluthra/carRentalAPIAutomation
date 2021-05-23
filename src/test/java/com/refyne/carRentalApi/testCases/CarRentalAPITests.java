package com.refyne.carRentalApi.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.refyne.apis.BookCar;
import com.refyne.apis.CalculatePrice;
import com.refyne.apis.Login;
import com.refyne.apis.SearchCar;
import com.refyne.apis.VerifyBookings;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CarRentalAPITests {

	
	@Test
	public void bookCar_Flow() {
		//Login API
		Login login = new Login().updateRequest("9988776655", "testuser@123");
		Response loginResponse = login.execute();
		Assert.assertEquals(loginResponse.getStatusCode(), 200);
		String authToken = loginResponse.jsonPath().get("authToken");
		System.out.println("AuthToken:" +authToken);
		
		//Search Car API
		String fromDateTime = "2021-05-23";
		String toDateTime="2021-05-24";
		SearchCar searchCar = new SearchCar(fromDateTime, toDateTime).updateRequest(authToken);;
		
		Response searchCarResponse = searchCar.execute();
		Assert.assertEquals(searchCarResponse.getStatusCode(), 200);
		String carID = searchCarResponse.jsonPath().get("available_cars[0]._id");
		String car_license_number = searchCarResponse.jsonPath().get("available_cars[0].car_license_number");
		System.out.println("Card Id:"+carID);
		System.out.println("car_license_number:"+car_license_number);
		
		//Calculate Price Of Car API
		CalculatePrice calculatePrice = new CalculatePrice(carID, fromDateTime, toDateTime).updateRequest(authToken);
		Response calculatePriceResponse = calculatePrice.execute();
		Assert.assertEquals(calculatePriceResponse.getStatusCode(), 200);
		int totalRentAmount = calculatePriceResponse.jsonPath().get("totalRentAmount");
		System.out.println("TotalRentAmount:"+totalRentAmount);
		
		//Book Car API
		BookCar bookCar = new BookCar().updateRequest(authToken, carID,car_license_number ,totalRentAmount, fromDateTime, toDateTime);
		Response bookCarResponse = bookCar.execute();
		Assert.assertEquals(bookCarResponse.getStatusCode(), 200);
		boolean errorFlag = bookCarResponse.jsonPath().get("error");
		
		
		if(!errorFlag) {
			//Verify Bookings API
			VerifyBookings verifyBookings = new VerifyBookings().updateRequest(authToken);
			Response verifyBookingsResponse = verifyBookings.execute();
			Assert.assertEquals(verifyBookingsResponse.getStatusCode(), 200);
			boolean errorFlagForVerifyBookings = verifyBookingsResponse.jsonPath().get("error");
			Assert.assertEquals(errorFlagForVerifyBookings, false);
			
		}else {
			throw new RuntimeException((String) bookCarResponse.jsonPath().get("message"));
		}
		
	}
	
}
;
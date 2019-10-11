package com.mobiquity.travelapi;

import io.swagger.client.ApiClient;
import io.swagger.client.api.TripsApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApiApplication.class, args);
		System.out.println("Hello other world");
		test();
	}

	private static void test() {
		ApiClient apiClient = new ApiClient();
//		apiClient.setApiKey("a3db7e8808944380b20408e9742c86ab");
		apiClient.setApiKeyPrefix("a3db7e8808944380b20408e9742c86ab");
		apiClient.setBasePath("gateway.apiportal.ns.nl/public-reisinformatie/api/v3");
		TripsApi tripsApi = new TripsApi(apiClient);
		try {
			tripsApi.getTrips(null, null, null, null, null, null, null,
					"2019-10-07T16L25:00+0200", null, null, null, null, null, null,
					null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null,
					null, null, null, null, null, null, null,
					null, null, "8400282", "8400056", null, null, null, null, null,
					null, null, null, null, null, null, null, null,
					null, null, null, null);
		} catch (Exception e) {
			System.out.println("In here!");
			System.out.println(e.getLocalizedMessage());
		}


		System.out.println("Wait here");
	}

}

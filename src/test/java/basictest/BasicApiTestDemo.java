package basictest;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resources.payLoad;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicApiTestDemo {

//	public static void main(String[] args) {
	
	@Test
	public void verifyAPIFlow() {
		RestAssured.baseURI = "https://rahulshettyacademy.com"; // Set the base URI
		
		// Validate add place works and get the response String
		// Given - All inputs
		// When - Submit the API - resource, http
		// Then - Validate Response
		String postResponseString = given().queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body(payLoad.getBodyForPostPlace())
		.when().post("/maps/api/place/add/json")
		.then()
		.assertThat()
		.statusCode(200)
		.body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(postResponseString);
		
		// Get data from response Json using JsonPath
		JsonPath jsonPostResponse = new JsonPath(postResponseString); // To Parse Json
		String responsePlaceId = jsonPostResponse.getString("place_id");
		System.out.println(responsePlaceId);
		System.out.println(payLoad.getBodyForUpdatePlace(responsePlaceId));
		// Update Place API
		String putResponseString = given().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(payLoad.getBodyForUpdatePlace(responsePlaceId))
				.when().put("/maps/api/place/update/json")
				.then().assertThat()
				.statusCode(200)
				.extract().response().asString();
		
		JsonPath jsonPutResponse = new JsonPath(putResponseString);
		System.out.println(jsonPutResponse.getString("msg"));
		
		// Get API Request
		String getResponseString = given().queryParam("key", "qaclick123")
				.queryParam("place_id", responsePlaceId)
				.header("User-Agent", "PostmanRuntime/7.24.1")
				.when().get("/maps/api/place/get/json")
				.then().assertThat()
				.statusCode(200)
				.extract().response().asString();
		JsonPath jsonGetResponse = new JsonPath(getResponseString);
		System.out.println(jsonGetResponse.getString("location.latitude"));
		System.out.println(jsonGetResponse.getString("address"));
		
		Assert.assertEquals(jsonGetResponse.getString("address"), "70 Update walk, USA");
		Assert.assertNotEquals(jsonGetResponse.getString("address"), "29, side PayLoad, cohen 09");
		
		// Delete Request
		String deleteResponseString = given().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(payLoad.getBodyForDeletePlace(responsePlaceId))
				.when().delete("/maps/api/place/delete/json")
				.then().assertThat()
				.statusCode(200)
				.assertThat()
				.body("status", equalTo("OK"))
				.extract().response().asString();
		
		JsonPath jsonDeleteResponse = new JsonPath(deleteResponseString);
		System.out.println(jsonDeleteResponse.getString("status"));
		
		// Verify using Get Request that place is actually removed
		getResponseString = given().queryParam("key", "qaclick123")
				.queryParam("place_id", responsePlaceId)
				.header("User-Agent", "PostmanRuntime/7.24.1")
				.when().get("/maps/api/place/get/json")
				.then().assertThat()
				.statusCode(404)
				.extract().response().asString();
		jsonGetResponse = new JsonPath(getResponseString);
		System.out.println(jsonGetResponse.getString("msg"));
	}
	

}

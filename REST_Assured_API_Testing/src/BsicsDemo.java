import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import files.*;

//import org.testng.Assert;

//import files.ReUsableMethods;
import files.Payload;

public class BsicsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		RestAssured.baseURI= "https://rahulshettyacademy.com"; 
		String response= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();// extracting response in string variable 
		
		System.out.println("response is:"+response);
		
		
		JsonPath js=ReusableMethods.stringTOjson(response); // parse to JSON(String to Json)
		String placeId=js.getString("place_id");
		String scopename=js.getString("scope");
		
		System.out.println("place id is: "+placeId);
		System.out.println("scope name is:"+scopename);
		
		//Update Place
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
	String getPlaceResponse= given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
	
	JsonPath js1=ReusableMethods.stringTOjson(getPlaceResponse); 
	String updatedAddress =js1.getString("address");
	System.out.println(updatedAddress);
	
	Assert.assertEquals(updatedAddress, "Pacific ocean"); // testng assertion
	//Cucumber Junit, Testng  */
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	}

}

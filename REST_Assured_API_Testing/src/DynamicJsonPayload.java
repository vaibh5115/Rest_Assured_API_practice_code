import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import files.*;
import static io.restassured.RestAssured.*;


public class DynamicJsonPayload 
{
	@Test
	public void addBook()
	{
	String response=RestAssured.baseURI="http://216.10.245.166";
	given().log().all().header("Content-Type","application/json")
	.body(Payload.AddBook())
	.when()
	.post("/Library/Addbook.php")
	.then().log().all().assertThat().statusCode(200)
	.extract().response().asString();
	
	JsonPath js=ReusableMethods.stringTOjson(response);
	String bookID=js.get("ID");
	System.out.println(bookID);
	
	
	}
	
	
}

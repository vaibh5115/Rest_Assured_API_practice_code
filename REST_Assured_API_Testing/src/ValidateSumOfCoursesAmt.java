import org.testng.Assert;
import org.testng.annotations.Test;
import files.*;
import io.restassured.path.json.JsonPath;

public class ValidateSumOfCoursesAmt 
{
	@Test
	public void SumOfCoursesAmt()
	{
		JsonPath js= new JsonPath(Payload.CoursePrice());
		int count=js.getInt("courses.size()");
		int sum=0;
		for(int i=0;i<count;i++)
		{
			int Courseprice=js.get("courses["+i+"].price");
			int numCopies=js.get("courses["+i+"].copies");
			int price=Courseprice*numCopies;
			sum=sum+price;
	
		}
		System.out.println("All courses total price is: "+sum);
		
		int purchaseAmount=js.get("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
		
	}

}

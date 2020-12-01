import io.restassured.path.json.JsonPath;
import files.*;


public class ComplexJsonParsing 
{
	public static void main(String args[])
	{
		// print the number of courses
		JsonPath js= new JsonPath(Payload.CoursePrice());
		int count=js.getInt("courses.size()");
		System.out.println("Number of courses are: "+count);
		
		//print the purchase amount of courses
		
		int purchaseAmt=js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amount is: "+purchaseAmt);
		
		//print the title first course
		
		String title=js.get("courses[0].title");
		
		System.out.println("1st course title is: "+title);
		
		//print the titles of all courses and their prices
		
		for(int i=0;i<count;i++)
		{
			
		String allCoursesTitles=js.get("courses["+i+"].title");
		System.out.println("Course title: "+allCoursesTitles);
		int price=js.get("courses["+i+"].price");
		System.out.println("Course price: "+price);
		}
		
		//print the number of copies sold by RPA course
		for(int j=0;j<count;j++)
		{
			
			String allCoursesTitles1=js.get("courses["+j+"].title");
			if(allCoursesTitles1.equalsIgnoreCase("RPA"))
			{
			int numCopies=js.get("courses["+j+"].copies");
			System.out.println("Number of copies sold by RPA course is: "+numCopies);
			}
		
		}
		
	}

}

package learning;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class jsonSandbox {

    @Test
    public void sumOfCourses(){
    //public static void main(String[] args){
        JsonPath js = new JsonPath(payloads.CoursePrice());

        //Get number of courses
        int numCourses = js.getInt("courses.size()");

        //Get purchase amount
        int puchaseTotal = js.getInt("dashboard.purchaseAmount");

        //Print Title of the first course
        System.out.println("The first course is: "+js.getString("courses[0].title"));

        //Print All course titles and their respective Prices
        for(int i=0;i<numCourses;i++){
            System.out.print(js.getString("courses["+i+"].title"));
            System.out.println(" - $"+js.getString("courses["+i+"].price"));
        }

        //Print no of copies sold by RPA Course
        for(int i=0;i<numCourses;i++){
            String title = js.getString("courses["+i+"].title");
            if(title.equalsIgnoreCase("RPA")){
                System.out.println("Number of RPA copies: "+js.getString("courses["+i+"].copies"));
                break;
            }
        }

        //Verify if Sum of all Course prices matches with Purchase Amount
        int total = 0;
        for(int i=0;i<numCourses;i++){
            total += (js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies"));
        }
        Assert.assertEquals(puchaseTotal,total);
        System.out.println("Total amount = "+total);
    }
}

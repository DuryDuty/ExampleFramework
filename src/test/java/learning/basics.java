package learning;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class basics {
    public static void main(String[] args){
        System.out.println("Cheesecake");

        //given - inputs
        //when - API submit
        //Then - validation
        RestAssured.baseURI= "https://rahulshettyacademy.com/";
        given().log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payload.ReturnPayload())
        .when().post("maps/api/place/add/json")
        .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("scope",equalTo("APP"))
                .header("server","Apache/2.4.18 (Ubuntu)");


    }
}

package learning;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import utils.ReusableFunctions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class basics {
    @Test
    public void basicAPI(){
        RestAssured.baseURI= "https://rahulshettyacademy.com/";

        //Add place through API
        String response =
        given().log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(payloads.AddPlace())
        .when().post("maps/api/place/add/json")
        .then()
                .assertThat()
                .statusCode(200)
                .body("scope",equalTo("APP"))
                .header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();

        String placeID = new JsonPath(response).getString("place_id");

        //Update place through API
        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type","application/json")
                .body(payloads.UpdatePlace(placeID))
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all()
                    .statusCode(200).body("msg",equalTo("Address successfully updated"));

        //Get updated place through API
        String updatedPlace = given()
                .queryParam("key", "qaclick123").queryParam("place_id",placeID)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().log().all()
                .statusCode(200).extract().response().asString();

        String updatedAddress = new JsonPath(updatedPlace).getString("address");
        ReusableFunctions.genericCompare("69 Summer walk, USA",updatedAddress);
    }

    @Test
    public void specBuildAPI(){
        RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
                .addQueryParam("key","qaclick123")
                .addHeader("Content-Type","application/json").build();

        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();

        //Add place specbuilder
        String response =
        given().log().all().spec(requestSpec)
                .body(payloads.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().spec(responseSpec).extract().response().asString();

        String placeID = new JsonPath(response).getString("place_id");
        System.out.println("Thingy done" + placeID);
    }
}

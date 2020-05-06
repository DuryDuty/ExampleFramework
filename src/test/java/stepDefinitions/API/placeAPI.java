package stepDefinitions.API;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import learning.AddPlace;
import learning.payloadBuilder;
import stepDefinitions.ReusableFunctions;

import static io.restassured.RestAssured.given;

public class placeAPI {
    ResponseSpecification responseSpec;
    RequestSpecification requestSpec;
    Response response;
    JsonPath js;

    @Given("Add place Payload")
    public void addPlacePayload() {
        //AddPlace placePayload = payloadBuilder.getAddPlace();
        RequestSpecification temp = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();
        //requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
        //        .setContentType(ContentType.JSON).build();
        //requestSpec = temp.given().spec(requestSpec).body(payloadBuilder.getAddPlace());
        requestSpec = given().spec(temp).body(payloadBuilder.getAddPlace());
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @When("user calls {string} with {string} request")
    public void userCallsWithRequest(String apiName, String httpType) {
        response = requestSpec.when().post("/maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();
    }

    @Then("the respose returns {string}")
    public void theResposeReturns(String statusCode) {
        ReusableFunctions.genericCompare(String.valueOf(response.getStatusCode()),statusCode);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        String res = response.asString();
        js = new JsonPath(res);
        ReusableFunctions.genericCompare(js.get(key).toString(),value);
    }
}

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
import learning.payloadBuilder;
import stepDefinitions.ReusableFunctions;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class placeAPI {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    Response response;
    JsonPath js;

    @Given("Add place Payload")
    public void addPlacePayload() throws FileNotFoundException {
        //requestSpec = given().spec(payloadBuilder.requestSpecification()).body(payloadBuilder.getAddPlaceBody());
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @Given("Add place Payload with {string}, {string}, {string}")
    public void addPlacePayloadWith(String name, String lang, String address) throws FileNotFoundException {
        requestSpec = given().spec(payloadBuilder.requestSpecification()).body(payloadBuilder.getAddPlaceBody(name,lang,address));
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @When("user calls {string} with {string} request")
    public void userCallsWithRequest(String apiName, String httpType) {
        response = requestSpec.when().post("/maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();
    }

    @Then("the response returns {string}")
    public void theResponseReturns(String statusCode) {
        ReusableFunctions.genericCompare(String.valueOf(response.getStatusCode()),statusCode);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        String res = response.asString();
        js = new JsonPath(res);
        ReusableFunctions.genericCompare(js.get(key).toString(),value);
    }
}

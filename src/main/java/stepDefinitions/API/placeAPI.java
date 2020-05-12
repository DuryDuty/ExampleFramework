package stepDefinitions.API;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.APIResources;
import utils.ReusableFunctions;
import utils.payloadBuilder;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class placeAPI {
    APIResources apiPath;
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    Response response;
    JsonPath js;
    String placeName;
    static HashMap<String, String> placeIDs = new HashMap<String, String>();


    @Given("payload generated with {string}, {string}, {string}")
    public void addPlacePayloadWith(String name, String lang, String address) throws FileNotFoundException {
        placeName = name;
        requestSpec = given().spec(payloadBuilder.requestSpecification()).body(payloadBuilder.getAddPlaceBody(name,lang,address));
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    }

    @When("user calls {string} with {string} request")
    public void userCallsWithRequest(String apiResource, String httpMethod) {

        switch (httpMethod.toLowerCase()){
            case "post":
                response = requestSpec.when().post(APIResources.valueOf(apiResource).getResource())
                        .then().spec(responseSpec).extract().response();
                break;
            case "get":
                response = requestSpec.when().get(APIResources.valueOf(apiResource).getResource())
                        .then().spec(responseSpec).extract().response();
                break;
            case "delete":
                response = requestSpec.when().delete(APIResources.valueOf(apiResource).getResource())
                        .then().spec(responseSpec).extract().response();
                break;
        }
        js = new JsonPath(response.asString());

    }

    @Then("the response returns {string}")
    public void theResponseReturns(String statusCode) {
        ReusableFunctions.genericCompare(String.valueOf(response.getStatusCode()),statusCode);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String key, String value) {
        ReusableFunctions.genericCompare(js.get(key).toString(),value);
    }

    @And("verify {string} added, by {string} request with parameter {string}")
    public void verifyAddedByWithKey(String placeName, String apiResource, String key) throws FileNotFoundException {
        requestSpec = given().spec(payloadBuilder.requestSpecification()).queryParam(key,js.get(key).toString());
        response = requestSpec.when().get(APIResources.valueOf(apiResource).getResource())
                .then().spec(responseSpec).extract().response();
        js = new JsonPath(response.asString());
    }

    @Given("GET request has {string} parameter")
    public void getRequestHasParameter(String key) throws FileNotFoundException {
        requestSpec = given().spec(payloadBuilder.requestSpecification()).queryParam(key,js.get(key).toString());
    }

    @And("place_id is stored in a hashmap")
    public void place_idIsStoredInAHashmap() {
        placeIDs.put(placeName,js.get("place_id").toString());
    }

    @Given("GET request has {string} place_id parameter")
    public void getRequestHasPlace_idParameter(String name) throws FileNotFoundException {
        String id = placeIDs.get(name);
        requestSpec = given().spec(payloadBuilder.requestSpecification()).queryParam("place_id",id);
        //responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        responseSpec = payloadBuilder.responseSpecification();
    }
}

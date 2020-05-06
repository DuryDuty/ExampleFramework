package learning;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class payloadBuilder {

	RequestSpecification req;

	
	payloadBuilder(){

	}

	
	public static AddPlace getAddPlaceBody(String name, String lang, String address){
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(lang);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		p.setLocation(l);

		return p;
	}

	public static RequestSpecification requestSpecification() throws FileNotFoundException {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/test/resources/config/global.properties")));
		} 	catch (IOException e) {
			e.printStackTrace();
		}

		RequestSpecification req = new RequestSpecBuilder().setBaseUri(prop.getProperty("apiURL")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();

		return req;
	}

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";


		RequestSpecification requestSpec =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();


		ResponseSpecification responseSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification res=given().spec(requestSpec)
				.body("null");

		Response response =res.when().post("/maps/api/place/add/json").
				then().spec(responseSpec).extract().response();

		String responseString=response.asString();
		System.out.println(responseString);

	}

}

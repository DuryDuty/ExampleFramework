package learning;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {

    @Test
    public void addDeleteBook(){
        RestAssured.baseURI="http://216.10.245.166/";

        String addBookResponse = given()
                .header("Content-Type","application/json")
                .body(payloads.addBook("Learning Stuff","josh","123","Beep Boop"))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = new JsonPath(addBookResponse);
        String bookID = js.getString("ID");
        System.out.println(bookID);

        given().queryParam("ID",bookID)
                .when().get("/Library/GetBook.php")
                .then().assertThat().statusCode(200).body("[0].author",equalTo("Beep Boop"));

        System.out.println(given()
                .header("Content-Type","application/json")
                .body(payloads.deleteBook(bookID))
                .when().post("/Library/DeleteBook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString());
    }
}

package utilites;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Sample_API {

    @Test
    public void get(){
        given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200);
    }
}

package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import utilites.APIConstants;

public class hooks {
    @Before
    public void Setup() {

       RestAssured.baseURI= APIConstants.BASE_URL;
        System.out.println("**** First Swagger API Starting Scenario ****");
    }

    @After
    public void tearDown() {
        System.out.println("**** First Swagger API Ending Scenario ****");
    }
}

package stepdef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import utilites.APIRequest;
import utilites.JsonReader;
import utilites.APIConstants;

public class PetStepdef {
    public String endpoint;
    public String requestBody;
    public Response response;
    public long petId;

    @Given("I have the pet endpoint")
    public void i_have_the_pet_endpoint() {
        endpoint = APIConstants.ENDPOINT;
    }

    @And("I have the pet payload from file {string}")
    public void i_have_the_pet_payload_from_file(String fileName) {
        String path = "src/test/resources/payloads/" + fileName;
        requestBody = JsonReader.readJsonFileAsString(path);
    }

    @When("I send POST request to create pet")
    public void i_send_post_request_to_create_pet() {
        response = APIRequest.postRequest(endpoint, requestBody);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatus) {
        Assert.assertEquals(expectedStatus.intValue(), response.getStatusCode());
    }

    @And("I store the pet id from the response")
    public void i_store_the_pet_id_from_the_response() {
        JsonPath jsonPath = response.jsonPath();
        petId = jsonPath.getLong("id");
        System.out.println("Created pet id: " + petId);
    }

    @When("I send GET request to fetch that pet")
    public void i_send_get_request_to_fetch_that_pet() {
        String getEndpoint = endpoint + "/" + petId;
        response = APIRequest.getRequest(getEndpoint);
    }

    @And("the pet name in response should be {string}")
    public void the_pet_name_in_response_should_be(String expectedName) {
        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("name");
        Assert.assertEquals(expectedName, actualName);
    }

    @When("I send PUT request to update pet using file {string}")
    public void i_send_put_request_to_update_pet_using_file(String fileName) {
        String path = "src/test/resources/payloads/" + fileName;
        String updatedBody = JsonReader.readJsonFileAsString(path);
        response = APIRequest.putRequest(endpoint, updatedBody);
    }

    @When("I send DELETE request to delete that pet")
    public void i_send_delete_request_to_delete_that_pet() {
        String deleteEndpoint = endpoint + "/" + petId;
        response = APIRequest.deleteRequest(deleteEndpoint);
    }
}

package utilites;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIRequest {
    public static RequestSpecification baseRequest() {
        return RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.fromContentType(APIConstants.CONTENT_TYPE));
    }

    // ========= GET (overloads) =========

    // Simple GET with only endpoint
    public static Response getRequest(String endpoint) {
        return baseRequest()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // GET with a path param: /pet/{id}
    public static Response getRequest(String endpoint, String pathParamName, String pathParamValue) {
        return baseRequest()
                .pathParam(pathParamName, pathParamValue)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // GET with a single query param: ?status=available
    public static Response getWithQuery(String endpoint, String queryParamName, String queryParamValue) {
        return baseRequest()
                .queryParam(queryParamName, queryParamValue)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // ========= POST (overloads) =========

    public static Response postRequest(String endpoint, String body) {
        return baseRequest()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    // POST where body comes from JSON file
    public static Response post(String endpoint, String jsonFilePath, boolean isFile) {
        String body = JsonReader.readJsonFileAsString(jsonFilePath);
        return postRequest(endpoint, body);
    }

    // ========= PUT (overloads) =========

    public static Response putRequest(String endpoint, String body) {
        return baseRequest()
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response put(String endpoint, String jsonFilePath, boolean isFile) {
        String body = JsonReader.readJsonFileAsString(jsonFilePath);
        return putRequest(endpoint, body);
    }

    // ========= DELETE (overloads) =========

    // DELETE with only endpoint – for things like /pet/{id} already built
    public static Response deleteRequest(String endpoint) {
        return baseRequest()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    // DELETE with path param, example: delete("/pet/{id}", "id", "123")
    public static Response deleteRequest(String endpoint, String pathParamName, String pathParamValue) {
        return baseRequest()
                .pathParam(pathParamName, pathParamValue)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}

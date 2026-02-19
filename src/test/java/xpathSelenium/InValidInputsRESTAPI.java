package xpathSelenium;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class InValidInputsRESTAPI {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.example.com/v1";
    }

    // 1. Boundary Value Analysis
    // Logic: Item quantity must be between 1 and 50.
    @Test
    public void testQuantityBoundaryExceeded() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"productId\": 101, \"quantity\": 51 }") // Just above max boundary
        .when()
            .post("/cart/add")
        .then()
            .statusCode(400)
            .body("error", equalTo("Maximum quantity allowed is 50"));
    }

    // 2. Negative Testing (Invalid Data)
    // Logic: Email must follow a valid pattern.
    @Test
    public void testInvalidEmailFormat() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"email\": \"wrong_email_format\", \"password\": \"Pass123\" }")
        .when()
            .post("/auth/register")
        .then()
            .statusCode(422)
            .body("message", containsString("Invalid email format"));
    }

    // 3. Auth & Permissions (RBAC)
    // Logic: Only Admins can delete users.
    @Test
    public void testUserCannotAccessAdminEndpoint() {
        String userToken = "eyJhbGciOiJIUzI1..."; // Valid JWT for a standard 'USER'

        given()
            .header("Authorization", "Bearer " + userToken)
        .when()
            .delete("/admin/users/99")
        .then()
            .statusCode(403) // Forbidden: Identified but not authorized
            .body("error", equalTo("Access Denied: Admin privileges required"));
    }
}
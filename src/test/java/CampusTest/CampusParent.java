package CampusTest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CampusParent {
    Faker faker = new Faker();
    RequestSpecification requestSpecification;
    String CountryID;

    @BeforeClass
    public void Setup() {

        baseURI="https://test.mersys.io";
        HashMap<String, String> loginData = new HashMap<>();
        loginData.put("username", "Campus25");
        loginData.put("password", "Campus.2524");
        loginData.put("rememberMe", "true");

        String token =
                given()
                        .contentType(ContentType.JSON)
                        .body(loginData)

                        .when()
                        .post("/auth/login")


                        .then().log().body()
                        .statusCode(200)
                        .extract().path("access_token");

        System.out.println(token);

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }
}

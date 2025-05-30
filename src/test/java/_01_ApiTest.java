import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class _01_ApiTest {
    @Test
    public void Test(){
        given().

                when().


                then();

    }

    @Test
    public void Test2(){
        given().

                when().
                get("http://api.zippopotam.us/us/90210").

                then()
                .log().body().statusCode(200); // status code 200 olmaz ise hata verecektir

    }

    @Test
    public void Test3() {
        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                .log()
                .body()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void checkCountryinResponseBody() {

        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                .log()
                .body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("country",equalTo("United States"))
        ;
    }

    @Test
    public void checkCountryinResponseBody1() {

        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                .log()
                .body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("places[0].state",equalTo("California"))
        ;
    }
}

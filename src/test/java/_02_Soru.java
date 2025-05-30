import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class _02_Soru {
    @Test
    public void Test(){
        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .log()
                .body()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("places.'place name'",hasItem("Dörtağaç Köyü")); //bütün place name lerin
                                                                            //içinde Dörtağaç köyü varmı

    }

    @Test
    public void Test1(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log()
                .body()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("places",hasSize(1))
                ;

    }

    @Test
    public void combiningTest(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log()
                .body()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("places",hasSize(1))
                .body("country",equalTo("United States"))
                .body("places[0].state",equalTo("California"))
        ;

    }
}

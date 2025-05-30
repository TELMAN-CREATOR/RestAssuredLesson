import Model.ZipCode;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiPojo {
    @Test
    public void extractJson(){

        ZipCode zipCode=
        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                .log().body()
                .extract().body().as(ZipCode.class)
        ;

        System.out.println(zipCode);
//        System.out.println(zipCode.getPlaces());
    }
}

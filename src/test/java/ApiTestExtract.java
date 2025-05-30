import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTestExtract {
    String country="";
    @Test(priority = 1)
    public void extractPath(){
         country=
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                .log()
                .body()
                .extract()
                .path("country")
                ;
        System.out.println(country);
    }

    @Test(priority = 2)
    public void Test(){
        System.out.println(country);
//        Assert.assertEquals(country,"United States");
    }
}

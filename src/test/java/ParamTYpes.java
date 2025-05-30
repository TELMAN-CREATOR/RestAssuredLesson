import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParamTYpes {
    @Test
    public void pathParam() {
        given()
                .pathParam("ulkeKOD", "us")
                .pathParam("rakam", "90210")
                .log().uri()

                .when()
                .get("http://api.zippopotam.us/{ulkeKOD}/{rakam}")

                .then().log().body();
    }

    @Test
    public void queryParam() {
        given()
                .param("page", 3)
                .log().uri()

                .when()
                .get("https://gorest.co.in/public/v1/users")


                .then().log().body();
    }

    @Test
    public void queryParamSoru() {
        for (int i = 1; i < 11; i++) {
            given()
                    .param("page", i)
                    .log().uri()

                    .when()
                    .get("https://gorest.co.in/public/v1/users")


                    .then().log().body()
                    .body("meta.pagination.page",equalTo(i));
        }
    }
}

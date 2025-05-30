import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTestSpesification {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    @BeforeClass
    public void SetUp(){
        requestSpecification=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.BODY)
                .build();

        responseSpecification=new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void Test(){
        given().spec(requestSpecification)

                .when()
                .get("https://gorest.co.in/public/v1/users")


                .then()
                .spec(responseSpecification);

    }

    @Test
    public void Test2(){

        given().spec(requestSpecification)


                .when()
                .get("https://gorest.co.in/public/v1/users")


                .then().spec(responseSpecification);

    }
}

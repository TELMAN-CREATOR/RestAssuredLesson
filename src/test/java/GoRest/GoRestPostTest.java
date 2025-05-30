package GoRest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GoRestPostTest {

    RequestSpecification requestSpecification;
    Faker random = new Faker();
    int UserId;

    @BeforeClass
    public void Setup() {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer f92bf3f56439b631d9ed928b3540968e747c8e75309c876420fb349cbb420ed1")
                .log(LogDetail.URI)
                .build();

    }

    @Test(dependsOnMethods = "PostUser")
    public void GetUserById() {
        String url = "https://gorest.co.in/public/v2/users/" + UserId;
        int gelenId =
                given()
                        .spec(requestSpecification)

                        .when()
                        .get(url)

                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().path("id");

        Assert.assertEquals(gelenId, UserId);

    }

    @Test
    public void PostUser() {

        // giden body ye ihtiyacım
//  giden body hazırlama :  1.Yöntem
//        String bodyUser="{" +
//                "  \"name\":\"{{$randomFullName}}\"," +
//                "  \"gender\":\"male\"," +
//                "  \"email\":\"{{$randomEmail}}\"," +
//                "  \"status\":\"active\"" +
//                "}";

//  giden body hazırlama :  2.Yöntem
//        User newUser2=new User();
//        newUser2.setName("İsmet Temur");
//        newUser2.setGender("male");
//        newUser2.setEmail("İsmetTemur@gmail.com");
//        newUser2.setStatus("active");

//  giden body hazırlama :  3.Yöntem
        String randomName = random.name().fullName();
        String randomemail = random.internet().emailAddress();

        HashMap<String, String> newUser = new HashMap<>();
        newUser.put("name", randomName);
        newUser.put("gender", "male");
        newUser.put("email", randomemail);
        newUser.put("status", "active");

        UserId =
                given()
                        .spec(requestSpecification)
                        .body(newUser)

                        .when()
                        .post("https://gorest.co.in/public/v2/users")


                        .then()
                        .statusCode(201)
                        .log().body()
                        .extract().path("id");

        System.out.println(UserId);

    }

    @Test(dependsOnMethods = "GetUserById")
    public void UpdateUser() {

        HashMap<String, String> uptUser = new HashMap<>();
        uptUser.put("name", "randomName");


        given()
                .spec(requestSpecification)
                .body(uptUser)


                .when()
                .put("https://gorest.co.in/public/v2/users/" + UserId)


                .then().statusCode(200)
                .log().body();


    }

    @Test(dependsOnMethods = "UpdateUser")
    public void DeleteUser() {

        given()
                .spec(requestSpecification)


                .when()
                .delete("https://gorest.co.in/public/v2/users/" + UserId)


                .then().statusCode(204);


    }

    @Test(dependsOnMethods = "DeleteUser")
    public void DeleteUserNegative() {
        try {


            int stacode =
                    given()
                            .spec(requestSpecification)


                            .when()
                            .delete("https://gorest.co.in/public/v2/users/" + UserId)


                            .then()
                            .extract().statusCode();
            System.out.println(stacode);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

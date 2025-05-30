package CampusTest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CampusCreateCountry extends CampusParent {

    String CountryID;

    HashMap<String, String> countryData = new HashMap<>();


    @Test
    public void CreateCountry() {

        countryData.put("id", null);
        countryData.put("name", faker.country().name());
        countryData.put("code", faker.country().countryCode2());
        countryData.put("translateName", null);
        countryData.put("hasState", null);

        CountryID =
                given()
                        .spec(requestSpecification)
                        .body(countryData)

                        .when()
                        .post("/school-service/api/countries")


                        .then()
                        .statusCode(201)
                        .log().body()
                        .extract().path("id");
        System.out.println(CountryID);

    }
    @Test(dependsOnMethods = "CreateCountry")
    public void CreateCountryNegative(){
        try {
            given().spec(requestSpecification)
                    .body(countryData)


                    .when()
                    .post("/school-service/api/countries")


                    .then()
                    .statusCode(400)
                    .log().body()
                    .extract().path("id");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(dependsOnMethods = "CreateCountry")
    public void GetCountryById(){
        given()
                .spec(requestSpecification)


                .when()
                .get("/school-service/api/countries/"+CountryID)

                .then().statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = "CreateCountry")
    public void UpdateCountry() {

        HashMap<String, String> countryData = new HashMap<>();
        countryData.put("id", CountryID);
        countryData.put("name", faker.country().name());
        countryData.put("code", faker.country().countryCode2());
        countryData.put("hasState", null);

        given()
                .spec(requestSpecification)
                .body(countryData)

                .when()
                .put("/school-service/api/countries")


                .then()
                .statusCode(200)
                .log().body();


    }

    @Test(dependsOnMethods = "UpdateCountry")
    public void DeleteAllCountry() {
        List<String>idler=
        given().spec(requestSpecification)

                .when()
                .get("/school-service/api/countries")

                .then()
                .log().body()
                .extract().path("id");
        System.out.println(idler);

        for (String id:idler) {

            given()
                    .spec(requestSpecification)

                    .when()
                    .delete("/school-service/api/countries/" + id)


                    .then()
                    .statusCode(200);
        }

    }


//    @Test(dependsOnMethods = "UpdateCountry")
//    public void DeleteCountry() {
//
//        given()
//                .spec(requestSpecification)
//
//                .when()
//                .delete("/school-service/api/countries/"+CountryID)
//
//
//                .then()
//                .statusCode(200);
//
//    }
//
//
//    @Test(dependsOnMethods ="DeleteCountry" )
//    public void DeleteCountryNegative() {
//
//        try {
//            given()
//                    .spec(requestSpecification)
//
//                    .when()
//                    .delete("/school-service/api/countries/" + CountryID)
//
//
//                    .then()
//                    .statusCode(400);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }

    // TODO: GetCountryId ->  get("school-service/api/countries/"+CountryID) bir tane country get
    //       AllCountryId ->  get("school-service/api/countries"); id leri list şeklinde alıcaksınız
    //1- GetCountryById yi Create den sonra ya ekleyiniz.
    //2- CreateCountryNegative   Create den sonra ya ekleyiniz.
    //3- Bütün Country leri siliniz. (Günün sorusu)


}

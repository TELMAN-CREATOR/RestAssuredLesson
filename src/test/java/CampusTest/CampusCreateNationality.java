package CampusTest;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CampusCreateNationality extends CampusParent {

        String CountryID;

        @Test
        public void CreateNationality() {


            HashMap<String, String> nationalityData = new HashMap<>();
            nationalityData.put("id", null);
            nationalityData.put("name", faker.address().country());
            nationalityData.put("translateName", null);


            CountryID =
                    given()
                            .spec(requestSpecification)
                            .body(nationalityData)

                            .when()
                            .post("/school-service/api/nationality")


                            .then()
                            .statusCode(201)
                            .log().body()
                            .extract().path("id");
            System.out.println(CountryID);

        }

        @Test(dependsOnMethods = "CreateNationality")
        public void UpdateNationality() {

            HashMap<String, String> nationalityData = new HashMap<>();
            nationalityData.put("id", CountryID);
            nationalityData.put("name", faker.address().country());
            nationalityData.put("translateName", null);

            given()
                    .spec(requestSpecification)
                    .body(nationalityData)

                    .when()
                    .put("/school-service/api/nationality")


                    .then()
                    .statusCode(200)
                    .log().body();


        }


        @Test(dependsOnMethods = "UpdateNationality")
        public void DeleteNationality() {

            given()
                    .spec(requestSpecification)

                    .when()
                    .delete("/school-service/api/nationality/"+CountryID)


                    .then()
                    .statusCode(200);

        }


        @Test(dependsOnMethods ="DeleteNationality" )
        public void DeleteNationalityNegative() {

            try {
                given()
                        .spec(requestSpecification)

                        .when()
                        .delete("/school-service/api/nationality/" + CountryID)


                        .then()
                        .statusCode(400);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    // TODO: GetNationalityId ->  get("school-service/api/nationality/"+NationalityID) bir tane nationality get
    //       AllNationalityId ->  get("school-service/api/nationality"); id leri list şeklinde alıcaksınız
    //1- GetNationalityId yi Create den sonra ya ekleyiniz.
    //2- CreateNationalityNegative   Create den sonra ya ekleyiniz.
    //3- Bütün Nationality leri siliniz. (Günün sorusu)
    }


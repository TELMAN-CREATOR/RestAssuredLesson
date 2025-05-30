import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class _03_ExtractingSorular {
    @Test
    public void extractingJsonPath2() {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint indne dönen
        // place dizisinin ilk elemanının state değerinin  "California"
        // olduğunu testNG Assertion ile doğrulayınız
        String state=
        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")



                .then().log().body()
                .extract().path("places[0].state");

        System.out.println(state);

        Assert.assertEquals(state,"California");
    }

    @Test
    public void extractingJsonPath3() {

                int limit=
                given()


                        .when()
                        .get("https://gorest.co.in/public/v1/users")



                        .then().log().body()
                        .extract().path("meta.pagination.limit")
                        ;

        System.out.println(limit);
        Assert.assertEquals(limit,10);
    }

    @Test
    public void extractingJsonPath4() {

        ArrayList<Integer> idler =
            given()


                    .when()
                    .get("https://gorest.co.in/public/v1/users")


                    .then().log().body()
                    .extract().path("data.id")

            ;
        System.out.println(idler);
        for (int i = 0; i <idler.size() ; i++) {
            if (idler.get(i)==7908888){
                Assert.assertEquals(idler.get(i),7908888);
            }
        }
        }

    @Test
    public void extractingJsonPath5() {

        ArrayList<String> names =
                given()


                        .when()
                        .get("https://gorest.co.in/public/v1/users")


                        .then().log().body()
                        .extract().path("data.name")

                ;
        System.out.println(names);
        Assert.assertTrue(names.contains("Smriti Reddy"));


    }


    @Test
    public void ResponseAll() {

        Response allData =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")


                        .then().log().body()
                        .extract().response()
                ;
        ArrayList<String>names=allData.path("data.name");
        ArrayList<Integer>idler=allData.path("data.id");
        int limit=allData.path("meta.pagination.limit");

        Assert.assertTrue(names.contains("Smriti Reddy"));
        Assert.assertTrue(idler.contains(7908888));
        Assert.assertEquals(limit,10);


    }

    }


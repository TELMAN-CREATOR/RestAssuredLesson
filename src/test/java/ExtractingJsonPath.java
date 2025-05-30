import Model.Data;
import Model.Place;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
public class ExtractingJsonPath {

    @Test
    public void extractingJsonPath(){
        int postcode=
        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                .extract().jsonPath().getInt("'post code'")
                ;
        System.out.println(postcode);

    }

    @Test
    public void extractingJsonPath1(){

        List<Place>places=
                given()


                        .when()
                        .get("http://api.zippopotam.us/us/90210")


                        .then()
                        .extract().jsonPath().getList("places", Place.class)
                ;
        System.out.println(places);

    }

    @Test
    public void Soru(){
        // https://gorest.co.in/public/v1/users  endpointte dönen Sadece Data Kısmını POJO
        // dönüşümü ile alarak yazdırınız.

        List<Data> data=
        given()


                .when()
                .get("https://gorest.co.in/public/v1/users")



                .then()
                .extract().jsonPath().getList("data",Data.class)
                ;
        System.out.println(data);

    }
}

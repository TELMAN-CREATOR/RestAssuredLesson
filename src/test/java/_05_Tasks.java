import Model.Todos;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class _05_Tasks {


    @Test
    public void Task(){

        /*
         * Task 1
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * expect status 200
         * expect content type JSON
         * expect title in response body to be "quis ut nam facilis et officia qui"
         */

        given()


                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")



                .then()
//                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("quis ut nam facilis et officia qui"))
        ;
    }


    @Test
    public void Task2(){
        /*
         * Task 2
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * expect status 200
         * expect content type JSON
         * a) expect response completed status to be false(hamcrest)
         * b) extract completed field and testNG assertion(testNG)
         */
        boolean sonuc =
        given()


                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")


                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("completed",equalTo(false))
                .extract().path("completed")

        ;
        System.out.println(sonuc);
        Assert.assertFalse(sonuc);
    }

    @Test
    public void Task3(){
        /* Task 3
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * Converting Into POJO body data and write
         */

        Todos todos=
        given()


                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")



                .then()
                .extract().body().as(Todos.class)

        ;

        System.out.println(todos);
    }

}

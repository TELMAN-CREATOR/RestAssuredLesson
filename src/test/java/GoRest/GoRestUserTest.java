package GoRest;

import Model.Post;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoRestUserTest {
    RequestSpecification requestSpecification;
    int PostId;
    Faker random = new Faker();

    @BeforeClass
    public void SetUp() {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer f92bf3f56439b631d9ed928b3540968e747c8e75309c876420fb349cbb420ed1")
                .log(LogDetail.URI)
                .build();


    }

    @Test
    public void CreatePost() {

        String paragraf = random.lorem().paragraph();
        String başlık = random.lorem().sentence();

        Post post = new Post();
        post.setUser_id(7915935);
        post.setBody(paragraf);
        post.setTitle(başlık);
        PostId =
                given()
                        .spec(requestSpecification)
                        .body(post)


                        .when()
                        .post("https://gorest.co.in/public/v2/posts")


                        .then()
                        .statusCode(201)
                        .log().body()
                        .extract().path("id")
        ;
        System.out.println(PostId);

    }

    @Test(dependsOnMethods = "CreatePost")
    public void GetPostById() {
        int gelenid =
                given()
                        .spec(requestSpecification)


                        .when().get("https://gorest.co.in/public/v2/posts/" + PostId)


                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().path("id");
        Assert.assertEquals(gelenid, PostId);

    }


    @Test(dependsOnMethods = "GetPostById")
    public void UpdatePost() {
        Post post = new Post();
        post.setTitle("Hello");
        post.setUser_id(7915935);
        post.setBody("idi naxuy");

        given()
                .spec(requestSpecification)
                .body(post)


                .when()
                .put("https://gorest.co.in/public/v2/posts/" + PostId)


                .then().log().body().statusCode(200);

    }

    @Test(dependsOnMethods = "UpdatePost")
    public void DeletePost() {
        given().spec(requestSpecification)

                .when().delete("https://gorest.co.in/public/v2/posts/" + PostId)


                .then().statusCode(204);


    }
}

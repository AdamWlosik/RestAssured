import Model.Post;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class VerifyResponse {

    @Test
    public void getPots() {

        String expected = "{\n" +
                "  \"author\": \"Daria\",\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"Nowy Tytul\"\n" +
                "}";

        given().when().
                    get("http://localhost:3000/posts/{postId}", 1)
                .then().log().all().body(Matchers.equalTo(expected));
    }

    @Test
    public void getPotsContains() {


        given().when().
                    get("http://localhost:3000/posts/{postId}", 1)
                .then()
                    .log().all().body(Matchers.containsString("Daria"));
    }

    @Test
    public void checkSpecificField() {


            when().
                    get("http://localhost:3000/posts/{postId}", 1).
                then().
                    assertThat().body("title", Matchers.equalTo("Nowy Tytul")).
                and().
                    assertThat().body("author", Matchers.equalTo("Daria"));
    }

    @Test
    public void getPostObject() {

        Integer id = 1;

        Post newPost = given().when().
                    get("http://localhost:3000/posts/{postId}", 1).
                then().log().all().
                    body("title", Matchers.equalTo("Nowy Tytul"))
                .and().
                    body("author", Matchers.equalTo("Daria")).extract().body().as(Post.class);

        Assert.assertEquals(newPost.getAuthor(), "Daria");
        Assert.assertEquals(newPost.getTitle(), "Nowy Tytul");
        Assert.assertEquals(newPost.getId(), id);
    }

    @Test
    public void addPostObject() {

        Post newPost = new Post();
        newPost.setTitle("Tytul obiektowy");
        newPost.setAuthor("Author obiektowy");

        Post createdPost = given().log().all().contentType(ContentType.JSON).
                body(newPost).
                when().
                    post("http://localhost:3000/posts").
                then().
                    log().all().extract().body().as(Post.class);

        Assert.assertEquals(newPost, createdPost);

        /*Assert.assertEquals(newPost.getAuthor(),"Author obiektowy");
        Assert.assertEquals(newPost.getTitle(),"Tytul obiektowy");*/

    }
}

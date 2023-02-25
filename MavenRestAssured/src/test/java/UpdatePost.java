import Model.Post;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdatePost {

    @Test
    public void updatePostObject() {

        Post newPost = new Post();
        newPost.setAuthor("Daria");
        newPost.setTitle("Nowy Tytul");



        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().patch("http://localhost:3000/posts/1").
                then().log().all();
    }
}

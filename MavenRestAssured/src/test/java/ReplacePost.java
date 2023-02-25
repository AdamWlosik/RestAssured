import Model.Post;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReplacePost {

    @Test
    public void replacePost() {

        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title","tytul po aktualizacji");
        newPost.put("author", "Daria"); // podmienia ca³oœæ zasobu jesli nie podamu autora zostanie on usuniety



        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().put("http://localhost:3000/posts/1").
                then().log().all();
    }

    @Test
    public void replacePostObject() {

        Post newPost = new Post();
        newPost.setAuthor("Pawel");
        //newPost.setTitle("Tytul Pawla"); // jeœli nie podobamy bêdzie null chyba, ze w post mamy dodane JsonInclude


        given().log().all().contentType(ContentType.JSON).body(newPost).
                when().put("http://localhost:3000/posts/1").
                then().log().all();
    }
}

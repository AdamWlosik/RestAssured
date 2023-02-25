import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FilterPosts {

    @Test
    public void filterPosts() {

        given().log().all().queryParam("author", "Daria")
                .when().get("http://localhost:3000/posts")
                .then().log().all();
    }

    @Test
    public void filterPostsById() {

        given().log().all().queryParam("id", "1","5")
                .when().get("http://localhost:3000/posts")
                .then().log().all().statusCode(200);
    }

    @Test
    public void filterPostsAuthorTitle() {

        Map<String, Object> params = new HashMap<>();
        params.put("author", "Daria");
        params.put("title", "Nowy Tytul");

        given().log().all().queryParams(params)
                .when().get("http://localhost:3000/posts")
                .then().log().all();
    }
}

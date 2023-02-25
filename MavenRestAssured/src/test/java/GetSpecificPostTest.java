import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;

public class GetSpecificPostTest {

    @Test
    public void getPots() {

        given().pathParam("postId", 1).when().get("http://localhost:3000/posts/{postId}").then().log().all();
        // oba poprawne zapisy
        given().when().get("http://localhost:3000/posts/{postId}", 1).then().log().all();
    }
}

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetPostsTest {

    public static void main(String[] args) {


    }

    @Test
    public void getPost() {
        when().get("http://localhost:3000/posts").then().log().body();

    }
}

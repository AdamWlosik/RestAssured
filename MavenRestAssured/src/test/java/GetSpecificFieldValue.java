import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class GetSpecificFieldValue {


    @Test
    public void checkSpecificField() {

        Response response = RestAssured.get("http://localhost:3000/posts/1");
        String author = response.path("author");

        //
        // String author2 = RestAssured.get("http://localhost:3000/posts/1").path("author");

        Assert.assertEquals(author, "Daria");

    }

    @Test
    public void checkSpecificFieldJsonPath() {

        Response response = RestAssured.get("http://localhost:3000/posts/1");
        JsonPath jsonPath = new JsonPath(response.asString());
        String author = jsonPath.get("author");

        Assert.assertEquals(author, "Daria");

    }
}

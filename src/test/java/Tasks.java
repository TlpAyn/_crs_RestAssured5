import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Tasks {

    /**
     * Task 2
     * create a request to https://httpstat.us/203
     * expect status 203
     * expect content type TEXT
     */

    @Test
    public void test(){

        given()
                .when()
                .get("https://httpstat.us/203")


                .then()
                .log().body()
                .statusCode(203)
                .contentType(ContentType.TEXT) // donen sonuc JSON mi ?
                ;






    }

        }

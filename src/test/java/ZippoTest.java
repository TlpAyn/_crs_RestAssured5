import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ZippoTest {


    @Test
    public void test(){



                given()
               // Hazirlik kismi : (token, send body, parametreler)


               .when()
               //endpoint (url), metodu

               .then();
       // assertion, test, data islemleri





    }

    @Test
    public void ilkTest(){



        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")



                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // donen sonuc JSON mi ?




        ;
    }

    @Test
    public void ch(){



        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")



                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // donen sonuc JSON mi ?




        ;
    }
}

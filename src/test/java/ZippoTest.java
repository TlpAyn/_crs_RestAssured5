import Model.Location;
import Model.Place;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ZippoTest {


    @Test
    public void test() {


        given()
                // Hazirlik kismi : (token, send body, parametreler)


                .when()
                //endpoint (url), metodu

                .then();
        // assertion, test, data islemleri


    }

    @Test
    public void ilkTest() {


        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON) // donen sonuc JSON mi ?
                .body("country", equalTo("United States"))


        ;
    }

    @Test
    public void ch() {


        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()

                .statusCode(200)
                .body("places[0].state", equalTo("California"))


        ;
    }

    @Test
    public void checkHasItemy() {


        given()


                .when()
                .get("http://api.zippopotam.us/tr/01000")


                .then()
                //.log().body()

                .statusCode(200)
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))


        ;
    }

    @Test
    public void bodyArrayHassSizeTest() {


        given()


                .when()

                .get("http://api.zippopotam.us/us/90210")


                .then()
                //.log().body()

                .statusCode(200)
                .body("places", hasSize(1))


        ;
    }

    @Test
    public void combiningTest() {


        given()


                .when()
                .get("http://api.zippopotam.us/us/90210")


                .then()
                .log().body()
                //.log().body()

                .statusCode(200)
                .body("places", hasSize(1))
                .body("places.state", hasItem("California"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))


        ;
    }

    @Test
    public void pathParamTest() {


        given()
                .pathParam("ulke", "us")
                .pathParam("postaKod", 90210)
                .log().uri() // request link


                .when()
                .get("http://api.zippopotam.us/{ulke}/{postaKod}")


                .then()
                .log().body()
                //.log().body()

                .statusCode(200)
                .body("places", hasSize(1))
                .body("places.state", hasItem("California"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))


        ;


    }

    @Test
    public void pathParamTest2() {

        // https://gorest.co.in/public/v1/users?page=3
        // bu linkteki 1 den 10 kadar sayfaları çağırdığınızda response daki donen page degerlerinin
        // çağrılan page nosu ile aynı olup olmadığını kontrol ediniz.

        for (int i = 1; i < 10; i++) {

            given()
                    .param("page", i)
                    .log().uri() // request link


                    .when()
                    .get("https://gorest.co.in/public/v1/users")


                    .then()

                    .statusCode(200)
                    .body("meta.pagination.page", equalTo(i));

        }
    }

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {

        baseURI = "https://gorest.co.in/public/v1";

        requestSpec = new RequestSpecBuilder()
                .log(LogDetail.URI)
                .setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .log(LogDetail.BODY)
                .build();
    }


    @Test
    public void test1() {


        given()
                .param("page", 1)
                .spec(requestSpec)


                .when()
                .get("/users")


                .then()
                .spec(responseSpec)
        ;


    }

    @Test
    public void exractingJsonPAth() {

        String countryName=

        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .extract().path("country")
        ;
        System.out.println("countryName = " + countryName);
        Assert.assertEquals(countryName,"United States");

    }
    @Test
    public void exractingJsonPAth2() {

        String countryName=

                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")
                        .then()
                        .extract().path("country")
                ;
        System.out.println("countryName = " + countryName);
        Assert.assertEquals(countryName,"United States");

    }
    @Test
    public void exractingJsonPAth3() {

int limit =

                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().path("meta.pagination.limit")
                ;
        System.out.println("limit = " + limit);

    }

    @Test
    public void exractingJsonPAth4() {

      List<Integer> idler =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("data.id")
                ;

        System.out.println("idler = "+ idler);



    }

    @Test
    public void exractingJsonPAth5() {

        List<String> names =
                given()
                        .when()
                        .get("https://gorest.co.in/public/v1/users")
                        .then()
                        //.log().body()
                        .statusCode(200)
                        .extract().path("data.name")
                ;

        System.out.println("idler = "+ names);

    }
    @Test
    public void exractingJsonPAthResponsAll() {

        Response donenData =
                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .statusCode(200)
                        // .log().body()
                        .extract().response(); // dönen tüm datayı verir.

        List<Integer> ids = donenData.path("data.id");
        List<String> names = donenData.path("data.name");
        int limit = donenData.path("meta.pagination.limit");

        System.out.println("ids = "+ ids);
        System.out.println("names = "+names);
        System.out.println("limit = "+limit);

        Assert.assertTrue(names.contains("Avani Saini"));
        Assert.assertTrue(ids.contains(1176496));
        Assert.assertEquals(limit, 10, "test sonucu hatali");

    }
    @Test
    public void extraxtJsonAll()
    {

        Location locationNesnesi =
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .extract().body().as(Location.class)

        ;

        System.out.println("locationNesnesi.get"+ locationNesnesi.getCountry());
    }

    @Test
    public void extractPOJO_Soru(){

        // aşağıdaki endpointte(link)  Dörtağaç Köyü ait diğer bilgileri yazdırınız


        Location  adana =
                given()
                        .when()
                        .get("http://api.zippopotam.us/tr/01000")

                        .then()
                        ///.log().body()
                        .statusCode(200)
                        .extract().body().as(Location.class)


                ;
        for (Place p : adana.getPlaces()) {

            if (p.getPlacename().equalsIgnoreCase("Dörtağaç Köyü"))
            {
                System.out.println("p = "+ p );
            }

        }


}
}

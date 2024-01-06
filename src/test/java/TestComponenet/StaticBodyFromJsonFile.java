package TestComponenet;

import AbstarctComponent.ReusableCode;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.equalTo;

public class StaticBodyFromJsonFile {


        @Test
        public void addPlaceTest() throws IOException {
            System.out.println(System.getProperty("user.dir"));

            RestAssured.baseURI ="https://rahulshettyacademy.com";

            String addPlaceResponse = given().log().all().queryParam("key","qaclick123").header("content-Type","application/json")
                    .body(new String(Files.readAllBytes(Path.of(System.getProperty("user.dir")+"\\src\\test\\java\\DataFiles\\addPlace.json"))))
                    .when().post("/maps/api/place/add/json")
                    .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();

            JsonPath jsonPath = ReusableCode.rawtojson(addPlaceResponse);
           String place_id =jsonPath.get("place_id");
            System.out.println(place_id);


        }
    }


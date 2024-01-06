package TestComponenet;

import AbstarctComponent.ReusableCode;
import DataFiles.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DemloTest {
    String place_id;
    String addressToUpdate ="70 winter walk, USA";
//Apurva Naik2024
 @Test
    public void addPlace(){

        RestAssured.baseURI ="https://rahulshettyacademy.com";

       String addPlaceResponse = given().log().all().queryParam("key","qaclick123").header("content-Type","application/json")
                .body(Payload.addPlacePayload())
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server",equalTo("Apache/2.4.52 (Ubuntu)")).extract().response().asString();

     JsonPath jsonPath =ReusableCode.rawtojson(addPlaceResponse);
     place_id =jsonPath.get("place_id");
     System.out.println(place_id);


    }

    @Test(dependsOnMethods = "addPlace")

    public void updatePlace(){
        RestAssured.baseURI ="https://rahulshettyacademy.com";

        given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_id).header("content-Type","application/json")
                .body(Payload.updatePlacePayload(place_id,addressToUpdate))
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

    }

    @Test(dependsOnMethods = "updatePlace")

    public void getPlace(){
        RestAssured.baseURI ="https://rahulshettyacademy.com";

        String getPlaceResponse =given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_id)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath jsonPath =ReusableCode.rawtojson(getPlaceResponse);
        String actualAddress =jsonPath.get("address");

        Assert.assertEquals(addressToUpdate,actualAddress);
        System.out.println("End of Execution");

    }
}


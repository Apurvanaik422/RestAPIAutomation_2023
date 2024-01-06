package TestComponenet;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class DemloTest2 {

    public static void main(String[] args) {

        RestAssured.baseURI ="https://rahulshettyacademy.com";

        given().log().all().queryParam("key","qaclick123").queryParam("place_id","e8160e8186e673f5958e5dcbb4c2d8ab")
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200);

    }
}


package TestComponenet;

import PojoClasses.AddPlace;
import PojoClasses.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class AddPlacewithPojoSearlization {

    @Test
    public void addPlace(){

        AddPlace addPlace =new AddPlace();
        addPlace.setAccuracy(20);
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setLanguage("English");
        addPlace.setPhone_number("7620172242");
        addPlace.setWebsite("https://www.google.com");
        addPlace.setName("Apurva");

        Location location =new Location();
        location.setLat(-34.123);
        location.setLng(-22.22);
        addPlace.setLocation(location);

        List<String> list =new ArrayList<>();
        list.add("shoe park");
        list.add("shop");

        addPlace.setTypes(list);


        RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").addHeader("content-Type","application/json").build();
        ResponseSpecification resp =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();


        RequestSpecification request =given().log().all().spec(req)
                .body(addPlace);


       String apiResponse = request.when().post("/Library/Addbook.php")
                .then().log().all().spec(resp).extract().response().asString();

       System.out.println(apiResponse);

    }
}

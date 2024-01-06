package TestComponenet;

import AbstarctComponent.ReusableCode;
import DataFiles.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LibraryApiTest {
    String ID;

    @Test(dataProvider = "addBook")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";

        String addbookResponse =given().log().all().header("content-Type","application/json")
                .body(Payload.addBookPayload(isbn,aisle))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).body("Msg",equalTo("successfully added")).extract().response().asString();

        JsonPath jsonPath =ReusableCode.rawtojson(addbookResponse);
        ID = jsonPath.get("ID").toString();
        System.out.println(ID);
    }



    @Test(dependsOnMethods ="addBook",dataProvider = "deletebookdata")
    public void deleteBook(String idVal){
        RestAssured.baseURI="http://216.10.245.166";

        given().log().all().header("content-Type","application/json")
                .body(Payload.deleteBookPayload(idVal))
                .when().post("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200);


    }
    @DataProvider(name="addBook")
    public Object[][]  getData(){

        return new Object[][] {{"dpfgfha","9961"},{"dpfgfha1","99611"}};


    }

    @DataProvider
    public  Object[][] deletebookdata(){

       return new Object[][] {{"dpfgfha9961"},{"dpfgfha199611"}};
    }
}

package TestComponenet;

import AbstarctComponent.ReusableCode;
import PojoClasses.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommorceSiteAutomation {

    static  String authToken;
    static  String userId;
    static  String productId;
    static  String productOrderId;

    @Test
    public void loginTest(){

        RequestSpecification loginBaseRequest =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Content-Type","application/json").build();
        ResponseSpecification resp =new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

        LoginRequest loginRequestPayload =new LoginRequest();
        loginRequestPayload.setUserEmail("apurvanaik42@academy.com");
        loginRequestPayload.setUserPassword("Improve@1234");

        RequestSpecification loginRequest =given().relaxedHTTPSValidation().log().all().spec(loginBaseRequest).body(loginRequestPayload);

        LoginResponse loginResponse =loginRequest.when().post("/api/ecom/auth/login")
                 .then().spec(resp).extract().as(LoginResponse.class);

         authToken =loginResponse.getToken();
        userId = loginResponse.getUserId();

        System.out.println(authToken);
        System.out.println(userId);


    }

    @Test(dependsOnMethods = "loginTest")
    public void createProduct(){
        RequestSpecification createProductBaseRequest =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Content-Type","multipart/form-data").addHeader("Authorization",authToken).build();
        ResponseSpecification resp =new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(201).build();


        RequestSpecification createProductRequest = given().log().all().spec(createProductBaseRequest);

       String createProductResponse = createProductRequest.param("productName","Apple_2013")
                .param("productAddedBy",userId)
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","11500")
                .param("productDescription","Addias Originals")
                .param("productFor","Men")
                .multiPart("productImage", new File("C:\\Users\\91762\\OneDrive\\Desktop\\AppleImg.jpg"))
                .when().post("/api/ecom/product/add-product")
                .then().log().all().spec(resp).extract().response().asString();

        JsonPath jsonPath =ReusableCode.rawtojson(createProductResponse);
        productId =jsonPath.get("productId");


    }

    @Test(dependsOnMethods = "createProduct")
    public void placeOrder(){

        RequestSpecification placeOrderBaseRequest =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
                .addHeader("Content-Type","application/json").addHeader("Authorization",authToken).build();
        ResponseSpecification resp =new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(201).build();



        Orders orders =new Orders();
        orders.setCountry("British Indian Ocean Territory");
        orders.setProductOrderedId(productId);

        List<Orders> ordersList =new ArrayList<Orders>();
        ordersList.add(orders);

        PlaceOrderRequest placeOrderRequest =new PlaceOrderRequest();
        placeOrderRequest.setOrders(ordersList);


        RequestSpecification placeOrderRequestLatest = given().log().all().spec(placeOrderBaseRequest).body(placeOrderRequest);

        PlaceOrderResponse placeOrderResponse = placeOrderRequestLatest.when().post("/api/ecom/order/create-order")
                .then().log().all().spec(resp).extract().response().as(PlaceOrderResponse.class);

        productOrderId = placeOrderResponse.getProductOrderId().get(0);
        System.out.println(productOrderId);


    }


    @Test(dependsOnMethods = "placeOrder")
    public void deleteproduct(){

        RequestSpecification deleteproductBaseRequest =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",authToken).build();
        ResponseSpecification resp =new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();

        RequestSpecification  deleteproductRequest = given().log().all().spec(deleteproductBaseRequest).pathParam("product_id",productId);

       String deleteproductResponse = deleteproductRequest.when().delete("/api/ecom/product/delete-product/{product_id}")
                .then().log().all().spec(resp).extract().asString();



        JsonPath jsonPath =ReusableCode.rawtojson(deleteproductResponse);
        String message =jsonPath.get("message");
        System.out.println(message);

    }
}

package TestComponenet;

import AbstarctComponent.ReusableCode;
import PojoClasses.AllCoursesInfo;
import PojoClasses.Api;
import PojoClasses.WebAutomation;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OAuth20Authorization {
    String accessToken;
    String authorizationCode;
//.queryParam("","")

    @Test
    public void getAuthorizationCode(){
      String repAuthourizationUrl ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AfJohXm9MOkjWCN2P4QtjgweFXsg-ryVTs4uu5EeV_iXQSpWhvz5dNCNixo0758FtMbyBw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=1&prompt=none";
      authorizationCode = repAuthourizationUrl.split("code=")[1].split("&scope")[0].trim();
      System.out.println(authorizationCode);
    }

    @Test(dependsOnMethods = "getAuthorizationCode")
    public void getAccessToken(){
       String accessTokenResponse = given().log().all()
                .urlEncodingEnabled(false)
                .queryParams("code",authorizationCode)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().post("https://www.googleapis.com/oauth2/v4/token")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath jsonPath =ReusableCode.rawtojson(accessTokenResponse);
         accessToken = jsonPath.get("access_token");
    }

    @Test(dependsOnMethods = "getAccessToken")
    public void actualRequest(){
      AllCoursesInfo allCoursesInfo =given().log().all().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/getCourse.php")
                .then().log().all().assertThat().statusCode(200).header("Server",equalTo("Apache/2.4.52 (Ubuntu)")).extract().as(AllCoursesInfo.class);



        System.out.println(allCoursesInfo.getUrl());
        System.out.println(allCoursesInfo.getLinkedIn());

        //Printing  cources name from API

        List<Api> api =allCoursesInfo.getCourses().getApi();

        for(int i=0;i<api.size();i++){
           System.out.println(api.get(i).getCourseTitle());
            System.out.println(api.get(i).getPrice());



        }
System.out.println("---------------------------------------------------");

        List<WebAutomation> automation =allCoursesInfo.getCourses().getWebAutomation();

        for(int i=0;i<automation.size();i++){

            if(automation.get(i).getCourseTitle().equalsIgnoreCase("Protractor")){
                System.out.println(automation.get(i).getPrice());
            }
        }

    }
}

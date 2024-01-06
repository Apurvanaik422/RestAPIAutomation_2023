package TestComponenet;

import DataFiles.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexjsonParseEg {
    public static void main(String[] args) {

        JsonPath jsonPath =new JsonPath(Payload.complexjson());
        System.out.println("1. Print No of courses returned by API");
       int courseCount =jsonPath.getInt("courses.size()");
        System.out.println("CourseCount :"+courseCount);

        System.out.println("2.Print Purchase Amount");
        int purchaseAmt =jsonPath.get("dashboard.purchaseAmount");
        System.out.println(purchaseAmt);

        System.out.println("3. Print Title of the first course");
        System.out.println(jsonPath.get("courses[0].title").toString());

        System.out.println("4. Print All course titles and their respective Prices");

        for(int i=0;i<courseCount;i++){
           String title = jsonPath.get("courses["+i+"].title").toString();
           int price =jsonPath.getInt("courses["+i+"].price");
            System.out.println(title);
         System.out.println(price);
        }

        System.out.println("//5. Print no of copies sold by RPA Course");

        for(int i=0;i<courseCount;i++){
            String title = jsonPath.get("courses["+i+"].title").toString();

            if(title.equalsIgnoreCase("RPA")){
                int price =jsonPath.getInt("courses["+i+"].price");
                System.out.println(price);

            }
        }

        System.out.println("6. Verify if Sum of all Course prices matches with Purchase Amount");
        int sum = 0;
int amount;
        for(int i=0;i<courseCount;i++) {
            int price = jsonPath.getInt("courses[" + i + "].price");
            int copies = jsonPath.getInt("courses[" + i + "].copies");
            amount = price * copies;
            sum = sum + amount;
        }
        System.out.println(sum);

        Assert.assertEquals(purchaseAmt,sum);
        System.out.println("Emd of Test case");



    }
}

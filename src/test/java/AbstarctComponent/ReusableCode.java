package AbstarctComponent;

import io.restassured.path.json.JsonPath;

public class ReusableCode {


    public  static JsonPath rawtojson(String responseVal)
    {
        JsonPath jsonPath =new JsonPath(responseVal);
        return jsonPath;

    }

}

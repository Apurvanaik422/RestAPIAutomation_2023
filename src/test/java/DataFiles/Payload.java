package DataFiles;

public class Payload {

    public static String addPlacePayload(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }

    public  static String updatePlacePayload(String place_id,String addressToUpdate){
        return "{\n" +
                "\"place_id\":\""+place_id+"\",\n" +
                "\"address\":\""+addressToUpdate+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n";
    }

    public  static String complexjson(){
        return "{\n" +
                "\t\t\t\t\"dashboard\": { \n" +
                "\t\t\t\t \"purchaseAmount\":1162, \n" +
                "\t\t\t\t \"website\":\"rahulshettyacademy.com\"\n" +
                "\t\t\t\t },\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t\"courses\": [\n" +
                "\t\t\t {  \n" +
                "\t\t\t  \"title\":\"Selenium Python\",  \n" +
                "\t\t\t  \"price\": 50,  \n" +
                "\t\t\t  \"copies\": 6  \n" +
                "\t\t\t },  \n" +
                "\t\t\t {  \n" +
                "\t\t\t  \"title\":\"Cypress\",  \n" +
                "\t\t\t  \"price\": 40,  \n" +
                "\t\t\t  \"copies\": 4  \n" +
                "\t\t\t },  \n" +
                "\t\t\t {  \n" +
                "\t\t\t  \"title\":\"RPA\",  \n" +
                "\t\t\t  \"price\": 45,  \n" +
                "\t\t\t  \"copies\": 10  \n" +
                "\t\t\t },  \n" +
                "\t\t\t  {  \n" +
                "\t\t\t  \"title\":\"Appium\",  \n" +
                "\t\t\t  \"price\": 36,  \n" +
                "\t\t\t  \"copies\": 7  \n" +
                "\t\t\t } \n" +
                "\t\t\t ]\n" +
                "\t\t\t \n" +
                "}\t";
    }

    public  static  String addBookPayload(String isbn,String aisle){
        String payload ="{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n" +
                " \n";

        return payload;
    }

    public static String deleteBookPayload(String Id){
        String payload ="{\n" +
                " \n" +
                "\"ID\" : \""+Id+"\"\n" +
                " \n" +
                "} \n";
        return payload;
    }
}

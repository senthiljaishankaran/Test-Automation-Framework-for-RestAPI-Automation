import Data.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restAPIClientBase.restAPITestBase;
import restAPIClientMethods.RestAPIClient;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class restAPIPostTest extends restAPITestBase {
    restAPITestBase RestAPITestBase;
    RestAPIClient restAPIClient;
    String serviceURL;
    String apiURL;
    String url;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setupPost(){
        RestAPITestBase=new restAPITestBase();
        serviceURL= properties.getProperty("URL");
        apiURL=properties.getProperty("serviceURL");
        url=serviceURL+apiURL;
    }

    @Test
    public void postAPICall() throws IOException, ParseException {
        restAPIClient=new RestAPIClient();
        HashMap<String,String> headerMap=new HashMap<>();
        headerMap.put("Content-Type","application/json");


        // Creating Mapper Object from Json
        ObjectMapper mapper=new ObjectMapper();
        Users users=new Users("Senthil","SDET");

        // Writing POJO to JSON in a Json file
        mapper.writeValue(new File("C:\\Users\\senth\\IdeaProjects\\Test-Automation-Framework-for-RestAPI-Automation\\src\\main\\java\\Data\\Postdata.json"),users);

        // Java Object to json in String
        String usersJsonString=mapper.writeValueAsString(users);
        System.out.println(usersJsonString);

        closeableHttpResponse=restAPIClient.post(url,usersJsonString,headerMap);

        // Getting Status Code
        int statusCode=closeableHttpResponse.getCode();
        System.out.println("Status Code is: "+ statusCode);
        Assert.assertEquals(statusCode,RESPONSE_CODE_201);

        // Getting  Json String
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");

        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("Response Json from String is: "+ responseJson);

        // Json to Java Object
        Users userResObj=mapper.readValue(responseString,Users.class);
        System.out.println("Converted Json to Java Object is: " +userResObj);

        Assert.assertTrue(users.getName().equals(userResObj.getName()));
        Assert.assertTrue(users.getJob().equals(userResObj.getJob()));
    }
}

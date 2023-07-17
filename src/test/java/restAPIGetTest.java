import TestUtilities.TestUtilities;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restAPIClientBase.restAPITestBase;
import restAPIClientMethods.RestAPIClient;

import java.io.IOException;
import java.util.HashMap;

public class restAPIGetTest extends restAPITestBase {
    restAPITestBase restAPITest;
    String serviceURL;
    String apiURL;
    RestAPIClient restAPIClient;
    String url;
    CloseableHttpResponse closeableHttpResponse;
    @BeforeMethod
    public void setupGet(){
        restAPITest=new restAPITestBase();
        serviceURL=properties.getProperty("URL");
        apiURL=properties.getProperty("serviceURL");
        url=serviceURL+apiURL;
    }
    @Test
    public void getAPITest() throws IOException, ParseException {
        restAPIClient=new RestAPIClient();
        HashMap<String,String> headerMap=new HashMap<String,String>();
        headerMap.put("Content-Type","application/json");
        closeableHttpResponse=restAPIClient.get(url,headerMap);

        // Getting Status code
        int statusCode=closeableHttpResponse.getCode();
        System.out.println("Status Code is: "+ statusCode);
        Assert.assertEquals(RESPONSE_CODE_200,statusCode);

        // Getting Response in String
        String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("Response String is :"+ responseString);

        // Getting Response in JSON
        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("Response JSON is :"+ responseJson);

        // Getting the JSON Object using Test Utilities
        String perPage= TestUtilities.getValueWithJsonPath(responseJson,"/per_page");
        System.out.println("Value of Per_page"+perPage);
        Assert.assertEquals(Integer.parseInt(perPage),6);

        String Page= TestUtilities.getValueWithJsonPath(responseJson,"/page");
        System.out.println("Value of Page"+Page);
        Assert.assertEquals(Integer.parseInt(Page),1);

        String Total= TestUtilities.getValueWithJsonPath(responseJson,"/total");
        System.out.println("Value of total"+Total);
        Assert.assertEquals(Integer.parseInt(Total),12);

        String TotalPage= TestUtilities.getValueWithJsonPath(responseJson,"/total_pages");
        System.out.println("Value of total_pages"+TotalPage);
        Assert.assertEquals(Integer.parseInt(TotalPage),2);

        // Getting Value from JSON ARRAY
        String id=TestUtilities.getValueWithJsonPath(responseJson,"/data[0]/id");
        String email=TestUtilities.getValueWithJsonPath(responseJson,"/data[0]/email");
        String first_name=TestUtilities.getValueWithJsonPath(responseJson,"/data[0]/first_name");
        String last_name=TestUtilities.getValueWithJsonPath(responseJson,"/data[0]/last_name");
        String avatar=TestUtilities.getValueWithJsonPath(responseJson,"/data[0]/avatar");

        System.out.println(id);
        System.out.println(email);
        System.out.println(first_name);
        System.out.println(last_name);
        System.out.println(avatar);

        // Creating Header Array
        Header[] headerArray=closeableHttpResponse.getHeaders();
        HashMap<String,String> allHeader= new HashMap<String,String>();

        for(Header header:headerArray){
            allHeader.put(header.getName(), header.getValue());
        }
        System.out.println("Response from JSON"+allHeader);
    }
}

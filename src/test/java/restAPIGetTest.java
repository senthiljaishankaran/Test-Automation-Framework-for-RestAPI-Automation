import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restAPIClientBase.restAPITestBase;
import restAPIClientMethods.RestAPIClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

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
        closeableHttpResponse=restAPIClient.get(url);

        // Getting Status code
        int statusCode=closeableHttpResponse.getCode();
        System.out.println("Status Code is: "+ statusCode);

        // Getting Response in String
        String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        System.out.println("Response String is :"+ responseString);

        // Getting Response in JSON
        JSONObject responseJson=new JSONObject(responseString);
        System.out.println("Response JSON is :"+ responseJson);

        // Creating Header Array
        Header[] headerArray=closeableHttpResponse.getHeaders();
        HashMap<String,String> allHeader= new HashMap<String,String>();

        for(Header header:headerArray){
            allHeader.put(header.getName(), header.getValue());
        }
        System.out.println("Response from JSON"+allHeader);
    }
}

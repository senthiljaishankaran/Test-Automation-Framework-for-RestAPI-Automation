import org.apache.hc.core5.http.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restAPIClientBase.restAPITestBase;
import restAPIClientMethods.RestAPIClient;

import java.io.IOException;
import java.util.Properties;

public class restAPIGetTest extends restAPITestBase {
    restAPITestBase restAPITest;
    String serviceURL;
    String apiURL;
    RestAPIClient restAPIClient;
    String url;
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
        restAPIClient.get(url);
    }
}

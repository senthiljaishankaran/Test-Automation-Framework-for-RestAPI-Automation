package restAPIClientMethods;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.util.HashMap;

public class RestAPIClient {
    // Get Method
   public void get(String URL) throws IOException, ParseException {
       CloseableHttpClient closeableHttpClient =HttpClients.createDefault();
       HttpGet httpGet=new HttpGet(URL);
       CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);
       int statusCode=closeableHttpResponse.getCode();
       System.out.println("Status Code is: "+ statusCode);

       String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
       System.out.println("Response String is :"+ responseString);

       Header[] headerArray=closeableHttpResponse.getHeaders();
       HashMap<String,String> allHeader= new HashMap<String,String>();

       for(Header header:headerArray){
           allHeader.put(header.getName(), header.getValue());
       }
       System.out.println("Response from JSON"+allHeader);

   }
}

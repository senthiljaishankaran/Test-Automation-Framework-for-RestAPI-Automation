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
   public CloseableHttpResponse get(String URL) throws IOException {
       CloseableHttpClient closeableHttpClient =HttpClients.createDefault();
       HttpGet httpGet=new HttpGet(URL);
       CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);
       return closeableHttpResponse;
   }
}

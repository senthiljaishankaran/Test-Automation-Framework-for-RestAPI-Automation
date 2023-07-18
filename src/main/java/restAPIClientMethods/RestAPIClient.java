package restAPIClientMethods;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestAPIClient {
    // Get Method
   public CloseableHttpResponse get(String URL,HashMap<String,String> headerMap) throws IOException {
       CloseableHttpClient closeableHttpClient =HttpClients.createDefault();
       HttpGet httpGet=new HttpGet(URL);
       for(Map.Entry<String,String> entry:headerMap.entrySet()){
           httpGet.addHeader(entry.getKey(),entry.getValue());
       }
       CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);
       return closeableHttpResponse;
   }
   // Post Method
    public CloseableHttpResponse post(String URL,String entityString,HashMap<String,String> headerMap) throws IOException{
       CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
       HttpPost httpPost=new HttpPost(URL);
       httpPost.setEntity(new StringEntity(entityString));
       for(Map.Entry<String,String> entry :headerMap.entrySet())
           httpPost.addHeader(entry.getKey(),entry.getValue());
       CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpPost);
       return closeableHttpResponse;
    }
}

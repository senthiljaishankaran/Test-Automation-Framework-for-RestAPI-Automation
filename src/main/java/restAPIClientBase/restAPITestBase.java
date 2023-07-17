package restAPIClientBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class restAPITestBase {
    public static int RESPONSE_CODE_200=200;
    public Properties properties;
   public restAPITestBase(){
       properties=new Properties();
       try {
           FileInputStream fileInputStream = new FileInputStream("C:\\Users\\senth\\IdeaProjects\\Test-Automation-Framework-for-RestAPI-Automation\\src\\main\\java\\ConfigProperties\\config.properties");
            properties.load(fileInputStream);
       }catch (IOException exception){
           exception.printStackTrace();
       }
   }
}

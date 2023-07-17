package TestUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtilities {
    public static String getValueWithJsonPath(JSONObject responseJson,String jsonPath){
        Object obj=responseJson;
        for(String string:jsonPath.split("/"))
            if(!(string.isEmpty()))
                if(!(string.contains("[")||string.contains("]")))
                    obj=((JSONObject)obj).get(string);
                else if(string.contains("[")||(string.contains("]")))
                    obj=((JSONArray)((JSONObject)obj).get(string.split("\\[")[0])).get(Integer.parseInt(string.split("\\[")[1].replace("]","")));
                return obj.toString();
    }
}

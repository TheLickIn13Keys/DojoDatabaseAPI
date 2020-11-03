import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class REST {

    public REST(){

    }

    private static HttpURLConnection APIconnection;
    public ArrayList<String> getTokens() throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        URL url = new URL("https://the-token-master.glitch.me/getDreams");
        APIconnection = (HttpURLConnection) url.openConnection();

        APIconnection.setRequestMethod("GET");
        APIconnection.setConnectTimeout(5000);
        APIconnection.setReadTimeout(5000);

        int status = APIconnection.getResponseCode();
        System.out.println(status);

        if(status > 299){
            reader = new BufferedReader(new InputStreamReader(APIconnection.getErrorStream()));
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
        }
        else{
            reader = new BufferedReader(new InputStreamReader(APIconnection.getInputStream()));
            while ((line = reader.readLine()) != null){
                responseContent.append(line);
            }
        }

        ArrayList<String> dreamList = new ArrayList<String>();
        JsonParser jp = new JsonParser();
        JsonArray dreams = (JsonArray) jp.parse(responseContent.toString());
        for(int i = 0; i<dreams.size(); i++){
            JsonObject dream = (JsonObject) dreams.get(i);
            String token = dream.get("dream").toString();
            System.out.println(token);
        }
        APIconnection.disconnect();
        return dreamList;

    }

}

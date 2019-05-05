package examples;

import com.objectia.objectia.ObjectiaClient;
import com.objectia.objectia.exceptions.APIConnectionException;
import com.objectia.objectia.exceptions.APIException;
import com.objectia.objectia.exceptions.ResponseException;
import com.objectia.objectia.api.GeoLocation;

public class Example {
    public static void main(String[] args) {
        string apiKey = System.getenv("OBJECTIA_APIKEY");
        ObjectiaClient.init(apiKey);
       
        try {
            ObjectiaClient.init(apiKey);
            GeoLocation location = GeoLocation.get("8.8.8.8");
            System.err.println("Country code: " + location.getCountryCode());
        } catch (ResponseException ex) {
            System.err.println("Response error: " + ex.getMessage());
        } catch (APIException ex) {
            System.err.println("API error: " + ex.getMessage());
        }
    }
}

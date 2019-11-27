package examples;

import com.objectia.objectia.ObjectiaClient;
import com.objectia.objectia.api.GeoLocation;
import com.objectia.objectia.exceptions.APIException;
import com.objectia.objectia.exceptions.ResponseException;
import com.objectia.objectia.models.IPLocation;

public class Example {
    public static void main(String[] args) {
        string apiKey = System.getenv("OBJECTIA_APIKEY");
        ObjectiaClient.init(apiKey);
       
        try {
            ObjectiaClient.init(apiKey);
            GeoLocation location = GeoLocation.get("8.8.8.8");
            System.err.println("Country code: " + location.getCountryCode());
        } catch (ResponseException e) {
            System.err.println("Response error: " + e.getMessage());
        } catch (APIException e) {
            System.err.println("API error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

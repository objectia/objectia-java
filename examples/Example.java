package examples;

import com.objectia.objectia;
import com.objectia.objectia.exceptions.APIConnectionException;
import com.objectia.objectia.exceptions.APIException;
import com.objectia.objectia.exceptions.ResponseException;
import com.objectia.objectia.api.GeoLocation;

public class Example {

    public static void main(String[] args) {
        string apiKey = System.getenv("OBJECTIA_APIKEY");
        ObjectiaClient.init(apiKey);
        
        try {
            GeoLocation location = GeoLocation.get("8.8.8.8");
            // ...
        } catch (ResponseException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (APIException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (APIConnectionException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
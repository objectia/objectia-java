package com.objectia.api;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import com.objectia.ObjectiaClient;
import com.objectia.models.Entity;
import com.objectia.models.Response;
import com.objectia.RestClient;
import com.objectia.exceptions.APIException;

/**
 * Usage GeoLocation model class.
 */
public class GeoLocation {
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
    
    /**
     * User id
     */
    @SerializedName("country_code")
    private final String countryCode;

    /**
     * Class constructor.
     */
    private GeoLocation() {
        this.countryCode = null;
    }

    /**
     * 
     * @return
     */
    public static GeoLocation get(final String ip) throws APIException, IllegalArgumentException {
        RestClient restClient = ObjectiaClient.getRestClient();
        Response resp = restClient.get("/geoip/" + ip); 
        return GeoLocation.fromJSON(resp.getBody());
    }

    /**
     * Get the country code
     * @return country code
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * Creates a GeoLocation object from a JSON string. 
     * 
     * This JSON has the following format:
     * <p>{
     *   "status": 200,
     *   "data": {
     *      ... details here ...
     *   } 
     * }</p>
     * 
     * @param json a string containing JSON to be converted
     * @return GeoLocation
     */
    public static GeoLocation fromJSON(final String json) {
        Type type = TypeToken.getParameterized(Entity.class, GeoLocation.class).getType();
        Entity<GeoLocation> res = GSON.fromJson(json, type); 
        return res.getData();
    }
}

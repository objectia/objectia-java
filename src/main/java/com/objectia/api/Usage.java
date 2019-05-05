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
 * Usage API model class.
 */
public class Usage {
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
    
    /**
     * User id
     */
    @SerializedName("geoip_requests")
    private final int geoLocationRequests;

    /**
     * Class constructor.
     */
    private Usage() {
        this.geoLocationRequests = 0;
    }

    /**
     * 
     * @return
     */
    public static Usage get() throws APIException, IllegalArgumentException {
        RestClient restClient = ObjectiaClient.getRestClient();
        Response resp = restClient.get("/usage"); 
        return Usage.fromJSON(resp.getBody());
    }

    /**
     * Get the number of geoip request 
     * @return number of requests
     */
    public int getGeoLocationRequests() {
        return this.geoLocationRequests;
    }

    /**
     * Creates a Usage object from a JSON string. 
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
     * @return Usage
     */
    public static Usage fromJSON(final String json) {
        Type type = TypeToken.getParameterized(Entity.class, Usage.class).getType();
        Entity<Usage> res = GSON.fromJson(json, type); 
        return res.getData();
    }

    /**
     * Creates a json string from the Usage
     * 
     * @return a JSON data string
     */
    public String toJSON() {
        return GSON.toJson(this);
    }
}

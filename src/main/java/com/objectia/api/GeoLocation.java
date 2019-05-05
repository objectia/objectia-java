package com.objectia.api;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import com.objectia.ObjectiaClient;
import com.objectia.models.Entity;
import com.objectia.models.Response;
import com.objectia.utils.StringUtils;
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
     * Get geolocation by IP address
     * @return geolocaiton
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static GeoLocation get(final String ip) throws APIException, IllegalArgumentException {
        if (ip == null) {
            throw new IllegalArgumentException("An IP address was not provided");
        }
        RestClient restClient = ObjectiaClient.getRestClient();
        Response resp = restClient.get("/geoip/" + ip); 
        return GeoLocation.fromJSON(resp.getBody());
    }

    /**
     * Get geolocation for requester's current IP address.
     * @return geolocaiton
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static GeoLocation getCurrent() throws APIException, IllegalArgumentException {
        return GeoLocation.get("myip");
    }

    public static List<GeoLocation> getBulk(final String ipList[]) throws APIException, IllegalArgumentException {
        if (ipList == null || ipList.length == 0) {
            throw new IllegalArgumentException("An IP address was not provided");
        }
        String ips = StringUtils.join(ipList, ",");
        RestClient restClient = ObjectiaClient.getRestClient();
        Response resp = restClient.get("/geoip/" + ips); 
        return GeoLocation.fromJSONArray(resp.getBody());
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

    public static List<GeoLocation> fromJSONArray(final String json) {
        Type type = TypeToken.getParameterized(Entity.class, GeoLocation[].class).getType();
        Entity<GeoLocation[]> res = GSON.fromJson(json, type); 
        return Arrays.asList(res.getData());
    }
}

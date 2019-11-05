package com.objectia.api;

import com.google.gson.annotations.SerializedName;

import com.objectia.ObjectiaClient;
import com.objectia.exceptions.APIException;

/**
 * Usage API model class.
 */
public class Usage {
    /**
     * Geo ip requests
     */
    @SerializedName("geoip_requests")
    private final int geoLocationRequests;

    @SerializedName("mail_requests")
    private final int mailRequests;

    /**
     * Class constructor.
     */
    private Usage() {
        this.geoLocationRequests = 0;
        this.mailRequests = 0;
    }

    /**
     * 
     * @return a Usage object
     */
    public static Usage get() throws APIException, IllegalArgumentException {
        return ObjectiaClient.get("/v1/usage", Usage.class);
    }

    /**
     * Get the number of geoip request 
     * @return number of requests
     */
    public int getGeoLocationRequests() {
        return this.geoLocationRequests;
    }

    /**
     * Get the number of mail request
     * @return number of requests
     */
    public int getMailRequests() {
        return this.mailRequests;
    }
}

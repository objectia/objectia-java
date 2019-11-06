package com.objectia.models;

import com.google.gson.annotations.SerializedName;

/**
 * APIUsage model.
 */
public class APIUsage {
    /**
     * Number of geolocation API requests
     */
    @SerializedName("geoip_requests")
    private final int geoLocationRequests;

    /**
     * Number of mail API requests
     */
    @SerializedName("mail_requests")
    private final int mailRequests;

    /**
     * Class constructor.
     */
    public APIUsage() {
        this.geoLocationRequests = 0;
        this.mailRequests = 0;
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

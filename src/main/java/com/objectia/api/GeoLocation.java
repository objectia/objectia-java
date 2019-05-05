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
  
    @SerializedName("ip")
    private final String ipAddress = null;        

    @SerializedName("type")
    private final String type = null;  // ipv4 or ipv6       

    @SerializedName("hostname")
	private final String hostname = null;               

    @SerializedName("continent")
	private final String continent = null;           

    @SerializedName("continent_code")
	private final String continentCode = null;       

    @SerializedName("country_name")
    private final String country = null;             

    @SerializedName("country_name_native")
    private final String countryNative = null;       

    @SerializedName("country_code")
    private final String countryCode = null;

    @SerializedName("country_code3")
    private final String countryCode3 = null;        

    @SerializedName("capital")
	private final String capital = null;             

    @SerializedName("region_name")
	private final String region = null;                 

    @SerializedName("region_code")
	private final String regionCode = null;             

    @SerializedName("city")
	private final String city = null;                    

    @SerializedName("postcode")
	private final String postcode = null;                

    @SerializedName("latitude")
	private final float latitude = 0;              

    @SerializedName("longitude")
	private final float longitude = 0;    

    @SerializedName("phone_prefix")
	private final String phonePrefix = null;            

    @SerializedName("currencies")
    private final IPCurrency[] currencies = null;
    
    @SerializedName("languages")
    private final IPLanguage[] languages = null;

    @SerializedName("flag")
    private final String flag = null;                   

    @SerializedName("flag_emoji")
	private final String flagEmoji = null;              
    
    @SerializedName("is_eu")
    private final Boolean isEU = false;        

    @SerializedName("internet_tld") 
	private final String tld = null;                    

    @SerializedName("timezone")
    private final IPTimezone timezone = null;
    
    @SerializedName("security")
    private final IPSecurity security = null;

    /**
     * Class constructor.
     */
    private GeoLocation() {}

    /**
     * Get geolocation by IP address
     * @param ip ip address
     * @return geolocation
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static GeoLocation get(final String ip) throws APIException, IllegalArgumentException {
        return GeoLocation.get(ip, "", false, false);
    }

    /**
     * Get geolocation by IP address
     * @param ip ip address
     * @param fields fields to return
     * @param hostname look up host name
     * @param security additional security info
     * @return geolocation
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static GeoLocation get(final String ip, final String fields, final Boolean hostname, final Boolean security) throws APIException, IllegalArgumentException {
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
        return GeoLocation.getCurrent("", false, false);
    }

    /**
     * Get geolocation for requester's current IP address.
     * @param fields fields to return
     * @param hostname look up host name
     * @param security additional security info
     * @return geolocation
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static GeoLocation getCurrent(final String fields, final Boolean hostname, final Boolean security) throws APIException, IllegalArgumentException {
        return GeoLocation.get("myip", fields, hostname, security);        
    }

    /**
     * Get geolocation for multiple IP addresses
     * @param ipList array of ip or domain names
     * @return geolocation array
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static List<GeoLocation> getBulk(final String ipList[]) throws APIException, IllegalArgumentException {
        return GeoLocation.getBulk(ipList, "", false, false);
    }

    /**
     * Get geolocation for multiple IP addresses
     * @param ipList array of ip or domain names
     * @param fields fields to return
     * @param hostname look up host name
     * @param security additional security info
     * @return geolocation array
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static List<GeoLocation> getBulk(final String ipList[], final String fields, final Boolean hostname, final Boolean security) throws APIException, IllegalArgumentException {
        if (ipList == null || ipList.length == 0) {
            throw new IllegalArgumentException("An IP address was not provided");
        }
        String ips = StringUtils.join(ipList, ",");
        RestClient restClient = ObjectiaClient.getRestClient();
        Response resp = restClient.get("/geoip/" + ips); 
        return GeoLocation.fromJSONArray(resp.getBody());
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


    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getType() {
        return this.type;
    }

    public String getHostname() {
        return this.hostname;
    }

    public String getContinent() {
        return this.continent;
    }

    public String getContinentCode() {
        return this.continentCode;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCountryNative() {
        return this.countryNative;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getCountryCode3() {
        return this.countryCode3;
    }

    public String getCapital() {
        return this.capital;
    }

    public String getRegion() {
        return this.region;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public String getCity() {
        return this.city;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public String getPhonePrefix() {
        return this.phonePrefix;
    }

    public IPCurrency[] getCurrencies() {
        return this.currencies;
    }

    public IPLanguage[] getLanguages() {
        return this.languages;
    }

    public String getFlag() {
        return this.flag;
    }

    public String getFlagEmoji() {
        return this.flagEmoji;
    }

    public Boolean getIsEU() {
        return this.isEU;
    }

    public Boolean isIsEU() {
        return this.isEU;
    }

    public String getTld() {
        return this.tld;
    }

    public IPTimezone getTimezone() {
        return this.timezone;
    }

    public IPSecurity getSecurity() {
        return this.security;
    }
}

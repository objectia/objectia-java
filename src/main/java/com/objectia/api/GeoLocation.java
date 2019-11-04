package com.objectia.api;

import java.util.Arrays;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import com.objectia.ObjectiaClient;
import com.objectia.utils.StringUtils;
import com.objectia.exceptions.APIException;

/**
 * Usage GeoLocation model class.
 */
public class GeoLocation {
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
        String query = makeQuery(fields, hostname, security);
        return ObjectiaClient.get("/v1/geoip/" + ip + query, GeoLocation.class);
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
        String query = makeQuery(fields, hostname, security);
        return Arrays.asList(ObjectiaClient.get("/v1/geoip/" + ips + query, GeoLocation[].class));
    }

    private static String makeQuery(final String fields, final Boolean hostname, final Boolean security) {
        StringBuilder sb = new StringBuilder();

        if (fields != null && fields.length() > 0) {
            sb.append("?fields="+fields);
        }
        if (hostname) {
            sb.append(sb.length() == 0 ? "?" : "&");
            sb.append("hostname=true");
        }
        if (security) {
            sb.append(sb.length() == 0 ? "?" : "&");
            sb.append("security=true");
        }

        return sb.toString();
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

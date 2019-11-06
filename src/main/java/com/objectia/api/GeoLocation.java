package com.objectia.api;

import java.util.Arrays;
import java.util.List;

import com.objectia.ObjectiaClient;
import com.objectia.models.IPLocation;
import com.objectia.utils.StringUtils;
import com.objectia.exceptions.APIException;

/**
 * GeoLocation API class.
 */
public class GeoLocation {
    /**
     * Class constructor.
     */
    private GeoLocation() {}

    /**
     * Get geolocation by IP address
     * 
     * @param ip ip address
     * @return geolocation
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static IPLocation get(final String ip) throws APIException, IllegalArgumentException {
        return GeoLocation.get(ip, "", false, false);
    }

    /**
     * Get geolocation by IP address
     * 
     * @param ip ip address
     * @param fields fields to return
     * @param hostname look up host name
     * @param security additional security info
     * @return geolocation
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static IPLocation get(final String ip, final String fields, final Boolean hostname, final Boolean security) throws APIException, IllegalArgumentException {
        if (ip == null) {
            throw new IllegalArgumentException("An IP address was not provided");
        }
        String query = makeQuery(fields, hostname, security);
        return ObjectiaClient.get("/v1/geoip/" + ip + query, IPLocation.class);
    }

    /**
     * Get geolocation for requester's current IP address.
     * 
     * @return geolocaiton
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static IPLocation getCurrent() throws APIException, IllegalArgumentException {
        return GeoLocation.getCurrent("", false, false);
    }

    /**
     * Get geolocation for requester's current IP address.
     * 
     * @param fields fields to return
     * @param hostname look up host name
     * @param security additional security info
     * @return geolocation
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static IPLocation getCurrent(final String fields, final Boolean hostname, final Boolean security) throws APIException, IllegalArgumentException {
        return GeoLocation.get("myip", fields, hostname, security);        
    }

    /**
     * Get geolocation for multiple IP addresses
     * 
     * @param ipList array of ip or domain names
     * @return geolocation array
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static List<IPLocation> getBulk(final String ipList[]) throws APIException, IllegalArgumentException {
        return GeoLocation.getBulk(ipList, "", false, false);
    }

    /**
     * Get geolocation for multiple IP addresses
     * 
     * @param ipList array of ip or domain names
     * @param fields fields to return
     * @param hostname look up host name
     * @param security additional security info
     * @return geolocation array
     * @throws APIException
     * @throws IllegalArgumentException
     */
    public static List<IPLocation> getBulk(final String ipList[], final String fields, final Boolean hostname, final Boolean security) throws APIException, IllegalArgumentException {
        if (ipList == null || ipList.length == 0) {
            throw new IllegalArgumentException("An IP address was not provided");
        }
        String ips = StringUtils.join(ipList, ",");
        String query = makeQuery(fields, hostname, security);
        return Arrays.asList(ObjectiaClient.get("/v1/geoip/" + ips + query, IPLocation[].class));
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
}

package com.objectia.models;

import com.google.gson.annotations.SerializedName;

/*
 * IPSecurity model
 */
public final class IPSecurity {
    @SerializedName("is_proxy")
    private final Boolean isAProxy = false;        

    @SerializedName("proxy_type")
    private final String proxyType = null;        

    @SerializedName("is_crawler")
    private final Boolean isACrawler = false;        

    @SerializedName("crawler_name")
    private final String crawlerName = null;        

    @SerializedName("crawler_type")
    private final String crawlerType = null;        

    @SerializedName("is_tor")
    private final Boolean isTor = false;        

    @SerializedName("threat_level")
    private final String threatLevel = null;        

    @SerializedName("threat_types")
    private final String[] threatTypes = null;        

    /**
     * Class constructor.
     */
    public IPSecurity() {}

    public Boolean isProxy() {
		return this.isAProxy;
	}

    public String getProxyType() {
		return this.proxyType;
	}

    public Boolean isCrawler() {
		return this.isACrawler;
    }
    
    public String getCrawlerName() {
		return this.crawlerName;
	}

    public String getCrawlerType() {
		return this.crawlerType;
	}

    public Boolean isTOR() {
		return this.isTor;
    }

    public String getThreatLevel() {
		return this.threatLevel;
	}

    public String[] getThreatTypes() {
		return this.threatTypes;
	}
}

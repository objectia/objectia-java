package com.objectia.models;

import java.util.Date;
import com.google.gson.annotations.SerializedName;

/*
 * IPTimezone model
 */
public final class IPTimezone {
	@SerializedName("id")
	private final String id = null;

	@SerializedName("localtime")
	private final Date localTime = null;

    @SerializedName("gmt_offset")
	private final int gmtOffset = 0;           

    @SerializedName("code")
	private final String code = null;     

    @SerializedName("daylight_saving")
	private final Boolean daylightSaving = false;         

    public IPTimezone() {}	

	public String getId() {
		return this.id;
	}

	public Date getLocalTime() {
		return this.localTime;
	}

	public int getGmtOffset() {
		return this.gmtOffset;
	}

	public String getCode() {
		return this.code;
	}

	public Boolean getDaylightSaving() {
		return this.daylightSaving;
	}

	public Boolean isDaylightSaving() {
		return this.daylightSaving;
	}
}
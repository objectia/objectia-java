package com.objectia.api;

import com.google.gson.annotations.SerializedName;

public final class IPLanguage {
    @SerializedName("code")
    private final String code = null; 

    @SerializedName("code2  ")
    private final String code2 = null; 

    @SerializedName("name")
    private final String name = null; 

    @SerializedName("native_name")
    private final String nativeName = null; 

    @SerializedName("rtl")
    private final Boolean rtl = false; 

    public IPLanguage() {}

    public String getCode() {
        return this.code;
    }

    public String getCode2() {
        return this.code2;
    }

    public String getName() {
        return this.name;
    }

    public String getNativeName() {
        return this.nativeName;
    }

    public Boolean getRtl() {
        return this.rtl;
    }

    public Boolean isRtl() {
        return this.rtl;
    }

}

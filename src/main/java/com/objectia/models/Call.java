package com.objectia.models;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * Call receipt
 */
public class Call {
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

    @SerializedName("ignored")
    private final boolean ignored;

    @SerializedName("phone")
    private final String phone;

    /**
     * CLass constructor
     */
    public Call() {
        this.ignored = false;
        this.phone = null;
    }

    /**
     * Checks if the ignored flag is set
     * 
     * @return true if the call was ignored, false otherwise
     */
    public boolean isIgnored() {
        return this.ignored;
    }

    /**
     * Gets the (masked) phone number where the call was sent
     * 
     * @return a string containing the phone number
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Creates a Call receipt from a JSON string. 
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
     * @return a Call receipt
     */
    public static Call fromJSON(final String json) {
        Type type = TypeToken.getParameterized(Entity.class, Call.class).getType();
        Entity<Call> res = GSON.fromJson(json, type); 
        return res.getData();
    }
}

package com.objectia.models;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * User model class.
 */
public class User {
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
    
    /**
     * User id
     */
    @SerializedName("id")
    private final String id;

    /**
     * Email
     */
    @SerializedName("email")
    private final String email;

    /**
     * Phone number
     */
    @SerializedName("phone")
    private final String phone;

    /**
     * Phone country code
     */
    @SerializedName("country_code")
    private final int countryCode;

    /**
     * Registered
     */
    @SerializedName("registered")
    private final boolean registered;

    /**
     * Confirmed
     */
    @SerializedName("confirmed")
    private final boolean confirmed;
    
    /**
     * Class constructor.
     */
    public User() {
        this.id = null;
        this.email = null;
        this.phone = null;
        this.countryCode = 0;
        this.registered = false;
        this.confirmed = false;
    }

    /**
     * Gets user's id.
     * 
     * @return a string with the user id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets user's email address
     * 
     * @return a string containing the user's email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets user's phone number
     * 
     * @return a string containing the user's phone number
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Gets user's phone country code
     * 
     * @return a numeric country code
     */
    public int getCountryCode() {
        return this.countryCode;
    }

    /**
     * Returns true if user is registered with an app.
     * 
     * @return a boolean that indicates if the user has an registered app.
     */
    public boolean isRegistered() {
        return this.registered;
    }

    /**
     * Returns true if the has confirmed his/her credentials.
     * 
     * @return a boolean that indicates if the user is confirmed.
     */
    public boolean isConfirmed() {
        return this.confirmed;
    }

    /**
     * Creates a User object from a JSON string. 
     * 
     * This JSON has the following format:
     * <p>{
     *   "status": 200,
     *   "data": {
     *      ... user details here ...
     *   } 
     * }</p>
     * 
     * @param json a string containing JSON to be converted
     * @return a User
     */
    public static User fromJSON(final String json) {
        Type type = TypeToken.getParameterized(Entity.class, User.class).getType();
        Entity<User> res = GSON.fromJson(json, type); 
        return res.getData();
    }

    /**
     * Creates a json string from the User
     * 
     * @return a JSON data string
     */
    public String toJSON() {
        return GSON.toJson(this);
    }
}

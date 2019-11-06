package com.objectia.models;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * MailReceipt model.
 */
public class MailReceipt {
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

    /**
     * Submit ID
     */
    @SerializedName("id")
    private final String ID;

    /**
     * Accepted recipients
     */
    @SerializedName("accepted_recipients")
    private final int acceptedRecipients;

    /**
     * Rejected recipients
     */
    @SerializedName("rejected_recipients")
    private final int rejectedRecipients;

    
	/**
	 * Class constructor
	 */
	public MailReceipt() {
        this.ID = "";
        this.acceptedRecipients = 0;
        this.rejectedRecipients = 0;
	}

    /**
     * Get the submit ID
     * @return a string with submit ID
     */
    public String getSubmitID() {
        return this.ID;
    }

    /**
     * Get the number of accepted recipients 
     * @return number of requests
     */
    public int getAcceptedRecipients() {
        return this.acceptedRecipients;
    }

    /**
     * Get the number of rejected recipients 
     * @return number of requests
     */
    public int getRejectedRecipients() {
        return this.rejectedRecipients;
    }

    /**
     * Creates a Usage object from a JSON string. 
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
     * @return Usage
     */
    public static MailReceipt fromJSON(final String json) {
        Type type = TypeToken.getParameterized(Entity.class, MailReceipt.class).getType();
        Entity<MailReceipt> res = GSON.fromJson(json, type); 
        return res.getData();
    }
}
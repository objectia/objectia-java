package com.objectia.api;

import com.google.gson.annotations.SerializedName;

import com.objectia.ObjectiaClient;
import com.objectia.models.Response;
import com.objectia.models.MailMessage;
import com.objectia.models.MailReceipt;
import com.objectia.RestClient;
import com.objectia.exceptions.APIException;

/**
 * Mail API model class.
 */
public class Mail {
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
     * Class constructor.
     */
    private Mail() {
        this.acceptedRecipients = 0;
        this.rejectedRecipients = 0;
        this.ID = "";
    }

    /**
     * 
     * @return
     */
    public static MailReceipt send(MailMessage message) throws APIException, IllegalArgumentException {
        return ObjectiaClient.post("/v1/usage", MailReceipt.class);
    }

    /**
     * Get the number of accepted recipients 
     * @return number of requests
     */
    public int getAcceptedRecipients() {
        return this.acceptedRecipients;
    }


}

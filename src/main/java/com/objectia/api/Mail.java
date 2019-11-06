package com.objectia.api;

import com.objectia.ObjectiaClient;
import com.objectia.exceptions.APIException;
import com.objectia.models.MailMessage;
import com.objectia.models.MailReceipt;

import org.apache.http.HttpEntity;

/**
 * Mail API model class.
 */
public class Mail {
    /**
     * Class constructor.
     */
    private Mail() {}

    /**
     * Send mail message. 
     * 
     * @return a mail receipt object with status info.
     */
    public static MailReceipt send(MailMessage message) throws APIException, IllegalArgumentException {
        HttpEntity content = message.asFormContent();
        return ObjectiaClient.post("/v1/mail/send", content, MailReceipt.class);
    }
}

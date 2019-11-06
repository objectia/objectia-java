package com.objectia.api;

import com.objectia.ObjectiaClient;
import com.objectia.models.APIUsage;
import com.objectia.exceptions.APIException;

/**
 * Usage API class.
 */
public class Usage {
    /**
     * Class constructor.
     */
    private Usage() {}

    /**
     * Get API usage.
     * 
     * @return an object with API usage info.
     */
    public static APIUsage get() throws APIException, IllegalArgumentException {
        return ObjectiaClient.get("/v1/usage", APIUsage.class);
    }

}

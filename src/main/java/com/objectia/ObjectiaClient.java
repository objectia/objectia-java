package com.objectia;

/**
 * Singleton class to initialize ObjectiaClient.
 */
public final class ObjectiaClient {

    private static String apiKey;
    private static int timeout = 30;
    private static RestClient restClient = null;

    /**
     * Private constructor to prevent instantiation.
     */
    private ObjectiaClient() {
    }

    /**
     * Initialize the client with apikey and timeout
     * 
     * @param apiKey  API key
     * @param timeout connection timeout
     */
    public static void init(final String apiKey, final int timeout) {
        ObjectiaClient.setApiKey(apiKey);
        ObjectiaClient.setTimeout(timeout);
    }

    /**
     * Initialize the client with apikey
     * 
     * @param apiKey API key
     */
    public static void init(final String apiKey) {
        init(apiKey, 30);
    }

    /**
     * Set the api key
     * 
     * @param apiKey API key to use
     */
    public static void setApiKey(final String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("No API key provided");
        }

        if (!apiKey.equals(ObjectiaClient.apiKey)) {
            ObjectiaClient.invalidate();
        }

        ObjectiaClient.apiKey = apiKey;
    }

    /**
     * Set the timeout
     * 
     * @param timeout timeout to use
     */
    public static void setTimeout(final int timeout) {
        if (timeout != ObjectiaClient.timeout) {
            ObjectiaClient.invalidate();
        }
        ObjectiaClient.timeout = timeout;
    }

    /**
     * Get the rest client
     * 
     * @return the rest client
     */
    public static RestClient getRestClient() {
        if (ObjectiaClient.restClient != null) {
            return ObjectiaClient.restClient;
        }

        if (ObjectiaClient.apiKey == null) {
            throw new IllegalArgumentException(
                    "RestClient was used before ApiKey was set, please call ObjectiaClient.init()");
        }

        ObjectiaClient.restClient = new RestClient(ObjectiaClient.apiKey, ObjectiaClient.timeout);
        return ObjectiaClient.restClient;
    }

    /**
     * Set the rest client
     * 
     * @param restClient Rest client to use
     */
    public static void setRestClient(RestClient restClient) {
        ObjectiaClient.restClient = restClient;
    }

    /**
     * Clear out the Rest Client
     */
    public static void invalidate() {
        ObjectiaClient.restClient = null;
    }
}

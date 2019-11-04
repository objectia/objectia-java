package com.objectia;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import com.objectia.models.Entity;
import com.objectia.exceptions.ResponseException;


/**
 * Singleton class to initialize ObjectiaClient.
 */
public final class ObjectiaClient {

    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

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

        Unirest.config()
            .socketTimeout(timeout * 1000)
            .connectTimeout(timeout * 1000)
            .setDefaultHeader("Authorization", "Bearer " + apiKey);
    }

    public static <T> T get(final String path, Class<T> aClass) throws ResponseException {
        HttpResponse<JsonNode> response = Unirest.get(Constants.API_BASE_URL + path).asJson();
        if (response.getStatus() == 200) {
            return ObjectiaClient.fromJSON(response.getBody().toString(), aClass);
        } else {
            // Error
            throw new ResponseException(response.getStatus(), response.getStatusText());
        }
    }

    public static <T> T post(final String path, Class<T> aClass) throws ResponseException {
        HttpResponse<JsonNode> response = Unirest.post(Constants.API_BASE_URL + path).asJson();
        if (response.getStatus() == 200) {
            return ObjectiaClient.fromJSON(response.getBody().toString(), aClass);
        } else {
            // Error
            throw new ResponseException(response.getStatus(), response.getStatusText());
        }
    }


    public static <T> T fromJSON(final String json, Class<T> aClass) {
        Type type = TypeToken.getParameterized(Entity.class, aClass).getType();
        Entity<T> res = GSON.fromJson(json, type); 
        return res.getData();
    }

    /**
     * Creates a json string from the Usage
     * 
     * @return a JSON data string
     */
    public static String toJSON(Object obj) {
        return GSON.toJson(obj);
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

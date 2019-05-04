package com.objectia;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.objectia.exceptions.APIConnectionException;
import com.objectia.exceptions.APIException;
import com.objectia.exceptions.APITimeoutException;
import com.objectia.exceptions.ResponseException;
import com.objectia.Error;
import com.objectia.models.Response;

public class RestClient {

    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

    protected String apiUrl = Constants.API_BASE_URL;
    protected int timeout = Constants.DEFAULT_TIMEOUT;
    protected String apiKey;

    protected RestClient(final String apiKey, final int timeout) throws IllegalArgumentException {
        this.apiKey = apiKey;
        this.timeout = timeout;

        if (this.apiKey == null) {
            throw new IllegalArgumentException("No API keyprovided");
        }
    }

    //******************************************************

    public Response get(final String path) throws APIException {
        return execute("GET", path, null);
    }

    public Response post(final String path, final Object params) throws APIException {
        return execute("POST", path, params);
    }

    public Response put(final String path, final Object params) throws APIException {
        return execute("PUT", path, params);
    }

    public Response patch(final String path, final Object params) throws APIException {
        return execute("PATCH", path, params);
    }

    public Response delete(final String path) throws APIException {
        return execute("DELETE", path, null);
    }

    protected Response execute(final String method, final String path, final Object payload)
            throws APIException {
        String uri = this.apiUrl + path;

        HttpsURLConnection conn = null;
        try {
            URL url = new URL(uri);

            conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod(method);

            conn.setRequestProperty("Authorization", "Bearer " + this.apiKey);
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", Constants.USER_AGENT);

            conn.setConnectTimeout(this.timeout * 1000);
            conn.setReadTimeout(this.timeout * 2000);
            conn.setUseCaches(false);

            if (payload != null) {
                // Add payload
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                String json = GSON.toJson(payload);
                byte buffer[] = json.getBytes("UTF-8");
                conn.setFixedLengthStreamingMode(buffer.length);
                OutputStream os = conn.getOutputStream();
                os.write(buffer);
                os.flush();
                os.close();
            }

            String body = null;
            int statusCode = conn.getResponseCode();
            if (statusCode >= 200 && statusCode < 300) {
                body = getBody(conn.getInputStream());
            } else {
                body = getBody(conn.getErrorStream());
                Error err = GSON.fromJson(body, Error.class);
                //LOGGER.warning(err.getMessage());
                throw new ResponseException(err.getStatus(), err.getMessage());
            }

            return new Response(statusCode, body, conn.getHeaderFields());
        } catch (java.net.SocketTimeoutException e) {
            throw new APITimeoutException("The request timed out");
        } catch (IOException ex) {
            //LOGGER.severe("Unable to connect to server");
            throw new APIConnectionException(
                    "Unable to connect to server. Please check your internet connection and try again.");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    protected String getBody(final InputStream is) throws IOException {
        //\A is the beginning of the stream boundary
        Scanner scanner = new Scanner(is, "UTF-8");
        scanner.useDelimiter("\\A");
        String body = scanner.next();
        scanner.close();
        is.close();
        return body;
    }

    protected String encode(final String str) throws IllegalArgumentException{
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (Exception ex ) {
            throw new IllegalArgumentException("Failed to url encode argment: " + str);
        }
    }
}

    //***************************************************************

    /*protected Response execute(final String method, final String path, final Object payload)
            throws APIException {
        String uri = this.apiUrl + path;

        if (uri.startsWith("https://")) {
            return secureRequest(method, path, payload, token);
        }

        HttpURLConnection conn = null;
        try {
            URL url = new URL(uri);

            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(method);
            if (token != null) {
                conn.setRequestProperty("Authorization", "Bearer " + token.getAccessToken());
            }
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("User-Agent", Constants.USER_AGENT);

            conn.setConnectTimeout(this.timeout * 1000);
            conn.setReadTimeout(this.timeout * 2000);
            conn.setUseCaches(false);

            if (payload != null) {
                // Add payload
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                String json = GSON.toJson(payload);
                byte buffer[] = json.getBytes("UTF-8");
                conn.setFixedLengthStreamingMode(buffer.length);
                OutputStream os = conn.getOutputStream();
                os.write(buffer);
                os.flush();
                os.close();
            }

            String body = null;
            int statusCode = conn.getResponseCode();
            if (statusCode >= 200 && statusCode < 300) {
                body = getBody(conn.getInputStream());
            } else {
                body = getBody(conn.getErrorStream());
                Error err = GSON.fromJson(body, Error.class);
                //LOGGER.warning(err.getMessage());
                throw new ResponseException(err.getStatus(), err.getMessage());
            }

            return new Response(statusCode, body, conn.getHeaderFields());
        } catch (java.net.SocketTimeoutException e) {
            throw new APITimeoutException("The request timed out");
        } catch (IOException ex) {
            //LOGGER.severe("Unable to connect to server");
            throw new APIConnectionException(
                    "Unable to connect to server. Please check your internet connection and try again.");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }*/


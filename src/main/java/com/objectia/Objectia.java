package com.objectia;

/**
 * Singleton class to initialize Objectia environment.
 */
public final class Objectia {

    private static String _apiKey;
    private static int _timeout = 30;
    private static RestClient _restClient = null;

    /**
     * Private constructor to prevent instantiation.
     */
    private Objectia() {}

    /**
     * Initialize the client with apikey and timeout
     * @param apiKey API key
     * @param timeout connection timeout
     */
    public static void init(final String apiKey, final int timeout) {
        SetApiKey(apiKey);
        SetTimeout(timeout);
    }

    /**
     * Initialize the client with apikey
     * @param apiKey API key
     */
    public static void init(final String apiKey) {
        init(apiKey, 30);
    }

    /**
     * Set the api key
     * @param apiKey API key to use
     */
    public static void SetApiKey(final String apiKey)
    {
        if (apiKey == null)
        {
            throw new IllegalArgumentException("No API key provided");
        }

        if (apiKey != _apiKey)
        {
            Invalidate();
        }

        _apiKey = apiKey;
    }

    /**
     * Set the timeout
     * @param timeout timeout to use
     */
    public static void SetTimeout(final int timeout)
    {
        if (timeout != _timeout)
        {
            Invalidate();
        }
        _timeout = timeout;
    }

    /**
     * Get the rest client
     * @return the rest client
     */
    public static RestClient GetRestClient()
    {
        if (_restClient != null)
        {
            return _restClient;
        }

        if (_apiKey == null)
        {
            throw new IllegalArgumentException(
                "RestClient was used before ApiKey was set, please call ObjectiaClient.init()"
            );
        }

        _restClient = new RestClient(_apiKey, _timeout);
        return _restClient;
    }

    /**
     * Set the rest client
     * @param restClient Rest client to use
     */
    public static void SetRestClient(RestClient restClient)
    {
        _restClient = restClient;
    }

    /**
     *  Clear out the Rest Client
     */
    public static void Invalidate()
    {
        _restClient = null;
    }
       
        /*
        public async Task<User> CreateUser(string email, string phone, int countryCode, bool force = false)
        {
            JObject payload = new JObject();
            payload.Add("email", email);
            payload.Add("phone", phone);
            payload.Add("country_code", countryCode);

            var response = await this.RestClient.Post("/users", payload);
            return User.FromJSON(response);
        }

        ///
        /// Get user status.
        ///
        public async Task<User> GetUser(string userId)
        {
            var response = await Get("/v1/users/" + Uri.EscapeDataString(userId));
            return User.FromJSON(response);
        }
*/







/*




    ** 
    * Class constructor.
    * 
    * @param clientId the client id to be used when accessing the API
    * @param clientSecret the client secret for your account.
    * @param sandbox use the sandbox api url if true, otherwise use live api url.
    
    public Client(final String clientId, final String clientSecret, final boolean sandbox) throws IllegalArgumentException {
        super(clientId, clientSecret, sandbox);
    }

    * 
    * Class constructor.
    * 
    * @param clientId the client id to be used when accessing the API
    * @param clientSecret the client secret for your account.
    
    public Client(final String clientId, final String clientSecret) throws IllegalArgumentException {
        super(clientId, clientSecret);
    }

    **
     * Creates a new user.
     * 
     * @param email a valid email address
     * @param phone a valid international phone number
     * @param countryCode a numeric country code
     * @param sendInstallLink a flag that indicates that an install link should be sent to the user
     * @return the User created
     *
    public User createUser(final String email, final String phone, final int countryCode, final boolean sendInstallLink) throws APIException, IllegalArgumentException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("phone", phone);
        params.put("country_code", countryCode);
        params.put("send_install_link", sendInstallLink);
        Response resp = post("/v1/users", params);
        return User.fromJSON(resp.getBody());
    }

    *
     * Creates a new user.
     * 
     * @param email a valid email address
     * @param phone a valid international phone number
     * @param countryCode a numeric country code
     * @return the User created
     *
    public User createUser(final String email, final String phone, final int countryCode) throws APIException, IllegalArgumentException {
        return createUser(email, phone, countryCode, false);
    }

    **
     * Get user by user id.
     * 
     * @param userId the id of the your to be retrieved
     * @return the User for the given id
     *
    public User getUser(final String userId) throws APIException, IllegalArgumentException {
        Response resp = get("/v1/users/" + encode(userId));
        return User.fromJSON(resp.getBody());
    }

    **
     * Remove user by user id.
     * 
     * @param userId the id of the your to be deleted
     * @return the User the given id
     *
    public User removeUser(final String userId) throws APIException, IllegalArgumentException { 
        Response resp = delete("/v1/users/" + encode(userId));
        return User.fromJSON(resp.getBody());
    }
    
    *
     * Request sms message to be sent to user.
     * 
     * @param userId the id of the your to be messaged
     * @param force a flag indicating if a sms should be sent even if an app has been registered for this user
     * @return an Sms receipt
     *
    public Sms requestSms(final String userId, final boolean force) throws APIException, IllegalArgumentException  {   
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("force", force);
        Response resp = post("/v1/users/" + encode(userId) + "/sms", params);
        return Sms.fromJSON(resp.getBody());
    }

    **
     * Request sms message to be sent to user.
     * 
     * @param userId the id of the your to be messaged
     * @return an Sms receipt
     *
    public Sms requestSms(final String userId) throws APIException, IllegalArgumentException  {   
        return requestSms(userId, false);
    }
*/
}

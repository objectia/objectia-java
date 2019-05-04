package com.objectia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.objectia.Objectia;
import com.objectia.exceptions.APIConnectionException;
import com.objectia.exceptions.APIException;
import com.objectia.exceptions.ResponseException;
import com.objectia.models.Call;
import com.objectia.models.Entity;
import com.objectia.Error;
import com.objectia.models.Headers;
import com.objectia.models.Response;
import com.objectia.models.Sms;
import com.objectia.models.User;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ObjectiaTest {

    private static String apiKey = null;

    @BeforeClass
    public static void setUp() {
        apiKey = System.getenv("OBJECTIA_APIKEY");
    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void testGetUsage() throws APIException {
        try {
            Objectia.init(apiKey);
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }


    @Test
    public void testWithNoApiKey() throws APIException {
        try {
            Objectia.init(null);
            fail();
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    /*
    @Test
    public void testTimeout() throws APIException {
        Client cli = new Client(CLIENT_ID, CLIENT_SECRET);
        cli.setTimeout(10);
        assertEquals(10, cli.getTimeout());

        try {
            cli.setTimeout(0);
            fail();

        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }

        try {
            cli.setTimeout(1000);
            fail();

        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testApiUrl() throws APIException {
        Client cli = new Client(CLIENT_ID, CLIENT_SECRET);
        cli.setApiUrl("http://localhost");
        assertEquals("http://localhost", cli.getApiUrl());
    }

    @Test
    public void testGetUser() throws APIException {
        User user = client.getUser("123456");
        assertNotNull(user);
    }

    @Test
    public void testGetUnknownUser() throws APIException {
        try {
            client.getUser("000000");
            fail();

        } catch (ResponseException ex) {
            assertNotNull(ex);
            assertNotEquals(0, ex.getStatus());
        }
    }

    @Test
    public void testCreateUser() throws APIException {
        User user = client.createUser("jdoe@example.com", "+12125551234", 1);
        assertNotNull(user);
    }

    @Test
    public void testCreateUserWithLink() throws APIException {
        User user = client.createUser("jdoe@example.com", "+12125551234", 1, true);
        assertNotNull(user);
    }

    @Test
    public void testCreateUserWithInvalidEmail() throws APIException {
        try {
            client.createUser("jdoeexample.com", "+12125551234", 1);
            fail();
        } catch (ResponseException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testRemoveUser() throws APIException {
        User user = client.removeUser("123456");
        assertNotNull(user);
    }

    @Test
    public void testRemoveUnknownUser() throws APIException {
        try {
            client.removeUser("000000");
            fail();

        } catch (ResponseException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testRequestSms() throws APIException {
        Sms receipt = client.requestSms("123456");
        assertNotNull(receipt);
        @SuppressWarnings("unused") boolean ignored = receipt.isIgnored();
        @SuppressWarnings("unused") String phone = receipt.getPhone();
    }

    @Test
    public void testRequestSmsForce() throws APIException {
        Sms receipt = client.requestSms("123456", true);
        assertNotNull(receipt);
    }

    @Test
    public void testRequestSmsUnknown() throws APIException {
        try {
            client.requestSms("000000");
            fail();

        } catch (ResponseException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testRequestCall() throws APIException {
        Call receipt = client.requestCall("123456");
        assertNotNull(receipt);
        @SuppressWarnings("unused") boolean ignored = receipt.isIgnored();
        @SuppressWarnings("unused") String phone = receipt.getPhone();
    }

    @Test
    public void testRequestCallForce() throws APIException {
        Call receipt = client.requestCall("123456", true);
        assertNotNull(receipt);
    }

    @Test
    public void testRequestCallUnknown() throws APIException {
        try {
            client.requestCall("000000");
            fail();

        } catch (ResponseException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testEntity() {
        Entity<User> entity = new Entity<User>();
        assertEquals(0, entity.getStatus());       
        assertNull(entity.getMessage());
        assertEquals(false, entity.getSuccess());
    }

    @Test
    public void testError() {
        Error err = new Error();
        assertEquals(0, err.getStatus());       
        assertNull(err.getMessage());
        assertEquals(false, err.getSuccess());
    }

    @Test
    public void testResponse() {
        Response resp = new Response(400, "body");
        assertEquals(400, resp.getStatus());       
        assertEquals("body", resp.getBody());
        assertNull(resp.getHeaders());
    }

    @Test
    public void testHeaders() {
        Map<String, List<String>> headers = new HashMap<String, List<String>>();

        // Null headers
        Headers h1 = new Headers(null);
        assertNull(h1.get("?"));       
        assertNull(h1.values("?"));       

        // Empty headers
        Headers h2 = new Headers(headers);
        assertNull(h2.get("?"));       
        assertNull(h2.values("?"));       

        ArrayList<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        headers.put("h", a);
        Headers h3 = new Headers(headers);
        assertNotNull(h3.get("h"));       
        assertNotNull(h3.values("h"));       
    }
    */
}
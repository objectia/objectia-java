package com.objectia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
//import static org.junit.Assert.fail;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.objectia.ObjectiaClient;
import com.objectia.api.GeoLocation;
import com.objectia.api.Mail;
import com.objectia.api.Usage;
import com.objectia.exceptions.APIException;
import com.objectia.exceptions.ResponseException;

import com.objectia.models.MailMessage;
import com.objectia.models.MailReceipt;

public class ObjectiaTest {
    private static String apiKey = null;

    @BeforeClass
    public static void setUp() {
        apiKey = System.getenv("OBJECTIA_APIKEY");
        ObjectiaClient.init(apiKey);
    }

    @AfterClass
    public static void tearDown() {
    }

    @Test
    public void testfromJSON() {
        try {
            class X  {
                @SerializedName("param")
                private String param;
            
                public X() {}

                public void setParam(final String p) {
                    this.param = p;
                }

                public String toJSON() {
                    Gson gson = new Gson();
                    return gson.toJson(this);
                }
            }

            X x = new X();
            x.setParam("Hello world");
            String json = x.toJSON();
            assertNotNull(json);
        } catch (Exception ex) {
            assertNull(ex);
        }
    }

    @Test
    public void testGetUsage() throws APIException {
        try {
            Usage usage = Usage.get();
            assertNotNull(usage);
            assertNotEquals(0, usage.getGeoLocationRequests());
        } catch (ResponseException ex) {
            assertNull(ex);
        } catch (IllegalArgumentException ex) {
            assertNull(ex);
        }
    }

    @Test
    public void testGetLocation() throws APIException {
        try {
            GeoLocation location = GeoLocation.get("8.8.8.8");
            assertNotNull(location);
            assertEquals("US", location.getCountryCode());
        } catch (ResponseException ex) {
            assertNull(ex);
        } catch (IllegalArgumentException ex) {
            assertNull(ex);
        }
    }

    @Test
    public void testGetBulkLocation() throws APIException {
        try {
            List<GeoLocation> locations = GeoLocation.getBulk(new String[]{"8.8.8.8", "apple.com"});
            assertNotNull(locations);
            assertEquals(2, locations.size());
            for (GeoLocation l : locations) {
                assertEquals("US", l.getCountryCode());
            }
        } catch (ResponseException ex) {
            assertNull(ex);
        } catch (IllegalArgumentException ex) {
            assertNull(ex);
        }
    }

    @Test
    public void testSendMail() throws APIException {
        try {
            MailMessage message = new MailMessage("ok@demo2.org", "Test", "This is a test", "ok@demo2.org");
            message.addAttachment("/Users/otto/me.png");
            message.setTestMode(true);
            MailReceipt receipt = Mail.send(message);

            assertNotNull(receipt);
            assertNotNull(receipt.getSubmitID());
            assertEquals(1, receipt.getAcceptedRecipients());
            assertEquals(0, receipt.getRejectedRecipients());
        } catch (ResponseException ex) {
            assertNull(ex);
        } catch (IllegalArgumentException ex) {
            assertNull(ex);
        }
    }
}
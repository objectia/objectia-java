package com.objectia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.objectia.ObjectiaClient;
import com.objectia.api.GeoLocation;
import com.objectia.api.Usage;
import com.objectia.exceptions.APIException;
import com.objectia.exceptions.ResponseException;

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
    public void testWithNoApiKey() throws APIException {
        try {
            ObjectiaClient.init(null);
            fail();
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testGetUsage() throws APIException {
        try {
            ObjectiaClient.init(apiKey);
            Usage usage = Usage.get();
            assertNotNull(usage);
            assertNotEquals(0, usage.getGeoLocationRequests());
        } catch (ResponseException ex) {
            assertNotNull(ex);
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testGetLocation() throws APIException {
        try {
            ObjectiaClient.init(apiKey);
            GeoLocation location = GeoLocation.get("8.8.8.8");
            assertNotNull(location);
            assertEquals("US", location.getCountryCode());
        } catch (ResponseException ex) {
            assertNotNull(ex);
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }

    @Test
    public void testGetBulkLocation() throws APIException {
        try {
            ObjectiaClient.init(apiKey);
            List<GeoLocation> locations = GeoLocation.getBulk(new String[]{"8.8.8.8", "apple.com"});
            assertNotNull(locations);
            assertEquals(2, locations.size());
            for (GeoLocation l : locations) {
                assertEquals("US", l.getCountryCode());
            }
        } catch (ResponseException ex) {
            assertNotNull(ex);
        } catch (IllegalArgumentException ex) {
            assertNotNull(ex);
        }
    }
}
package integrationtest;

import category.IntegrationTest;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


/**
 * Created by mcouliba on 16/02/2018.
 */
@Category(IntegrationTest.class)
public class ApiTest {

    private HttpClient httpClient;

    @Before
    public void setUp() throws Exception {
        httpClient = new HttpClient();
        httpClient.start();
    }


    @After
    public void tearDown() throws Exception {
        httpClient.stop();
    }

    @Test
    public void testHealthz() throws Exception {

        ContentResponse response = httpClient.newRequest("http://mlbparks-mitzicom-test.apps.46.4.112.21.xip.io/ws/healthz")
                .timeout(5, TimeUnit.SECONDS)
                .send();
        assertEquals(200, response.getStatus());
        assertEquals("OK", response.getContentAsString());
    }

    @Test
    public void testBackendController() throws Exception {

        String expectedResponse = "{\"id\":\"mlbparks\",\"displayName\":\"MLB Parks\",\"center\":{\"latitude\":\"39.82\",\"longitude\":\"-98.57\"},\"zoom\":5}";

        ContentResponse response = httpClient.newRequest("http://mlbparks-mitzicom-test.apps.46.4.112.21.xip.io/ws/info")
                .timeout(5, TimeUnit.SECONDS)
                .send();

        assertEquals(200, response.getStatus());
        assertEquals(expectedResponse, response.getContentAsString());
    }
}

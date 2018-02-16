package integrationtest;

import category.IntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;


/**
 * Created by mcouliba on 16/02/2018.
 */
@Category(IntegrationTest.class)
public class ApiTest {

    private String serverUrl = "http://parksmap-web-mitzicom-test.apps.46.4.112.21.xip.io";
    private String mlbparksUrl = "mlbparks-mitzicom-test.apps.46.4.112.21.xip.io";
    private String nationalparksUrl = "nationalparks-mitzicom-test.apps.46.4.112.21.xip.io";
    private RestTemplate restClient;
    private HttpHeaders headers;

    @Before
    public void setUp() throws Exception {
        restClient = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
    }


    @After
    public void tearDown() throws Exception {
        // NOTHING
    }

    @Test
    public void testHealthz() throws Exception {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = restClient.exchange(serverUrl + "/ws/healthz/", HttpMethod.GET, requestEntity, String.class);

        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals("OK", responseEntity.getBody());
    }

    @Test
    public void testBackendsController() throws Exception {

        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);

        String expectedResponse = "[{\"id\":\"mlbparks\",\"displayName\":\"MLB Parks\",\"center\":{\"latitude\":\"39.82\",\"longitude\":\"-98.57\"},\"zoom\":5,\"maxZoom\":1,\"type\":\"cluster\",\"visible\":true,\"scope\":\"all\"},{\"id\":\"nationalparks\",\"displayName\":\"National Parks\",\"center\":{\"latitude\":\"47.039304\",\"longitude\":\"14.505178\"},\"zoom\":4,\"maxZoom\":1,\"type\":\"cluster\",\"visible\":true,\"scope\":\"all\"}]";
        ResponseEntity<String> responseEntity1 = restClient.exchange(serverUrl + "/ws/backends/list", HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, responseEntity1.getStatusCode().value());
        assertEquals(expectedResponse, responseEntity1.getBody());
    }
}

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
    private String mlbparksUrl = "http://mlbparks-mitzicom-test.apps.46.4.112.21.xip.io";
    private String nationalparksUrl = "http://nationalparks-mitzicom-test.apps.46.4.112.21.xip.io";
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
        ResponseEntity<String> responseEntity1 = restClient.exchange(serverUrl + "/ws/backends/register?service=" + mlbparksUrl, HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, responseEntity1.getStatusCode().value());

        ResponseEntity<String> responseEntity2 = restClient.exchange(serverUrl + "/ws/backends/register?service=" + nationalparksUrl, HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, responseEntity2.getStatusCode().value());

        String expectedResponse = "OK";
        ResponseEntity<String> responseEntity3 = restClient.exchange(serverUrl + "/ws/backends/list", HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, responseEntity3.getStatusCode().value());
        assertEquals(expectedResponse, responseEntity3.getBody());

        ResponseEntity<String> responseEntity4 = restClient.exchange(serverUrl + "/ws/backends/unregister?service=" + mlbparksUrl, HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, responseEntity4.getStatusCode().value());
        ResponseEntity<String> responseEntity5 = restClient.exchange(serverUrl + "/ws/backends/unregister?service=" + nationalparksUrl, HttpMethod.GET, requestEntity, String.class);
        assertEquals(200, responseEntity5.getStatusCode().value());


    }
}

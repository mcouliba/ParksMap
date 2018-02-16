package unittest;

import category.UnitTest;
import com.openshift.evg.roadshow.rest.BackendController;
import com.openshift.evg.roadshow.rest.gateway.model.Backend;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

@Category(UnitTest.class)
public class BackendControllerTest {

    private BackendController backendController;

    @Before
    public void setup() {
        backendController = new BackendController();
    }

    @Test
    public void testGet() {
        String expectedId = "mlbparks";
        String expectedDisplayName = "MLB Parks";
        String expectedLat = "39.82";
        String expectedLng = "-98.57";
        int expectedZoom = 5;

        Backend result = backendController.get();

        assertEquals(expectedId, result.getId());
        assertEquals(expectedDisplayName, result.getDisplayName());
        assertEquals(expectedLat, result.getCenter().getLatitude());
        assertEquals(expectedLng, result.getCenter().getLongitude());
        assertEquals(expectedZoom, result.getZoom());
    }
}

package unittest;


import category.UnitTest;
import com.openshift.evg.roadshow.parks.rest.BackendController;
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
        String expectedId = "nationalparks";
        String expectedDisplayName = "National Parks";
        String expectedLat = "47.039304";
        String expectedLng = "14.505178";
        int expectedZoom = 4;

        Backend result = backendController.get();

        assertEquals(expectedId, result.getId());
        assertEquals(expectedDisplayName, result.getDisplayName());
        assertEquals(expectedLat, result.getCenter().getLatitude());
        assertEquals(expectedLng, result.getCenter().getLongitude());
        assertEquals(expectedZoom, result.getZoom());
    }
}

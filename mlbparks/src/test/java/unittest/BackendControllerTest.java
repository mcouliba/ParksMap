package unittest;

import com.openshift.evg.roadshow.rest.BackendController;
import com.openshift.evg.roadshow.rest.gateway.model.Backend;
import com.openshift.evg.roadshow.rest.gateway.model.Coordinates;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;


/**
 * Provides information about this backend
 *
 * Created by jmorales on 26/09/16.
 */
@Path("/info")
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

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Backend get() {
        return new Backend("mlbparks", "MLB Parks", new Coordinates("39.82", "-98.57"), 5);
    }
}

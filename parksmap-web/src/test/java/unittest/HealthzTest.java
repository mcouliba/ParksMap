package unittest;

import category.UnitTest;
import com.openshift.evg.roadshow.rest.Healthz;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

@Category(UnitTest.class)
public class HealthzTest {

    private Healthz healthz;

    @Before
    public void setup() {
        healthz = new Healthz();
    }

    @Test
    public void testHealthz() {
        assertEquals("OK", healthz.healthz());
    }
}

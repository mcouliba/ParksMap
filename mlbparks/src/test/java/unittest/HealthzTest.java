package unittest;

import com.openshift.evg.roadshow.rest.Healthz;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
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

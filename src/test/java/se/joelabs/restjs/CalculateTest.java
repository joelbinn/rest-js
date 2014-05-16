package se.joelabs.restjs;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Calculate Tester.
 */
public class CalculateTest {

    /**
     * Method: apply(int a, int b)
     */
    @Test
    public void testApply() throws Exception {
        System.out.println(new Calculate().apply(3,4));
        assertThat(new Calculate().apply(3, 4), is(7));
    }
}

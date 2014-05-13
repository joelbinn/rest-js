/*
 * User: joel
 * Date: 2014-05-14
 * Time: 00:08
 */
package se.joelabs.restjs;

import java.util.Arrays;

/**
*
*/
public class Result {
    final public int[] value;

    public Result(int[] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("value=").append(Arrays.toString(value));
        sb.append('}');
        return sb.toString();
    }
}

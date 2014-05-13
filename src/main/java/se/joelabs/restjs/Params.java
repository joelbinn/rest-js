/*
 * User: joel
 * Date: 2014-05-14
 * Time: 00:08
 */
package se.joelabs.restjs;

/**
 *
 */
public class Params {
    public static class CostRow {
        public boolean eligibleForSupport;
        public int[] amounts;

        public CostRow(boolean eligibleForSupport, int[] amounts) {
            this.eligibleForSupport = eligibleForSupport;
            this.amounts = amounts;
        }
    }

    public static class CoFinancingRow {
        public boolean eligibleForSupport;
        public int[] amounts;

        public CoFinancingRow(boolean eligibleForSupport, int[] amounts) {
            this.eligibleForSupport = eligibleForSupport;
            this.amounts = amounts;
        }
    }

    public final CostRow[] costs;
    public final CoFinancingRow[] coFinancing;

    public Params(CostRow[] costs, CoFinancingRow[] coFinancing) {
        this.costs = costs;
        this.coFinancing = coFinancing;
    }
}

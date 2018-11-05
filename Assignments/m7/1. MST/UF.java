/**
 * Class for uf.
 */
class UF {
    /**
     * { var_description }.
     */
    private int[] parent;
    /**
     * { var_description }.
     */
    private byte[] rank;
    /**
     * { var_description }.
     */
    private int count;
    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param      nn    the number of sites
     * @throws     IllegalArgumentException  if {@code n < 0}
     */
    public UF(final int nn) {
        if (nn < 0) {
            throw new IllegalArgumentException();
        }
        count = nn;
        parent = new int[nn];
        rank = new byte[nn];
        for (int i = 0; i < nn; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    /**
     * Returns the component identifier for the component containing site {@code
     * p}.
     *
     * @param      a     { parameter_description }
     *
     * @return     the component identifier for the component containing site
     *             {@code p}
     * @throws     IllegalArgumentException  unless {@code 0 <= p < n}
     */
    public int find(final int a) {
        int p = a;
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    /**
     * Returns the number of components.
     *
     * @return     the number of components
     * (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }
    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p}
     * and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(final int p, final int q) {
        return find(p) == find(q);
    }
    /**
     * Merges the component containing site {@code p}
     * with the the component
     * containing site {@code q}.
     *
     * @param      p     the integer representing one site
     * @param      q     the integer representing the other site
     * @throws     IllegalArgumentException  unless both
     * {@code 0 <= p < n} and {@code
     *                                       0 <= q < n}
     */
    public void union(final int p, final int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }
    /**
     * { function_description }.
     *
     * @param      p     { parameter_description }
     */
    private void validate(final int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p
                + " is not between 0 and " + (n - 1));
        }
    }
}
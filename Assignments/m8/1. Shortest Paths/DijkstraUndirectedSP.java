/**
 * Class for dijkstra undirected sp.
 */
class DijkstraUndirectedSP {
    /**
     * { var_description }.
     */
    private double[] distTo;
    /**
     * { var_description }.
     */
    private Edge[] edgeTo;
    /**
     * { var_description }.
     */
    private IndexMinPQ<Double> pq;
    /**
     * Computes a shortest-paths tree from the
     * source vertex {@code s} to every
     * other vertex in the edge-weighted graph
     * {@code G}.
     *
     * @param      gr    The graphics
     * @param      s     the source vertex
     * @throws     IllegalArgumentException 
     * if an edge weight is negative
     * @throws     IllegalArgumentException
     * unless {@code 0 <= s < V}
     */
    public DijkstraUndirectedSP(final EdgeWeightedGraph gr, final int s) {
        for (Edge e : gr.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }
        distTo = new double[gr.ver()];
        edgeTo = new Edge[gr.ver()];
        validateVertex(s);
        for (int v = 0; v < gr.ver(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        pq = new IndexMinPQ<Double>(gr.ver());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : gr.adj(v))
                relax(e, v);
        }
        assert check(gr, s);
    }
    /**
     * { function_description }.
     *
     * @param      e     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void relax(final Edge e, final int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            }
            else {
                pq.insert(w, distTo[w]);
            }
        }
    }
    /**
     * Returns the length of a shortest path between the source
     * vertex {@code s} and
     * vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return the length of a shortest path between the source
     * vertex {@code s} and
     *         the vertex {@code v}; {@code Double.POSITIVE_INFINITY}
     *         if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }
    /**
     * Returns true if there is a path between the source
     * vertex {@code s} and
     * vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path between
     * the source vertex
     *         {@code s} to vertex {@code v};
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     * {@code 0 <= v < V}
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     * Returns a shortest path between the source vertex {@code s}
     * and vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return a shortest path between the source vertex {@code s}
     * and vertex {@code v};
     *         {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge> pathTo(final int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
    /**
     * { function_description }.
     *
     * @param      gr    The graphics
     * @param      s     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final EdgeWeightedGraph gr, final int s) {
        for (Edge e : gr.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < gr.ver(); v++) {
            if (v == s) {
                continue;
            }
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }
        for (int v = 0; v < gr.ver(); v++) {
            for (Edge e : gr.adj(v)) {
                int w = e.other(v);
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge "
                        + e + " not relaxed");
                    return false;
                }
            }
        }
        for (int w = 0; w < gr.ver(); w++) {
            if (edgeTo[w] == null) {
                continue;
            }
            Edge e = edgeTo[w];
            if (w != e.either() && w != e.other(e.either())) {
                return false;
            }
            int v = e.other(w);
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge "
                    + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        int vr = distTo.length;
        if (v < 0 || v >= vr) {
            throw new IllegalArgumentException("vertex "
                + v + " is not between 0 and " + (vr - 1));
        }
    }
}
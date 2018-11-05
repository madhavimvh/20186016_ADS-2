/**
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**
     * { var_description }.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * { var_description }.
     */
    private final int ver;
    /**
     * { var_description }.
     */
    private int edg;
    /**
     * { var_description }.
     */
    private Bag<Edge>[] adj;
    /**
     * Initializes an empty edge-weighted graph with {@code V} vertices and 0
     * edges.
     *
     * @param      ver   The version
     * @throws     IllegalArgumentException  if {@code V < 0}
     */
    public EdgeWeightedGraph(final int ver) {
        if (ver < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        this.ver = ver;
        this.edg = 0;
        adj = (Bag<Edge>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    // /**
    //  * Initializes a random edge-weighted graph with {@code V} vertices and <em>E</em> edges.
    //  *
    //  * @param  V the number of vertices
    //  * @param  E the number of edges
    //  * @throws IllegalArgumentException if {@code V < 0}
    //  * @throws IllegalArgumentException if {@code E < 0}
    //  */
    // public EdgeWeightedGraph(int V, int E) {
    //     this(V);
    //     if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
    //     for (int i = 0; i < E; i++) {
    //         int v = StdRandom.uniform(V);
    //         int w = StdRandom.uniform(V);
    //         double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
    //         Edge e = new Edge(v, w, weight);
    //         addEdge(e);
    //     }
    // }

    // /**  
    //  * Initializes an edge-weighted graph from an input stream.
    //  * The format is the number of vertices <em>V</em>,
    //  * followed by the number of edges <em>E</em>,
    //  * followed by <em>E</em> pairs of vertices and edge weights,
    //  * with each entry separated by whitespace.
    //  *
    //  * @param  in the input stream
    //  * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
    //  * @throws IllegalArgumentException if the number of vertices or edges is negative
    //  */
    // public EdgeWeightedGraph(In in) {
    //     this(in.readInt());
    //     int E = in.readInt();
    //     if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
    //     for (int i = 0; i < E; i++) {
    //         int v = in.readInt();
    //         int w = in.readInt();
    //         validateVertex(v);
    //         validateVertex(w);
    //         double weight = in.readDouble();
    //         Edge e = new Edge(v, w, weight);
    //         addEdge(e);
    //     }
    // }

    /**
     * Initializes a new edge-weighted graph that is a deep copy of {@code G}.
     *
     * @param      gr    The graphics
     */
    public EdgeWeightedGraph(final EdgeWeightedGraph gr) {
        this(gr.ver());
        this.edg = gr.edg();
        for (int v = 0; v < gr.ver(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Edge> reverse = new Stack<Edge>();
            for (Edge e : gr.adj[v]) {
                reverse.push(e);
            }
            for (Edge e : reverse) {
                adj[v].add(e);
            }
        }
    }


    /**
     * Returns the number of vertices in this edge-weighted graph.
     *
     * @return     the number of vertices in this edge-weighted graph
     */
    public int ver() {
        return ver;
    }

    /**
     * Returns the number of edges in this edge-weighted graph.
     *
     * @return     the number of edges in this edge-weighted graph
     */
    public int edg() {
        return edg;
    }

    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= ver)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (ver - 1));
    }

    /**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param      e     the edge
     * @throws     IllegalArgumentException  unless both endpoints are between {@code
     *                                       0} and {@code V-1}
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        edg++;
    }

    /**
     * Returns the edges incident on vertex {@code v}.
     *
     * @param      v     the vertex
     *
     * @return     the edges incident on vertex {@code v} as an Iterable
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public Iterable<Edge> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param      v     the vertex
     *
     * @return     the degree of vertex {@code v}
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public int degree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns all edges in this edge-weighted graph. To iterate over the edges
     * in this edge-weighted graph, use foreach notation: {@code for (Edge e :
     * G.edges())}.
     *
     * @return     all edges in this edge-weighted graph, as an iterable
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < ver; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    /**
     * Returns a string representation of the edge-weighted graph. This method
     * takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return     the number of vertices <em>V</em>, followed by the number of
     *             edges <em>E</em>, followed by the <em>V</em> adjacency lists
     *             of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(ver + " " + edg + NEWLINE);
        for (int v = 0; v < ver; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
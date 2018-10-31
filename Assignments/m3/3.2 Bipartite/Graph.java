/**
 * The <tt>Graph</tt> class represents an undirected graph of vertices named 0
 * through V-1. It supports the following operations: add an edge to the graph,
 * iterate over all of the neighbors adjacent to a vertex. Parallel edges and
 * self-loops are permitted. <p> For additional documentation, see <a
 * href="http://algs4.cs.princeton.edu/51undirected">Section 5.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
/**
 * Class for graph.
 */
public class Graph {
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
    private Bag<Integer>[] adj;
   /**
     * Create an empty graph with V vertices.
     *
     * @param      ver   The version
     */
    public Graph(final int verr) {
        if (verr < 0) {
            throw new RuntimeException(
            "Number of vertices must be nonnegative");
        }
        this.ver = verr;
        this.edg = 0;
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

   /**
     * Create a random graph with V vertices and E edges. Expected running time
     * is proportional to V + E.
     *
     * @param      ver   The version
     * @param      edg   The edg
     */
    public Graph(final int verr, final int edgg) {
        this(verr);
        if (edg < 0) {
            throw new RuntimeException("Number of edges must be nonnegative");
        }
        for (int i = 0; i < edg; i++) {
            int v = (int) (Math.random() * verr);
            int w = (int) (Math.random() * verr);
            addEdge(v, w);
        }
    }

   /**
     * Create a digraph from input stream.
     */
/*    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }*/

   /**
     * Copy constructor.
     */
/*    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }*/

   /**
     * Return the number of vertices in the graph.
     *
     * @return     { description_of_the_return_value }
     */
    public int ver() {
        return ver;
    }

   /**
     * Return the number of edges in the graph.
     *
     * @return     { description_of_the_return_value }
     */
    public int edg() {
        return edg;
    }


   /**
     * Add the edge v-w to graph.
     *
     * @param      v     { parameter_description }.
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        edg++;
        adj[v].add(w);
        adj[w].add(v);
    }


   /**
     * Return the list of neighbors of vertex v as in Iterable.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }


   /**
     * Return a string representation of the graph.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        String newline = System.getProperty("line.separator");
        s.append(ver + " vertices, " + edg + " edges " + newline);
        for (int v = 0; v < ver; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(newline);
        }
        return s.toString();
    }

}


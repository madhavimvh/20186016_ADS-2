import java.util.Scanner;
/**
 * Class for directed cycle.
 */
class DirectedCycle {
    /**
     * { var_description }.
     */
    private boolean[] marked;
    /**
     * { item_description }.
     */
    private int[] edgeTo;
    /**
     * { var_description }.
     */
    private boolean[] onStack;
    /**
     * { var_description }.
     */
    private Stack<Integer> cycle;
    /**
     * Determines whether the digraph {@code G} has a directed cycle and, if so,
     * finds such a cycle.
     *
     * @param      gr    The graphics
     */
    DirectedCycle(final Digraph gr) {
        marked  = new boolean[gr.ver()];
        onStack = new boolean[gr.ver()];
        edgeTo  = new int[gr.ver()];
        for (int v = 0; v < gr.ver(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(gr, v);
            }
        }
    }

    /**
     * { function_description }.
     *
     * @param      gr    The graphics
     * @param      v     { parameter_description }
     */
    private void dfs(final Digraph gr, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : gr.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(gr, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Does the digraph have a directed cycle?
     *
     * @return     {@code true} if the digraph has a directed cycle, {@code
     *             false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and {@code
     * null} otherwise.
     *
     * @return     a directed cycle (as an iterable) if the digraph has a
     *             directed cycle, and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }


    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check() {
        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.err.printf(
                    "cycle begins with %d and ends with %d\n",
                    first, last);
                return false;
            }
        }


        return true;
    }
}
/**
 * Class for digraph.
 */
class Digraph {
    /**
     * { var_description }.
     */
    private static final String NEWLINE
    = System.getProperty("line.separator");
    /**
     * { var_description }.
     */
    private final int ver;
    /**
     * { item_description }.
     */
    private int edg;
    /**
     * { var_description }.
     */
    private Bag<Integer>[] adj;
    /**
     * { var_description }.
     */
    private int[] indegree;
    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param      vv    { parameter_description }
     * @throws     IllegalArgumentException  if {@code V < 0}
     */
    Digraph(final int vv) {
        if (vv < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.ver = vv;
        this.edg = 0;
        indegree = new int[ver];
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**
     * Initializes a new digraph that is a deep. copy of the specified digraph.
     *
     * @param      gr    The graphics
     */
    Digraph(final Digraph gr) {
        this(gr.ver());
        this.edg = gr.edg();
        for (int v = 0; v < ver; v++) {
            this.indegree[v] = gr.indegree(v);
        }
        for (int v = 0; v < gr.ver(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : gr.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }
    /**
     * Returns the number of vertices in this digraph.
     *
     * @return     the number of vertices in this digraph
     */
    public int ver() {
        return ver;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return     the number of edges in this digraph
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
        if (v < 0 || v >= ver) {
            throw new IllegalArgumentException("vertex "
                + v + " is not between 0 and " + (ver - 1));
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param      v     the tail vertex
     * @param      w     the head vertex
     * @throws     IllegalArgumentException
     * unless both {@code 0 <= v < V} and {@code
     *                                       0 <= w < V}
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edg++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param      v     the vertex
     *
     * @return     the vertices adjacent from vertex {@code v} in this digraph,
     *             as an iterable
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}. This
     * is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param      v     the vertex
     *
     * @return     the outdegree of vertex {@code v}
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}. This
     * is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param      v     the vertex
     *
     * @return     the indegree of vertex {@code v}
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return     the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(ver);
        for (int v = 0; v < ver; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return     the number of vertices <em>V</em>, followed by the number of
     *             edges <em>E</em>, followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(ver + " vertices, " + edg + " edges " + NEWLINE);
        for (int v = 0; v < ver; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
/**
 * { item_description }.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int ver = Integer.parseInt(scan.nextLine());
        int edg = Integer.parseInt(scan.nextLine());
        Digraph digraph = new Digraph(ver);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            digraph.addEdge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]));
        }
        DirectedCycle cycle = new DirectedCycle(digraph);
        if (cycle.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}

// import java.util.Scanner;
/**
 * Class for graphmatrix.
 */
public class Graph {
    /**
     * { var_description }.
     */
    private int ver;
    /**
     * { var_description }.
     */
    private int e;
    /**
     * { var_description }.
     */
    private int[][] adj;
    /**
     * Constructs the object.
     *
     * @param      verr  The verr
     */
    Graph(final int verr) {
        this.ver = verr;
        this.e = 0;
        this.adj = new int[ver][ver];
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int ver() {
        return ver;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public int edg() {
        return e;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        e++;
        adj[v][w] = 1;
        adj[w][v] = 1;

    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        Bag<Integer> bag =  new Bag<Integer>();
        for (int i = 0; i < ver; i++) {
            if (adj[v][i] == 1) {
                bag.add(i);
            }
        }
        return bag;
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return (adj[v][w] == 1);
    }
    /**
     * { function_description }.
     *
     * @param      str        The string
     *
     * @return     { description_of_the_return_value }
     *
     * @throws     Exception  { exception_description }
     */
    public String display(final String[] str)
    throws Exception {
        if (ver <= 1 && e <= 1) {
            System.out.println(ver() + " vertices"
                + ", " + edg() + " edges");
            throw new Exception("No edges");
        } else {
        System.out.println(ver() + " vertices"
            + ", " + edg() + " edges");
        String s = "";
        for (int i = 0; i < ver; i++) {
            for (int j = 0; j < ver; j++) {
                s += adj[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}
}


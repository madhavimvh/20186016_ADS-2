import java.util.Scanner;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    int ver();
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    int edg();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    void addEdge(int v, int w);
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**
 * Class for graphlist.
 */
class Graphlist implements Graph {
    /**
     * { var_description }.
     */
    private int ver;
    /**
     * { var_description }.
     */
    private int edg;
    /**
     * { var_description }.
     */
    private int e;
    /**
     * { var_description }.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      verr  The verr
     * @param      edgg  The edgg
     */
    Graphlist(final int verr, final int edgg) {
        this.ver = verr;
        this.edg = edgg;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
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
        if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
        e++;
        }
        adj[v].add(w);
        adj[w].add(v);
    }
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];

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
        for (int each : adj[v]) {
            if (each == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * { function_description }.
     *
     * @param      str        The string
     *
     * @throws     Exception  { exception_description }
     */
    public void display(final String[] str) throws Exception {
        if (ver <= 1 && edg <= 1) {
            System.out.println(ver() + " vertices"
                + ", " + edg() + " edges");
            throw new Exception("No edges");
        } else {
        System.out.println(ver() + " vertices"
            + ", " + edg() + " edges");
        String s = "";
        for (int i = 0; i < str.length; i++) {
            s = str[i] + ": ";
            for (int each : adj[i]) {
                s += str[each] + " ";
            }
            System.out.println(s);
        }
    }
    }
}
/**
 * Class for graphmatrix.
 */
class Graphmatrix implements Graph {
    /**
     * { var_description }.
     */
    private int ver;
    /**
     * { var_description }.
     */
    private int edg;
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
     * @param      edgg  The edgg
     */
    Graphmatrix(final int verr, final int edgg) {
        this.ver = verr;
        this.edg = edgg;
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
        if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
            e++;
        }
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
        if (ver <= 1 && edg <= 1) {
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
/**
 * Class for solution.
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
        String str = scan.nextLine();
        int v = Integer.parseInt(scan.nextLine());
        int e = Integer.parseInt(scan.nextLine());
        switch (str) {
            case "List":
            Graphlist list = new Graphlist(v, e);
            String[] str1 = scan.nextLine().split(",");
            while (scan.hasNext()) {
                String[] s = scan.nextLine().split(" ");
                // System.out.println(s[0]);
                list.addEdge(Integer.parseInt(s[0]),
                    Integer.parseInt(s[1]));
            }
            try {
            list.display(str1);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            break;
            case "Matrix":
            // v = Integer.parseInt(scan.nextLine());
            // e = Integer.parseInt(scan.nextLine());
            Graphmatrix matrix = new Graphmatrix(v, e);
            str1 = scan.nextLine().split(",");
            while (scan.hasNext()) {
                String[] s = scan.nextLine().split(" ");
                matrix.addEdge(Integer.parseInt(s[0]),
                    Integer.parseInt(s[1]));
            }
            try {
                System.out.println(matrix.display(str1));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            break;
            default:
            break;
        }
    }
}



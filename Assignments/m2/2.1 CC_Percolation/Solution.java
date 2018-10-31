/**
 * @author     : madhavi mvh
 */
import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
     * declaration of wtarr.
     */
    private Graph graph;
    /**
     * { declaration of wtarr }.
     */
    private boolean[] arr;
    /**
     * { declaration of n }.
     */
    private int n;
    /**
     * { declaration of top }.
     */
    private int top;
    /**
     * { declaration of bottom }.
     */
    private int bottom;
    /**
     * { declaration of size }.
     */
    private int size;
    /**
     * { declaration of opensize }.
     */
    private int opensize;
    /**
     * create n-by-n grid, with all sites blocked.
     *
     * @param      n1    The n 1
     */
    Percolation(final int n1) {
        this.n = n1;
        this.opensize = 0;
        this.size = n1 * n1;
        this.top = size;
        this.bottom = size + 1;
        this.arr = new boolean[size];
        this.graph = new Graph(size + 2);
        for (int i = 0; i < n; i++) {
            graph.addEdge(top, i);
            graph.addEdge(bottom, size - i - 1);
        }
    }
    /**
     * Searches for the first match.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int indexOf(final int i, final int j) {
        return n * (i - 1) + (j - 1);
    }
    /**
     * Connects opensites.
     *
     * @param      row   The row
     * @param      col   The col
     */
    public void connectOpensites(final int row, final int col) {
        if (arr[col] && !graph.hasEdge(row, col)) {
            graph.addEdge(row, col);
        }
    }
    /**
    * open site (row, col) if it is not open already.
    *
    * @param      row   The row
    * @param      col   The col
    */
    public void open(final int row, final int col) {
        int index = indexOf(row, col);
        arr[index] = true;
        int bottomrow = index + n;
        int toprow = index - n;
        opensize++;
        if (n == 1) {
            graph.addEdge(top, index);
            graph.addEdge(bottom, index);
        }
        if (bottomrow < size) {
            connectOpensites(index, bottomrow);
        }
        if (toprow >= 0) {
            connectOpensites(index, toprow);
        }
        if (col == 1) {
            if (col != n) {
                connectOpensites(index, index + 1);
            }
            return;
        }
        if (col == n) {
            connectOpensites(index, index - 1);
            return;
        }
        connectOpensites(index, index + 1);
        connectOpensites(index, index - 1);
    }
    /**
     * Determines if open.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return arr[indexOf(row, col)];
    }
    /**
     @return     { description_of_the_return_value }.
     */
    public     int numberOfOpenSites() {
        return opensize;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public boolean percolates() {
    	CC connectedComponents = new CC(graph);
        return connectedComponents.connected(top, bottom);
    }
}
/**
 * class  for solution.
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
        int n = Integer.parseInt(scan.nextLine());
        Percolation perc = new Percolation(n);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            perc.open(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]));
        }
        System.out.println(perc.percolates()
                    && perc.numberOfOpenSites() != 0);
    }
}

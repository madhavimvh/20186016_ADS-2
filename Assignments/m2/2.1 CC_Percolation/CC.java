/**
 * Class for cc.
 */
public class CC {
    /**
     * { var_description }.
     */
    private boolean[] marked;
    /**
     * { var_description }.
     */
    private int[] id;
    /**
     * { var_description }.
     */
    private int[] size;
    /**
     * { var_description }.
     */
    private int count;
    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param      gr    The graphics
     */
    public CC(final Graph gr) {
        marked = new boolean[gr.ver()];
        id = new int[gr.ver()];
        size = new int[gr.ver()];
        for (int v = 0; v < gr.ver(); v++) {
            if (!marked[v]) {
                dfs(gr, v);
                count++;
            }
        }
    }

    /**
     * { function_description }.
     *
     * @param      gr    The graphics
     * @param      v     { parameter_description }
     */
    private void dfs(final Graph gr, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : gr.adj(v)) {
            if (!marked[w]) {
                dfs(gr, w);
            }
        }
    }



    /**
     * Returns the component id of the connected component containing vertex
     * {@code v}.
     *
     * @param      v     the vertex
     *
     * @return     the component id of the connected component containing vertex
     *             {@code v}
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public int id(final int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * Returns the number of vertices in the connected component containing
     * vertex {@code v}.
     *
     * @param      v     the vertex
     *
     * @return     the number of vertices in the connected component containing
     *             vertex {@code v}
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     */
    public int size(final int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return     the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param      v     one vertex
     * @param      w     the other vertex
     *
     * @return     {@code true} if vertices {@code v} and {@code w} are in the
     *             same connected component; {@code false} otherwise
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     * @throws     IllegalArgumentException  unless {@code 0 <= w < V}
     */
    public boolean connected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param      v     one vertex
     * @param      w     the other vertex
     *
     * @return     {@code true} if vertices {@code v} and {@code w} are in the
     *             same connected component; {@code false} otherwise
     * @throws     IllegalArgumentException  unless {@code 0 <= v < V}
     * @throws     IllegalArgumentException  unless {@code 0 <= w < V}
     * @deprecated Replaced by {@link #connected(int, int)}.
     */
    @Deprecated
    public boolean areConnected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        int ver = marked.length;
        if (v < 0 || v >= ver) {
            throw new IllegalArgumentException("vertex "
                + v + " is not between 0 and " + (ver - 1));
        }
    }
}

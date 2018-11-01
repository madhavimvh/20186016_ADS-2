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
                // System.out.println("first");
                // System.out.println(v);
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
        // System.out.println("onStack");
        // System.out.println(Arrays.toString(onStack));
        marked[v] = true;
        // System.out.println("marked");
        // System.out.println(Arrays.toString(marked));
        for (int w : gr.adj(v)) {
            // System.out.println("lslk");
            // System.out.println(w);
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                // System.out.println(Arrays.toString(edgeTo));
                dfs(gr, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                // System.out.println("----------------");
                // System.out.println(v);
                // System.out.println(w);
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
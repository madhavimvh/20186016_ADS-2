/**
 * Class for sap.
 */
public class SAP {
    /**
     * { var_description }.
     */
    private Digraph gr;
    /**
     * { var_description }.
     */
    private int ancestors;

    // constructor takes a digraph (not necessarily a DAG)

    /**
     * Constructs the object.
     *
     * @param      gr    The graphics
     */
    public SAP(final Digraph gr) {
        this.gr = gr;
        ancestors = -1;
    }

    //
    // length of shortest ancestral path between any vertex in v and any vertex
    // in w; -1 if no such path
    
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(gr, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(gr, w);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < gr.ver(); i++) {
            if (bfdv.hasPathTo(i) && bfdw.hasPathTo(i)) {
                int dist = bfdv.distTo(i) + bfdw.distTo(i);
                if (dist < min) {
                    ancestors = i;
                    // System.out.println(dist);
                    min = dist;
                    // System.out.println(min);
                }
                
            }
        }
        return min;
    }

    // a common ancestor that participates in shortest
    // ancestral path; -1 if no such path
    
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final Iterable<Integer> v,
        Iterable<Integer> w) {
        return ancestors;
    }

    // do unit testing of this class
    // public static void main(String[] args)
}
public class SAP {
    private Digraph gr;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph gr) {
        this.gr = gr;
    }

    // length of shortest ancestral path between v and w; -1
    // if no such path
    // public int length(int v, int w) {
    // }

    // a common ancestor of v and w that participates in a
    // shortest ancestral path; -1 if no such path
    // public int ancestor(int v, int w)

    // length of shortest ancestral path between any vertex in v
    // and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(gr, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(gr, w);
        int min = 0;
        for (int i = 0; i < gr.ver(); i++) {
            if (bfdv.hasPathTo(i) && bfdw.hasPathTo(i)) {
                int dist = bfdv.distTo(i) + bfdw.distTo(i);
                if (dist < min) {
                    min = dist;
                }
                
            }
        }
        return min;
    }

    // a common ancestor that participates in shortest
    // ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int min = length(v, w);
        return min;
        
    }

    // do unit testing of this class
    // public static void main(String[] args)
}
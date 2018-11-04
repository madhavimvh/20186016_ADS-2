public class SAP {
    private Digraph gr;
    private int ancestors;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph gr) {
        this.gr = gr;
        ancestors = -1;
    }

    // length of shortest ancestral path between any vertex in v
    // and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfdv = new BreadthFirstDirectedPaths(gr, v);
        BreadthFirstDirectedPaths bfdw = new BreadthFirstDirectedPaths(gr, w);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < gr.ver(); i++) {
            if (bfdv.hasPathTo(i) && bfdw.hasPathTo(i)) {
                int dist = bfdv.distTo(i) + bfdw.distTo(i);
                if (dist < min) {
                    ancestors = i;
                    System.out.println(dist);
                    min = dist;
                    System.out.println(min);
                }
                
            }
        }
        return min;
    }

    // a common ancestor that participates in shortest
    // ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return ancestors;
    }

    // do unit testing of this class
    // public static void main(String[] args)
}
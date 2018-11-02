import java.util.Arrays;
import java.util.*;
/**
 * Class for word net.
 */
public class WordNet {
        /**
         * { var_description }.
         */
        private int vertices;
        /**
         * { var_description }.
         */
        private String hypernyms;
        /**
         * { var_description }.
         */
        private Digraph digraph;
        /**
         * { item_description }.
         */
        private LinearProbingHashST<String, List<Integer>> st;
        private ArrayList<String> synsetids;
        private SAP sap;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    WordNet(final String synsets, final String hypernyms) {
        vertices = readsyn(synsets);
        digraph = new Digraph(vertices);
        readhyn(hypernyms);
        st = new LinearProbingHashST<>();
        sap = new SAP(digraph);
    }
    WordNet() {
        
    }
    /**
     * { function_description }.
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public int readsyn(final String file) {
        In in = new In("./Files/" + file);
        String[] s1 = null;
        String[] s = null;
        while (!in.isEmpty()) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
            vertices++;
        s = in.readLine().split(",");
        int id = Integer.parseInt(s[0]);
        synsetids.add(id, s[1]);
        s1 = s[1].split(" ");
        System.out.println(s1.length);
            for (int i = 0; i < s1.length; i++) {
                System.out.println(st.contains(s1[i]));
                if (st.contains(s1[i])) {
                    ids.addAll(st.get(s[i]));
                    ids.add(id);
                    st.put(s[i], ids);
                } else {
                    ids.add(id);
                    st.put(s1[i], ids);
                }
            } 
        
        // System.out.println(Arrays.toString(s1));
    }
    return vertices;
}
    /**
     * { function_description }.
     *
     * @param      file  The file
     */
    public void readhyn(final String file)  {
        In in = new In("./Files/" + file);
        while(!in.isEmpty()) {
            String[] s = in.readString().split(",");
            // System.out.println(Arrays.toString(s));
            for (int i = 1; i < s.length; i++) {
                // System.out.println(s[0]);
                // System.out.println(s[1]);
                digraph.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[i]));
            }
        }
        DirectedCycle dir = new DirectedCycle(digraph);
        int count = 0;
        for (int i = 0; i < vertices; i++) {
            if (digraph.outdegree(i) == 0) {
                count++;
            }
        }
        if (count > 1) {
            throw new IllegalArgumentException("Multiple roots");
        }
        if(dir.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else {
        System.out.println(digraph);
        }

    }
    // returns all WordNet nouns

    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> nouns() {
        return st.keys();
    }
    // // is the word a WordNet noun?

    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        return st.contains(word);
    }
    // // distance between nounA and nounB (defined below)
    /**
     * { function_description }.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public int distance(final String nounA, final String nounB) {
        if (nounA == null || nounB == null) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        Iterable<Integer> nA = st.get(nounA);
        Iterable<Integer> nB = st.get(nounB);
        return sap.length(nA, nB);

    }
    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    // /**
    //  * { function_description }.
    //  *
    //  * @param      nounA  The noun a
    //  * @param      nounB  The noun b
    //  *
    //  * @return     { description_of_the_return_value }
    //  */
    public String sap(final String nounA, final String nounB) {
        // Iterable<Integer> nA = st.get(nounA);
        // Iterable<Integer> nB = st.get(nounB);
        // if (nA == null || nB == null) {
        //     throw new IllegalArgumentException("cannot be null");
        // }
        // int ancesid = sap.ancestor(nA, nB);
        int ancesid = distance(nounA, nounB);
        return synsetids.get(ancesid);

    }
    // // do unit testing of this class
    // /**
    //  * { function_description }.
    //  *
    //  * @param      args  The arguments
    //  */
    // public static void main(final String[] args) {
    // }
}

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
        private Digraph digraph;
        /**
         * { item_description }.
         */
        private LinearProbingHashST<String, ArrayList<Integer>> st;
        private List<String> synsetids;
        private SAP sap;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    WordNet(final String synsets, final String hypernyms) {
        st = new LinearProbingHashST<String, ArrayList<Integer>>();
        synsetids = new ArrayList<String>();
        vertices = readsyn(synsets);
        digraph = readhyn(hypernyms, vertices);
        sap = new SAP(digraph);
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
        int count = 0;
        while (!in.isEmpty()) {
            count++;
            String[] line = in.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            synsetids.add(id, line[1]);
            // Added to array list synset for look up.

            String[] nouns = line[1].split(" ");
            for (int i = 0; i < nouns.length; i++) {
                ArrayList<Integer> ids;
                if (st.contains(nouns[i])) {
                    ids = st.get(nouns[i]);
                    //ids.addAll(st.get(nouns[i]));
                    ids.add(id);
                } else {
                    ids = new ArrayList<Integer>();
                    ids.add(id);
                }
                st.put(nouns[i], ids);
            }
        }
        return count;
    }
    /**
     * { function_description }.
     *
     * @param      file  The file
     */
    public Digraph readhyn(final String file, final int vertices)  {
        Digraph digraph = new Digraph(vertices);
        In in = new In("./Files/" + file);
        while(!in.isEmpty()) {
            String[] s = in.readString().split(",");
            int v = Integer.parseInt(s[0]);
            for (int i = 1; i < s.length; i++) {
                digraph.addEdge(v, Integer.parseInt(s[i]));
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
        }
        return digraph;
    }
    // returns all WordNet nouns

    public Digraph getDigraph() {
        return digraph;
    }

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
    public boolean isNoun(final String nounA) {
        if (nounA.equals(null)) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        return st.contains(nounA);
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
        if (!isNoun (nounA) || !isNoun(nounB)) {
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
        int ancesid = sap.ancestor(st.get(nounA), st.get(nounB));
        if (ancesid == -1) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        return synsetids.get(ancesid);

    }
}

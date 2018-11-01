import java.util.Arrays;
import java.util.*;

public class WordNet {
        private int vertices;
        private String hypernyms;
        private Digraph digraph;
        // private SAP sap;
        // private int grlength;
        private LinearProbingHashST<String, List<Integer>> st;


    WordNet(String synsets, String hypernyms) {
        vertices = readsyn(synsets);
        
        digraph = new Digraph(vertices);
        readhyn(hypernyms);
        // sap = new SAP();
        // grlength = 0;
        st = new LinearProbingHashST<String, List<Integer>>();
    }
    public int readsyn(String file) {
        In in = new In("./Files/" + file);
        String[] s1 = null;
        String[] s = null;
        while (!in.isEmpty()) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
            vertices++;
        s = in.readString().split(",");
        int id = Integer.parseInt(s[0]);
        ids.add(id);
        if (s[1].length() > 1) {
        for (int i = 0; i < s[1].length(); i++) {
            s1 = s[1].split(" ");
            if (st.contains(s1[i])) {
                ids.addAll(st.get(s[i]));
                // ids.add(id);
                st.put(s[1], ids);
            } else {
                // ids.add(id);
            st.put(s1[i], ids);
            }
        } 
        }
        // System.out.println(Arrays.toString(s1));
    }
    return vertices;
}
    public void readhyn(String file) {
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
        if(dir.hasCycle()) {
            System.out.println("Cycle detected");
        } else {
        System.out.println(digraph);
        }
    }
    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return st.keys();
    }
    // // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return st.contains(word);
    }
    // // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB) {

    // }
    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    // public String sap(String nounA, String nounB) {

    // }

    // do unit testing of this class
    // public static void main(String[] args) {

    // }
}

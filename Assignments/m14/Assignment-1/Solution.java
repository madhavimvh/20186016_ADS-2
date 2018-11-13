import java.util.Scanner;
// import java.util.Arrays;
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
        String[] words = loadWords();
        // System.out.println(Arrays.toString(words));
        Scanner scan = new Scanner(System.in);
        TST<Integer> tst = new TST<Integer>();
        String prefix = scan.nextLine();
        int j = 0;
        for (String word : words) {
            SuffixArray sa = new SuffixArray(word);
            for (int i = 0; i < word.length(); i++) {
                tst.put(sa.select(i), j++);
            }
        }
        for (String word : tst.keysWithPrefix(prefix)) {
            System.out.println(word);
        }
        //Your code goes here...
    }
    /**
     * Loads words.
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}

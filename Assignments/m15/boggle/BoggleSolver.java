import java.util.Set;
import java.util.HashSet;
/**
 * Class for boggle solver.
 */
public final class BoggleSolver {
    /**
     * { var_description }.
     */
    // private static final int FOUR = 4;
    private static final int FIVE = 5;
    /**
     * { var_description }.
     */
    private static final int ELE = 11;
    /**
     * { var_description }.
     */
    private static final int EIGHT = 8;
    /**
     * { var_description }.
     */
    private TrieST<Integer> dicTrie;
    /**
     * { var_description }.
     */
    private Set<String> validwords;
    /**
     * { var_description }.
     */
    private boolean[][] marked;
    // Initializes the data structure using the given
    // array of strings as the dictionary.
    // (You can assume each word in the dictionary
    // contains only the uppercase letters A through Z.)
    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        validwords = new HashSet<String>();
        dicTrie = new TrieST<Integer>();
        int[] points = {0, 0, 0, 1, 1, 2, 2 + 1, FIVE, ELE};
        for (String word : dictionary) {
            if (word.length() >= EIGHT) {
                dicTrie.put(word, ELE);
            } else {
                dicTrie.put(word, points[word.length()]);
            }
        }
    }
    // Returns the set of all valid words in
    //the given Boggle board, as an Iterable.
    /**
     * Gets all valid words.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        if (board == null) {
            throw new IllegalArgumentException("board is null");
        }
        marked = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                String word = appendCharacter("", board.getLetter(i, j));
                dfs(i, j, board, word);
            }
        }
        return validwords;
    }
    /**
     * Appends a character.
     *
     * @param      wordd   The word
     * @param      letter  The letter
     *
     * @return     { description_of_the_return_value }
     */
    private String appendCharacter(final String wordd,
        final char letter) {
        String word = wordd;
        if (letter == 'Q') {
            word += "QU";
            // return word + "QU";
        } else {
            word += letter;
            // return word + letter;
        }
        return word;
    }
    /**
     * Determines if validword.
     *
     * @param      word  The word
     *
     * @return     True if validword, False otherwise.
     */
    private boolean isValidword(final String word) {
        if (word.length() < 2 + 1) {
            return false;
        }
        return dicTrie.contains(word);
    }
    /**
     * { function_description }.
     *
     * @param      rows   The rows
     * @param      cols   The cols
     * @param      board  The board
     * @param      word   The word
     */
    private void dfs(final int rows, final int cols,
        final BoggleBoard board, final String word) {
        if (!dicTrie.hasPrefix(word)) {
            return;
        }
        if (isValidword(word)) {
            validwords.add(word);
        }
        marked[rows][cols] = true;
        for (int row = rows - 1; row <= rows + 1; row++) {
            for (int col = cols - 1; col <= cols + 1; col++) {
                if (row >= 0 && row < board.rows() && col >= 0
                    && col < board.cols() && !marked[row][col]) {
                    String sequence = appendCharacter(word,
                        board.getLetter(row, col));
                    dfs(row, col, board, sequence);
                }
            }
        }
        marked[rows][cols] = false;
    }
    // Returns the score of the given word if
    // it is in the dictionary, zero otherwise.
    // (You can assume the word contains only
    // the uppercase letters A through Z.)
    /**
     * { function_description }.
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        if (word == null) {
            return 0;
        }
        if (dicTrie.contains(word)) {
            return dicTrie.get(word);
        } else {
            return 0;
        }
    }
}

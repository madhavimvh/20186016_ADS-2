import java.util.Set;
import java.util.HashSet;
public class BoggleSolver {
	private TrieST<Integer> dicTrie;
	private Set<String> validwords;
	private boolean[][] marked;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
		validwords = new HashSet<String>();
		dicTrie = new TrieST<Integer>();
		int[] points = {0, 0, 0, 1, 1, 2, 3, 5, 11};
		for (String word : dictionary) {
			if (word.length() >= 8) {
				dicTrie.put(word, 11);
			} else {
				dicTrie.put(word, points[word.length()]);
			}
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
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
	private String appendCharacter(String word, char letter) {
		if (letter == 'Q') {
			word += "QU";
		} else {
			word += letter;
		}
		return word;
	}
	private boolean isValidword(String word) {
		if (word.length() < 3) {
			return false;
		}
		return dicTrie.contains(word);
	}
	private void dfs(int rows, int cols, BoggleBoard board, String word) {
		if (!dicTrie.hasPrefix(word)) {
			return;
		}
		if (isValidword(word)) {
			validwords.add(word);
		}
		marked[rows][cols] = true;
		for (int row = rows - 1; row <= rows + 1; row++) {
			for (int col = cols - 1; col <= cols + 1; col++) {
				if (row >= 0 && row < board.rows() && col >= 0 && col < board.cols() && !marked[row][col]) {
					String sequence = appendCharacter(word, board.getLetter(row, col));
					dfs(row, col, board, sequence);
				}
			}
		}
		marked[rows][cols] = false;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (word == null) return 0;
		if (dicTrie.contains(word)) {
			return dicTrie.get(word);
		} else {
			return 0;
		}
	}
}
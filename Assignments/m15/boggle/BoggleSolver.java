import java.util.Set;
import java.util.HashSet;
public class BoggleSolver {
	private TrieST<Integer> dicTrie;
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	public BoggleSolver(String[] dictionary) {
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
		Set<String> validwords = new HashSet<String>();
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); i++) {
				boolean[][] marked = new boolean[board.rows()][board.cols()];
				dfs(validwords, i, j, marked, board, "");
			}
		}
		return validwords;
	}
	public void dfs(Set<String> validwords, int row, int col, boolean[][] marked, BoggleBoard board, String prefix) {
		if (marked[row][col]) {
			return;
		}
		String word = prefix;
		char letter = board.getLetter(row, col);
		if (letter == 'Q') {
			word += "QU";
		} else {
			word += letter;
		}
		if (!dicTrie.hasPrefix(word)) {
			return;
		}
		if (word.length() > 2 && dicTrie.contains(word)) {
			validwords.add(word);
		}
		marked[row][col] = true;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 || j == 0) {
					continue;
				}
				if ((row + i >= 0) && (row + i < board.rows()) && (col + j >= 0) && (col + j < board.cols())) {
					dfs(validwords, row + i, col + j, marked, board, word);					
				}
			}
		}
		marked[row][col] = false;

	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (dicTrie.contains(word)) {
			return dicTrie.get(word);
		} else {
			return 0;
		}
	}
}
import java.util.Arrays;
/**
 * { item_description }.
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
		String synsets = StdIn.readString();
		String hypernyms = StdIn.readString();
		String type = StdIn.readString();
		try {
			WordNet wordnet = new WordNet(synsets, hypernyms);
			if (type.equals("Graph")) {
				System.out.println(wordnet.getDigraph());
			} else {
				while (!StdIn.isEmpty()) {
					String nounA = StdIn.readString();
					String nounB = StdIn.readString();
					System.out.println("distance = " +
					                   wordnet.distance(nounA, nounB) + ", ancestor = " +
					                   wordnet.sap(nounA, nounB));
				}
			}
		} catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
}

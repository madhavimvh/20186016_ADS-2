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
		// wordnet.readfile(synsets);
		String type = StdIn.readString();
		try {
		if (type.equals("Graph")) {
			WordNet wordnet = new WordNet(synsets, hypernyms);
		//wordnet.readhyn()
		} 
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		if (type.equals("Queries")) {
			String[] arr = StdIn.readString().split(" ");
			if (arr[0].equals("null")) {
				System.out.println("IllegalArgumentException");
			}
			
		}
	}
}

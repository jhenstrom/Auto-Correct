/*
Ongoing project: this project aims to spell check given documents against a given dictionary. It will take two arguments. First 
will be the dictionary to use for the spell check. Eventually, the dictionary will be stored externally and then be refreneced
rather than having to be read in each time. The Second argument will be the document that you wish to review. The output will be
a copy of your input file with corrections made.

At the current utility, the program will take a dictionary and then a word to check as arguments, returning the word if it is
correct, or a suggestion if it is not correct
*/
package spell;

import java.io.IOException;

public class Main {

	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws IOException {

		String dictionaryFileName = args[0];
		String inputWord = args[1];

		/**
		 * Create an instance of your corrector here
		 */
		SpellCorrector corrector = new SpellCorrector();

		corrector.useDictionary(dictionaryFileName);

		String suggestion = corrector.suggestSimilarWord(inputWord);
		if (suggestion == null) {
		    suggestion = "No similar word found";
		}
		else
			System.out.println("Suggestion is: " + suggestion);

	}

}

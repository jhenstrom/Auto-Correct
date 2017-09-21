package spell;

import java.io.IOException;

import java.util.Set;
import java.util.HashSet;

import java.io.File;
import java.util.Scanner;

import java.io.FileNotFoundException;

public class SpellCorrector implements ISpellCorrector {

	public Trie dict =  new Trie();
	public Trie dict2 =  new Trie();

	public Trie getTrie()
	{
		return dict;
	}

	public void useDictionary(String dictionaryFileName) throws IOException
  {
		File file = new File(dictionaryFileName);
		Scanner in = null;
		try
		{
			in = new Scanner(file);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		while(in.hasNextLine())
		{
			dict.add(in.nextLine());
		}
		return;
  }

	public String suggestSimilarWord(String inputWord)
  {
		inputWord = inputWord.toLowerCase();
		Set<String> neighbors = new HashSet<String>();
		Set<String> neighbors2 = new HashSet<String>();
		ITrie.INode suggest;
		suggest = dict.find(inputWord);
		String best = inputWord;
		if (suggest != null) return best;

		dict.deletion(inputWord, neighbors);
		dict.insertion(inputWord, neighbors);
		dict.replacement(inputWord, neighbors);
		dict.switching(inputWord, neighbors);

		for (String s : neighbors)
		{
			if(dict.find(s) != null)
			{
				if (best == inputWord || suggest.getValue() < dict.find(s).getValue())
				{
					suggest = dict.find(s);
					best = s;
				}
				else if (suggest.getValue() == dict.find(s).getValue() && s.compareTo(best) < 0)
				{
					suggest = dict.find(s);
					best = s;
				}
			}
		}
		if (suggest != null) return best;
		for(String s : neighbors)
		{
				dict.deletion(s, neighbors2);
				dict.insertion(s, neighbors2);
				dict.replacement(s, neighbors2);
				dict.switching(s, neighbors2);
		}

		for (String s : neighbors2)
		{
			if(dict.find(s) != null)
			{
				if (best == inputWord || suggest.getValue() < dict.find(s).getValue())
				{
					suggest = dict.find(s);
					best = s;
				}
				else if (suggest.getValue() == dict.find(s).getValue() && s.compareTo(best) < 0)
				{
					suggest = dict.find(s);
					best = s;
				}
			}
		}

		if (suggest != null) return best;
		else
		{
			System.out.println("null");
			return null;
		}
	}
}

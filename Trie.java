//package spell;

import java.util.Set;
import java.util.HashSet;

public class Trie implements ITrie {

	public Node root = new Node();
	int nodeNum;
	int wordNum;

	public void add(String word)
  {
		word = word.toLowerCase();
		Node t = root;
		rAdd(word, t);
		return;
  }

	private void rAdd(String word, Node t)
	{
		if (word.length() == 0)
		{
			if (t.count == 0) wordNum++;
			t.count++;
			//System.out.println(t.count);
			return;
		}
		else
		{
			int nextChar = word.charAt(0) - 'a';
			if (t == null) return;
			if (t.n[nextChar] == null)
			{
				t.n[nextChar] = new Node();
			}
			t = t.n[nextChar];
			word = word.substring(1);
			rAdd(word, t);
			return;
		}
	}

	public INode find(String word)
  {
		word = word.toLowerCase();
		Node t = root;
		return rFind(word, t);

  }

	public Node rFind(String word, Node t)
	{
		if (word.length() == 0)
		{
			if (t.count > 0) return t;
			else return null;
		}
		else
		{
			int nextChar = word.charAt(0) - 'a';
			if (t.n[nextChar] != null)
			{
				t = t.n[nextChar];
				word = word.substring(1);
				return rFind(word, t);
			}
			else
			{
				return null;
			}
		}
	}

	public void deletion(String s, Set<String> set)
	{
		for (int i = 0; i < s.length(); i++)
		{
			StringBuilder b = new StringBuilder(s);
			set.add(b.deleteCharAt(i).toString());
		}
		return;
	}

	public void insertion(String s, Set<String> set)
	{
		for (int i = 0; i < s.length()+1; i++)
		{
			for (char c = 'a'; c <= 'z'; c++)
			{
				StringBuilder b = new StringBuilder(s);
				b.insert(i, Character.toString(c));
				String temp = b.toString();
				set.add(temp);
				//System.out.println(temp);
			}
		}
		return;
	}

	public void replacement(String s, Set<String> set)
	{
		for (int i = 0; i < s.length() + 1; i++)
		{
			for (char c = 'a'; c <= 'z'; c++)
			{
				StringBuilder b = new StringBuilder(s);
				b.replace(i, i+1, Character.toString(c));
				String temp = b.toString();
				set.add(temp);
				//System.out.println(temp);
			}
		}
		return;
	}

	public void switching(String s, Set<String> set)
	{
		for (int i = 0; i < s.length() - 1; i++)
		{
			StringBuilder b = new StringBuilder(s);
			char mover = b.charAt(i);
			char mover2 = b.charAt(i + 1);
			b.setCharAt(i, mover2);
			b.setCharAt(i+1, mover);
			//System.out.println(b.toString());
			set.add(b.toString());
		}
		return;
	}

	public int getWordCount()
  {
		return wordNum;
  }

	public int getNodeCount()
  {
		return nodeNum;
  }

	@Override
	public String toString()
  {
		Node temp = root;
		StringBuilder curr = new StringBuilder();
		StringBuilder total = new StringBuilder();
		rToString(temp, curr, total);
		return total.toString();
  }

	public void rToString(Node nodie, StringBuilder c, StringBuilder t)
	{
		if (nodie.count > 0)
		{
			t.append(c + "\n");
		}
		for (int i = 0; i < 26; i++)
		{
			if (nodie.n[i] != null)
			{
				c.append((char)(i + 'a'));
				rToString(nodie.n[i], c, t);
				c.deleteCharAt(c.length()-1);
			}
		}
		return;
	}

	@Override
	public int hashCode()
  {
		int hash = (wordNum * nodeNum)*31;

		return hash;
  }

	@Override
	public boolean equals(Object o)
  {
		if (o == null) return false;
		else if (o == this) return true;
		else if (this.getClass() != o.getClass()) return false;
		else
		{
			Trie t = (Trie)o;
			Node temp = root;
			Node temp2 = t.root;
			return rEquals(temp, temp2);
		}
  }

	public boolean rEquals(Node nodie, Node nodie2)
	{
		if (nodie == null && nodie2 == null)
		{
			//System.out.println("true by null nodes");
			return true;
		}
		if (nodie == null || nodie2 == null)
		{
			//System.out.println("false by one null node");
			return false;
		}
		if (nodie.count != nodie2.count)
		{
			//System.out.println("false by diff count");
			return false;
		}
		if (nodie.n != null && nodie.n != null)
		{
			for (int i = 0; i < 26; i++)
			{
				if (!rEquals(nodie.n[i], nodie2.n[i]))
				{
					//System.out.println("false  by false children");
					return false;
				}
			}
			return true;
		}
		else if (nodie.n == null && nodie2.n != null)
		{
			//System.out.println("false by different levels");
			return false;
		}
		else if (nodie2.n == null && nodie.n != null)
		{
			//System.out.println("false by different levels 2");
			return false;
		}
		else
		{
			//System.out.println("true by no problems");
			return true;
		}
	}

	public class Node implements ITrie.INode {

    public int count;
		public Node[] n = new Node[26];

		Node()
		{
			nodeNum++;
		}

		public int getValue()
    {
			return count;
    }
	}
}

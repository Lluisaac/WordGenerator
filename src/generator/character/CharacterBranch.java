package generator.character;

import generator.Tree;
import generator.Word;
import generator.map.Branch;

public class CharacterBranch extends Branch<Character>
{
	public CharacterBranch(Character[] tag, Tree<Character> tree)
	{
		super(tag, tree);
	}

	public CharacterBranch(Word<Character> tag, Tree<Character> tree)
	{
		super(tag, tree);
	}

	public Character parse(String str)
	{
		return str.charAt(0);
	}
}

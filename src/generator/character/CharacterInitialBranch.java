package generator.character;

import generator.Tree;
import generator.map.InitialBranch;

public class CharacterInitialBranch extends InitialBranch<Character>
{
	public CharacterInitialBranch(Tree<Character> tree)
	{
		super(tree);
	}

	@Override
	public Character parse(String str)
	{
		return str.charAt(0);
	}
}
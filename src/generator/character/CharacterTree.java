package generator.character;

import java.util.Random;

import generator.Tree;
import generator.Word;
import generator.map.Branch;
import generator.map.InitialBranch;

public class CharacterTree extends Tree<Character>
{

	public CharacterTree(Character[] alphabet, Character start, Character end)
	{
		super(alphabet, start, end);
	}

	public CharacterTree(Random rand, Character[] characters, char c, char d)
	{
		super(rand, characters, c, d);
	}

	@Override
	protected Branch<Character> getNewBranch(Word<Character> tag, Tree<Character> tree)
	{
		return new CharacterBranch(tag, tree);
	}

	@Override
	protected Word<Character> parse(String str)
	{
		return new Word<Character>(str.chars().mapToObj(c -> (char) c).toArray(Character[]::new));
	}

	@Override
	protected InitialBranch<Character> getNewInitialBranch(Tree<Character> tree)
	{
		return new CharacterInitialBranch(tree);
	}
	
}

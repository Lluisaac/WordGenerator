package generator.map;

import generator.Tree;
import generator.Word;

public abstract class InitialBranch<T> extends Branch<T>
{
	private static <T> Word<T> getnewWord(Tree<T> tree)
	{
		T start = tree.getStart();
		Word<T> word = new Word<T>(start);
		return word;
	}

	public InitialBranch(Tree<T> tree)
	{
		super(InitialBranch.getnewWord(tree), tree);
	}

	@Override
	protected Branch<T> getNewBranchWith(T symbol)
	{
		Word<T> newTag = this.tag.add(symbol);
		int index = this.tree.getTreeIndex(newTag);

		return this.tree.getBranchAt(index);
	}
}
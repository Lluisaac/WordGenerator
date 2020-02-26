package generator;

public class InitialBranch<T> extends Branch<T>
{
	public InitialBranch(Tree<T> tree)
	{
		super(getnewWord(tree), tree);
	}

	private static <T> Word<T> getnewWord(Tree<T> tree)
	{
		T start = tree.getStart();
		Word<T> word = new Word<T>(start);
		return word;
	}

	@Override
	protected Branch<T> getNewBranchWith(T symbol)
	{
		Word<T> newTag = this.tag.add(symbol);
		int index = this.tree.getTreeIndex(newTag);

		return this.tree.getBranchAt(index);
	}
}
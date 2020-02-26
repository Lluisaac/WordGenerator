package generator;

import java.util.Map;

public class Branch<T> extends WeightedProbabilisticMap<T>
{
	protected Tree<T> tree;

	protected Word<T> tag;

	public Branch(T[] tag, Tree<T> tree)
	{
		this(new Word<T>(tag), tree);
	}

	public Branch(Word<T> tag, Tree<T> tree)
	{
		super(tree.getRand());
		this.tree = tree;
		this.tag = tag;
	}

	public Word<T> getTag()
	{
		return this.tag;
	}

	public T getLastLetter()
	{
		return this.tag.getLast();
	}

	public Branch<T> findNextRandomBranch()
	{
		return getNewBranchWith(getRandomElement());
	}

	public boolean isValid()
	{
		return this.getWeight() > 0 || this.isLastBranch();
	}

	public boolean isFirstBranch()
	{
		return this.tag.getFirst().equals(this.tree.getStart());
	}

	public boolean isLastBranch()
	{
		return this.tag.getLast().equals(this.tree.getEnd());
	}

	protected Branch<T> getNewBranchWith(T symbol)
	{
		Word<T> newTag = this.tag.getAllButFirst().add(symbol);
		int index = this.tree.getTreeIndex(newTag);

		return this.tree.getBranchAt(index);
	}

	@Override
	public String toString()
	{
		String str = this.tag + ">";

		for (Map.Entry<T, Integer> entry : this.getLeafs().entrySet())
		{
			str += entry.getKey() + "-" + entry.getValue() + ";";
		}

		return str;
	}
}

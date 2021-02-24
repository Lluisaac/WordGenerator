package generator.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class WeightedProbabilisticMap<T>
{
	protected Map<T, Integer> leafs;

	protected int weight;

	private Random rand;

	public WeightedProbabilisticMap(Random rand)
	{
		this.leafs = new HashMap<T, Integer>();
		this.weight = 0;

		this.rand = rand;
	}

	public void addBranch(T symbol)
	{
		if (!this.getLeafs().containsKey(symbol))
		{
			this.getLeafs().put(symbol, 0);
		}

		this.getLeafs().replace(symbol, this.getLeafs().get(symbol) + 1);

		this.weight++;
	}

	public T getRandomElement()
	{
		return getElementFromWeight(this.getRand().nextInt(this.weight));
	}

	private T getElementFromWeight(int value)
	{
		int sum = 0;

		for (Map.Entry<T, Integer> entry : this.getLeafs().entrySet())
		{
			sum += entry.getValue();
			if (sum >= value)
			{
				return entry.getKey();
			}
		}

		return null;
	}

	@Override
	public String toString()
	{
		String str = "";

		for (Map.Entry<T, Integer> entry : leafs.entrySet())
		{
			str += entry.getKey() + "-" + entry.getValue() + ";";
		}

		return str;
	}

	public Random getRand()
	{
		return this.rand;
	}

	public Map<T, Integer> getLeafs()
	{
		return this.leafs;
	}

	public int getWeight()
	{
		return this.weight;
	}

	public abstract void buildFromString(String str);
}

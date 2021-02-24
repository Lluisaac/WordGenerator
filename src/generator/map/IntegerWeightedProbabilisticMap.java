package generator.map;

import java.util.Random;

public class IntegerWeightedProbabilisticMap extends WeightedProbabilisticMap<Integer>
{

	public IntegerWeightedProbabilisticMap(Random rand)
	{
		super(rand);
	}

	@Override
	public void buildFromString(String str)
	{
		String[] entries = str.split(";");

		int sum = 0;

		for (String entry : entries)
		{
			Integer key = Integer.parseInt(entry.split("-")[0]);
			Integer value = Integer.parseInt(entry.split("-")[1]);

			this.leafs.put(key, value);

			sum += value;
		}
		
		this.weight = sum;
	}

}

package generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class Tree <T>
{
	private Random rand;
	
	private Branch<T>[] branches;
	
	private WeightedProbabilisticMap<Integer> wordSizeMap;
	
	private T[] alphabet;
	private T start;
	private T end;
	
	private InitialBranch<T> root;

	public Tree(T[] alphabet, T start, T end)
	{
		this(new Random(), alphabet, start, end);
	}
	
	@SuppressWarnings("unchecked")
	public Tree(Random rand, T[] alphabet, T start, T end) {

		this.rand = rand;

		this.branches = (Branch<T>[]) new Branch[(int) Math.pow(alphabet.length + 2, 2)];
		
		this.wordSizeMap = new WeightedProbabilisticMap<Integer>(this.rand);
		
		this.alphabet = alphabet;
		this.start = start;
		this.end = end;
		
		this.root = new InitialBranch<T>(this);

		buildBranches();
	}

	public Word<T> makeWord()
	{
		return makeWord(this.wordSizeMap.getRandomElement());
	}

	public Word<T> makeWord(int maxSize)
	{
		Word<T> word;
		
		while((word = makeRandomWord()).length() > maxSize);
		
		return word;
	}

	public Word<T> makeRandomWord()
	{
		Word<T> word = new Word<T>();
		Branch<T> next = this.root;
		while (!(next = next.findNextRandomBranch()).isLastBranch())
		{
			word = word.add(next.getLastLetter());
		}

		return word;
	}

	public void insertWord(Word<T> word)
	{
		insertWordLength(word);
		
		T first = this.getStart();

		T second = word.get(0);
		this.root.addBranch(second);

		word = word.getAllButFirst();

		while (word.length() > 0)
		{
			Word<T> tag = new Word<T>(first, second);
			T newChar = word.get(0);
			word = word.getAllButFirst();

			this.branches[this.getTreeIndex(tag)].addBranch(newChar);

			first = second;
			second = newChar;
		}

		Word<T> tag = new Word<T>(first, second);
		this.branches[this.getTreeIndex(tag)].addBranch(this.end);
	}

	private void insertWordLength(Word<T> word)
	{
		int length = word.length();

		this.wordSizeMap.addBranch(length);
	}

	private void buildBranches()
	{
		Word<T> tag = new Word<T>(this.start, this.start);

		for (int i = 0; i < this.branches.length; i++)
		{
			this.branches[i] = new Branch<T>(tag, this);
			tag = getNextTag(tag);
		}
	}

	public Word<T> getNextTag(Word<T> tag)
	{
		boolean looped = true;
		int index = tag.length() - 1;

		while (looped && index >= 0)
		{
			tag = tag.set(index, nextChar(tag.get(index)));

			if (tag.get(index).equals(this.start))
			{
				looped = true;
				index--;
			}
			else
			{
				looped = false;
			}
		}

		return tag;
	}

	public int getTreeIndex(Word<T> tag)
	{
		int sum = (int) (Math.pow(this.alphabet.length + 2, 1) * getValueOf(tag.get(0)));
		sum += (int) (Math.pow(this.alphabet.length + 2, 0) * getValueOf(tag.get(1)));

		return sum;
	}

	public int getValueOf(T symbol)
	{
		if (symbol.equals(this.start))
		{
			return 0;
		}
		else if (symbol.equals(this.end))
		{
			return this.alphabet.length + 1;
		}
		else
		{
			return indexOf(symbol) + 1;
		}
	}

	private int indexOf(T symbol)
	{
		for (int i = 0; i < this.alphabet.length; i++) {
			if (this.alphabet[i].equals(symbol)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public T nextChar(T symbol)
	{
		int val = getValueOf(symbol);

		if (val == 0)
		{
			return this.alphabet[0];
		}
		else if (val == this.alphabet.length)
		{
			return this.end;
		}
		else if (val == this.alphabet.length + 1)
		{
			return this.start;
		}
		else
		{
			int index = indexOf(symbol);
			return this.alphabet[index + 1];
		}
	}

	public Branch<T> getBranchAt(int index)
	{
		return this.branches[index];
	}

	public Random getRand()
	{
		return this.rand;
	}

	public T getStart()
	{
		return this.start;
	}

	public T getEnd()
	{
		return this.end;
	}
	
	@Override
	public String toString() 
	{
		String str = "";
		
		str += "wordSize:" + this.wordSizeMap + "\n";
		str += "branches:" + branchesToString() + "\n";
		
		return str;
	}

	private String branchesToString()
	{
		String str = "";
		
		for (int i = 0; i < this.branches.length; i++) {
			if (branches[i].getWeight() > 0) {
				str += branches[i] + "|";
			}
		}
		
		return str;
	}

	public void save(BufferedWriter file) throws IOException
	{
		file.write(this.toString());
	}

	public void buildFromFile(BufferedReader file) throws IOException
	{
		String firstLine = file.readLine();
		String secondLine = file.readLine();
		
		String wordSizeMapString = firstLine.split(":")[1];
		this.wordSizeMap.buildFromString(wordSizeMapString);
		
		//TODO Faire les branches
	}
}

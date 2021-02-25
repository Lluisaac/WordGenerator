package generator;

import java.util.ArrayList;
import java.util.List;

public class Word<T>
{

	private List<T> symbols;

	public Word()
	{
		this.symbols = new ArrayList<T>();
	}

	public Word(T symbol)
	{
		this();
		this.symbols.add(symbol);
	}

	@SafeVarargs
	public Word(T... tag)
	{
		this();

		for(T symbol : tag)
		{
			this.symbols.add(symbol);
		}
	}

	public Word(Word<T> word)
	{
		this();

		for(T symbol : word.symbols)
		{
			this.symbols.add(symbol);
		}
	}

	public Word<T> add(T symbol)
	{
		Word<T> newWord = new Word<T>(this);

		newWord.addUnsafely(symbol);

		return newWord;
	}

	private void addUnsafely(T symbol)
	{
		this.symbols.add(symbol);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj.getClass().equals(Word.class))
		{
			return ((Word<?>) obj).symbols.equals(this.symbols);
		}
		else
		{
			return false;
		}
	}

	public T get(int i)
	{
		return this.symbols.get(i);
	}

	public Word<T> getAllButFirst()
	{
		Word<T> newWord = new Word<T>();

		for(int i = 1; i < this.symbols.size(); i++)
		{
			newWord.addUnsafely(this.symbols.get(i));
		}

		return newWord;
	}

	public T getFirst()
	{
		return this.symbols.get(0);
	}

	public T getLast()
	{
		return this.symbols.get(this.symbols.size() - 1);
	}

	public int length()
	{
		return this.symbols.size();
	}

	public Word<T> set(int index, T symbol)
	{
		Word<T> newWord = new Word<T>(this);

		newWord.symbols.set(index, symbol);

		return newWord;
	}

	@Override
	public String toString()
	{
		String str = "";

		for(T symbol : this.symbols)
		{
			str += symbol.toString();
		}

		return str;
	}
}

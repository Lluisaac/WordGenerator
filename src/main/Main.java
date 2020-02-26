package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import generator.Tree;
import generator.Word;

public class Main
{
	public static void main(String[] args)
	{
		Tree<Character> tree = buildTree();
		tree = importTree();
		
		for (int i = 0; i < 50; i++) {
			System.out.println(tree.makeWord());
		}
	}

	private static Tree<Character> buildTree()
	{
		Tree<Character> tree = new Tree<Character>(new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}, '$', '#');
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("words_alpha.txt"));
			String str;
			while ((str = reader.readLine()) != null)
			{
				Character[] array = str.chars().mapToObj(c -> (char)c).toArray(Character[]::new); 
				tree.insertWord(new Word<Character>(array));
			}
			
			reader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			BufferedWriter file = new BufferedWriter(new FileWriter("save"));
			tree.save(file);
			file.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return tree;
	}

	private static Tree<Character> importTree()
	{
		Tree<Character> tree = new Tree<Character>(new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}, '$', '#');

		try
		{
			BufferedReader file = new BufferedReader(new FileReader("save"));
			tree.buildFromFile(file);
			file.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return tree;
	}
}

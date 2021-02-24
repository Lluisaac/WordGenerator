package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import generator.Tree;
import generator.Word;
import generator.character.CharacterTree;

public class Main
{
	
	public static void main(String[] args)
	{
		Tree<Character> tree;
		tree = buildTree();
		//tree = importTree(new Random());
		
		for (int i = 0; i < 500; i++) {
			System.out.println(tree.makeWord());
		}
	}

	private static Tree<Character> buildTree()
	{
		Tree<Character> tree = new CharacterTree(new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}, '$', '#');
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
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

	private static Tree<Character> importTree(Random rand)
	{
		Tree<Character> tree = new CharacterTree(rand, new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}, '$', '#');

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

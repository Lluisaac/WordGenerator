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

	private static Tree<Character> buildTree(String input)
	{
		Tree<Character> tree = new CharacterTree(new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' }, '$', '#');

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(input));
			String str;
			while((str = reader.readLine()) != null)
			{
				Character[] array = str.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
				tree.insertWord(new Word<Character>(array));
			}

			reader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			BufferedWriter file = new BufferedWriter(new FileWriter("save.wg"));
			tree.save(file);
			file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return tree;
	}

	private static Tree<Character> importTree(Random rand)
	{
		Tree<Character> tree = new CharacterTree(rand, new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' }, '$', '#');

		try
		{
			BufferedReader file = new BufferedReader(new FileReader("save.wg"));
			tree.buildFromFile(file);
			file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return tree;
	}

	public static void main(String[] args)
	{
		if((args.length < 2) || "-h".equals(args[0]))
		{
			Main.printHelp();
		}
		else
		{
			if("-b".equals(args[1]) || "-i".equals(args[1]))
			{
				Tree<Character> tree;

				int argNumber = 2;

				if("-b".equals(args[1]))
				{
					tree = Main.buildTree(args[2]);
					argNumber++;
				}
				else
				{
					tree = Main.importTree(new Random());
				}

				if((args.length - 1) >= argNumber)
				{
					Main.makeWords(Integer.parseInt(args[0]), args[argNumber], tree);
				}
				else
				{
					Main.makeWords(Integer.parseInt(args[0]), "words.txt", tree);
				}
			}
			else
			{
				Main.printHelp();
			}
		}
	}

	private static void makeWords(int amount, String output, Tree<Character> tree)
	{
		try
		{
			BufferedWriter file = new BufferedWriter(new FileWriter(output));

			for(int i = 0; i < amount; i++)
			{
				file.write(tree.makeWord().toString() + "\n");
			}

			file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void printHelp()
	{
		System.out.println("Usage:");
		System.out.println("java -jar WordGenerator.jar [-h] <n> <-b or -i> [input file] [output file]");
		System.out.println("-h: will print this text for help");
		System.out.println("n:  must be a number and will be the number of words generated");
		System.out.println("-b: use this argument for the first time and every time you add words to be fed to the generator");
		System.out.println("-i: if you ran the program with -b at least once, you can use -i and it will use a compact version of the words given");
		System.out.println("input file: when using -b only, put the name of the file where all the words are (all words must be written with only the 26 characters of the latin alphabet and with only one word per line)");
		System.out.println("output file: default is \"words.txt\". The name of the file for the generated words");
	}
}

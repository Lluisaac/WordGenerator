# Word Generator

This project is a generator that creates words that are statistically alike the ones given in the program.

To put the words that you want, change the contents of words.txt. Currently it is a list of a few thousand English words. Only put words from the 26 letters of the Latin alphabet, without accentuation, punctuation or spacing (you can of course change all of that by changing the code) with one word per line.

To run, execute the Main.java file once compiled.

When the program has run, it will make a file "save" which is a compact way to save the relevant content of all the words present in the words.txt file. To speed up the process of launching the program, you can invert the comment between the following lines:

```Java
tree = buildTree();
//tree = importTree(new Random());
```

## How it works

What it basically does, is map the frequency at which a letter appears after two letters and then building a word letter by letter using the two last letters. In English, following the two-letter combination "in" there is often the letter "g", so if the generator put those two letters, it is very likely that it will put a "g" next. It is also very likely for a word to finish after the two-letter combination "ng" since a lot of words end with "ing", so after "ng" there is a high probability that the word will end (the end and the start of a word is considered to be a character).

## Examples

Below are a few examples of generated words, some words are better than others, and some even already exist. The interesting part is that they all seem easy to pronunciate in English.

```bash
nong

deriansitze

ande

ni

pantardingsm

concea

nechly

foechified

over

freprerix

mineousm

unt

overever

mismuts

umron

calted

eumasinish
```

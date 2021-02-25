# Word Generator

This project is a generator that creates words that are statistically alike the ones given in the program.

A list of a few thousand English words is available for testing. If you want to put your own words, only put words written with the 26 letters of the Latin alphabet, without accentuation, punctuation or spacing (you can of course change all of that by changing the code) with one word per line.

## How to run it

To run, use the following command:
```bash
java -jar WordGen.jar [-h] <n> <-b or -i> [input file] [output file]
```

*-h*: This argument will print a help message and ignore all other arguments
*n*: Mandatory argument to generate words, put a number that will be the number of words generated
*-b*: Standard generation method, will use the file from the argument *input file* to get words to use
*-i*: Only use after one use of *-b*, will use a more compact and quick mapping of the words given.
*input file*: Only use with *-b*, the name of the file in which all the words have been put.
*output file*: Optional, default is "words.txt", the file in which all the generated words will be written in.

When the program has run, it will make a file "save.wg" which is a compact way to save the relevant content of all the words that have been given to the program. To speed up the process of launching the program or to compress the words list to a strictly relevant weight, you can launch using the *-i* argument.

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

# Development Diary

A commit-by-commit narration of my thoughts whilst building this app.

## Plan

### Requirements

- Take a file path as an input.
- Count frequency of each word in file.
- Sort output by frequency (most frequent first).

### Assumptions

- Ignore case and punctuation
    - i.e. "foo" "Foo" and "foo." should all be counted as the same word.
- Do not assume application will be run with sensible arguments. Handle these cases:
    - Incorrect file path.
    - Missing argument.
    - Too many arguments.
    - Not a text file.
- Do not assume anything about file size.
- Do not assume anything about language (i.e. handle non-english characters).

### Stretch goals

- Help text.
- Output to file.
- Sort alphabetically option.
- Sort alphabetically if frequencies are equal.
- Format output into columns.

## Diary

### Initial Commit

Using Java 17 as it's the latest LTS version.

Begin by making a gradle project using VS Code. Using gradle might be overkill for a small project
like this, but this seemed like a good opportunity to try it.

### Store word counts in hashmap

Next, make a minimum viable product without worrying too much efficiency or features (first make it
work, then make it good).

Start by making a class to keep track of the word counts. The class is fed the text one line at a
time and stores the results in a HashMap (there might be choices for this in terms of speed).

### Order word counts by frequency

It makes sense to split up the tasks of counting the words from formatting them, so make a new
class. This class is responsible for any formatting or sorting. Initially I planned to have this
class also print the results, but that would make testing it a pain. Instead, this returns a simple
string array that can be printed elsewhere.

As I might want to add options for how the output is sorted and formatted, split these two tasks
into separate functions. This also means the test asserting the data is ordered should make as few
assumptions about the format as possible (hence only checking the first part of the string).

### Use command line argument to load file and count words

Time to actually use what's been written so far. Use a Scanner to read the file, as this does not
keep the full file in memory (which will be good for larger files). Put some basic exception
handling in for the most obvious cases (still need to handle binary files somehow).

### Handle binary files

It's surpringly difficult to tell if a file is binary or not. I checked online and it seems that
the best solution is to check the file's mime type.

### Refactor and test file reading

The main method was getting a bit too complicated. Refactor any file reading to a new class so it
can be unit tested.

### Ignore case and punctuation

Not much to say here, just a matter of finding the right bit of regex. It could be handy to make
these optional. I can imagine a case sensitive word count being useful. I'm not sure why you would
ever want to leave the punctuation in, but if it's not much work then I should make this optional
too.

### Read output file from command line arguments

This new Options class should be flexible enough to handle multiple kinds of options, without
needing to worry about the order they're in. I've been trying to avoid having any class other than
App print to the terminal, so store any errors that occur in a list.

### Read sort alphabetically from command line arguments

Alphabetical flag can be added with others in any order. I couldn't decide if this should be
"alphabetical" or "alphabetically" so I've included both.

## Read order from command line arguments

Add an option to sort ascending/descending. It seems natural that the default order should be
ascending when alphabetical, but the spec requires descending as default when sorting by
frequency. To get round this I've added a boolean to mark if the order has been manually set,
otherwise the default changes when using the alphabetical flag.

## Add usage text

Add some text to explain the various options. This also displays whenever there's a problem parsing
an option. There might be a clever way to format the text into neat columns, but given it's only a
few lines I've just hardcoded in the spaces.

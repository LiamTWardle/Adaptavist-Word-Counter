# Adaptavist Word Counter

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

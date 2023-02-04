# Adaptavist Word Counter

## Requirements

- Take a file path as an input.
- Count frequency of each word in file.
- Sort output by frequency (most frequent first).

## Assumptions

- Ignore case and punctuation
    - i.e. "foo" "Foo" and "foo." should all be counted as the same word.
- Do not assume application will be run with sensible arguments. Handle these cases:
    - Incorrect file path.
    - Missing argument.
    - Too many arguments.
    - Not a text file.

## Stretch goals

- Help text.
- Output to file.
- Sort alphabetically option.
- Format output into columns.

## Diary

Using Java 17 as it's the latest LTS version.

Begin by making a gradle project using VS Code. Using gradle might be overkill for a small project
like this, but this seemed like a good opportunity to try it.

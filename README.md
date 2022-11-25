A multi-threaded sudoku solver capable of solving up to 10 boards at once.
If there is no boards.txt file, the program runs on a sample board.

To import your own boards, create a boards.txt file, and designate your boards.

Board formatting:
To create a board, just write what values in the squares within two brackets for each line.
  - A 0 denotes an empty square
  - If a line has less than 9 numbers, the rest of the values in that line fill in as 0's
  - If a board has less than 9 lines, then the rest of the lines fill in as 0's
  - If we have 9 lines, and add a 10th line, a new board will be created for that line
  - Empty lines are skipped
  - Each line doesn't have to end with a bracket, only start with one; it's good convention to have them though.
  - Board line numbers are index at 0 (error on line 1 means there is an error on the second line)

Example boards.txt: 
_____________________________________________________

boards.txt    |  ---->  |       interpreted board
              |         |   _________________________
[123456789]   |         |   | 1 2 3 | 4 5 6 | 7 8 9 |
[123456789]   |         |   | 1 2 3 | 4 5 6 | 7 8 9 |
[123456789]   |  ---->  |   | 1 2 3 | 4 5 6 | 7 8 9 |
[987654321]   |         |   _________________________
              |         |   | 9 8 7 | 6 5 4 | 3 2 1 |
              |         |   | 1 2 3 | 4 0 0 | 0 0 0 |
[1234]        |  ---->  |   | 1 2 3 | 4 5 6 | 0 0 0 |
[123456]      |         |   _________________________
              |         |   | 0 0 0 | 0 0 0 | 0 0 0 |
              |         |   | 0 0 0 | 0 0 0 | 0 0 0 |
              |  ---->  |   | 0 0 0 | 0 0 0 | 0 0 0 |
              |         |   _________________________


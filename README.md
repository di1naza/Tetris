# Tetris
Game Overview: When the Tetris game starts, only an empty board with borders drawn around
its edges should be displayed as in Figure 1. A Tetris block, chosen randomly from the seven
possible Tetris blocks, shown below, should appear at the top of the board. This block should
fall by moving down the board, one cell at a time. A block cannot fall into a cell that already
occupied by a previously fallen block. When a block can fall no further, it should stop moving;
a new random block should then appear at the top of the board and begin to fall. As blocks fall,
rows (or horizontal lines) of occupied cells spanning the board's width may form. When such a
line is formed, it disappears, and all the cells above it falls one line to fill the newly empty cells.
This process continues until a new block appears and has no room to fall because it is already
resting on a previously fallen block. The game is then over, and everything on the board should
stop completely.
I: four cells in a straight line;
J: a row of three cells with one added below the right side;
L: a row of three cells with one added below the left side;
O: four cells in a 2×2 cell;
S: two stacked horizontal cells with the top one offset to the right;
T: a row of three cells with one added below the center;
Z: two stacked horizontal cells with the top one offset to the left.
The game should be paused by input ‘p’. While a block is falling, the player may
rotate or move it by pressing specific keys on the keyboard. Pressing `a' should move the block
one cell to the left. Pressing `d' should move the block one cell to the
right. Pressing `w' should rotate the block clockwise by 90°. At regular time intervals, the block
should fall vertically one cell at a time. The player should be able to drop the block quickly by
pressing ‘s’. By dropping a block, the player forfeits his/her chance to manipulate the block any
further, and the block falls as far as it can. The player should be able to pause the game at any
time by pressing `p'. When the game is paused, the player should not be able to manipulate the
block in any way.

We will implement the Tetris in Java. You need to implement the Cell
class, Block class, Board class, InvalidInputException class and OutOfBoardException class.
The descriptions of these classes are listed in the following subsections. Codes should be
indented and commented. A method should be broken into several methods if it is longer than
30 lines. You can define as many helper methods as you like. You may reuse some codes
from your assignment 1.
Cell
Cell (3, 2) is in the following board has 3 as the row number and 2 as the col number.
When you move this cell left, the coordinate would become (row, col-1) that is (3, 1); when you
move this cell right, the coordinate would become (row, col+1) that is (3, 3); the coordinate
would become (row+1, col) that is (4, 2) when you move this cell down.
Block
A block is a geometric shape composed of four cells. There are seven distinct blocks. A block
can be rotated 90° clockwise. Several rotations get the shape back to the original orientation.
Each block has one (the O), or four (the L, J, T, I, S and Z) distinct rotational orientations. In
this assignment, we define those commonalities in an abstract class named Block. It has seven
subclasses, IBlock, JBlock, LBlock, OBlock, SBlock, TBlock, and ZBlock.
Board
On the Board, we have a grid of size 20×10, where 20 is the row number and 10 is the column
number as Figure 3. As a block moves in the wall, it would not make sense for it to be able to
move through previously fallen blocks or beyond the edges of the Board. In order to prevent
these illegal moves, when a block wants to move to a new location, it should check that a
previously fallen block does not already occupy the new location and that it is not beyond the
edges of the board. These checks should be made whenever a block wants to move left, move
right, move down, or rotate.
0 1 2 3 4 5 6 7 8 9
0
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
Figure 3
Exceptions
They are for error handing. In the present assignments, we will implement the following
exceptions:
 InvalidInputException
 OutOfBoardException
Programming Guidelines
You only need to implement class Cell, Abstract class Block, class Board, InvalidInputException
class and OutOfBoardException class by yourself. You don't need to do anything with class
Tetris, enum BlockColor and class BoardDisplay.
class Tetris
Contains the main method already implemented in Tetris.java. After completing all the
following content, you can run this class to play your own implemented game with an interface.
You don't need to do anything with this class.
enum BlockColor
BlockColor is a type and already implemented in BlockColor.java. It can define a variable of
seven different colors NO_COLOR, Z_COLOR, S_COLOR, I_COLOR, T_COLOR, O_COLOR,
0 1 2 3 4 5 6 7 8 9
0
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
L_COLOR, J_COLOR. You don't need to do anything with this class.
class BoardDisplay
This class displays the board in a GUI and already implemented in BoardDisplay.java. You
don't need to do anything with this class.
class Cell
Represents a cell of a block, implemented in Cell.java, you may reuse some codes from
your assignment 1.
.
Member fields
 private int row.
Define the row number of such cell.
 private int col.
Define the column number of such cell.
 private BlockColor color.
Represent the color of such cell.
Constructor
 public Cell (int row, int col, BlockColor color).
Constructs a new cell, which takes three integers to construct a Cell object.
Methods
 public int getRow ().
To get value of row.
 public int getCol ().
To get value of col.
 public BlockColor getColor();
To get value of color.
 public void setRow (int row).
To set the value of row.
 public void setCol (int col).
To set the value of col.
 public void setColor (BlockColor color).
To set the value of color.
 public void left ().
To move the current cell left by one position.
 public void right ().
To move the current cell right by one position.
 public void down ().
To move the current cell down by one position.
Abstract class Block
An abstract grouping of 4 cells, implemented in Block.java.
Member fields
 private Cell [] cells;
A Cell array of size 4 for one block.
 private char shape.
Indicate the shape for the current block (‘I’, ‘J’, ‘L’, ‘O’, ‘S’,’T’ or ’Z’).
 private int status.
Defines the orientation status for the current block, from 0-3.
Constructor
 public Block (Cell c1, Cell c2, Cell c3, Cell c4, char shape, int status).
You need to assign them to the relevant fields, respectively.
Methods
 public Cell[] getCells().
Return cell array of size 4.
 public char getShape ().
Return value of shape.
 public int getStatus ().
Return value of status.
 public BlockColor getColor ().
Return color of four cells. The color in one block is the same, return any of 4 cells.
 public void setStatus (int status).
Set the value of status, the status of ‘O’ is always 0.
 public void moveLeft () throws OutOfBoardException.
Move the current block left by one position, which is achieved by having four cells in
the Block all move left as Figure 2.
 public void moveRight () throws OutOfBoardException.
Move the current block right by one position.
 public void moveDown () throws OutOfBoardException.
Move the current block down by one position.
 public void rotate () throws OutOfBoardException.
Rotate the current block clockwise by 90°.'O' does not need to be rotated, other shapes
need to be calculated first to find the index of center in four cells, and then let the other
three cells transform their coordinates around the center, as assignment1.
 public static Block randomBlock ().
Use Math.random() to get 7 integers at random, correspond to 7 subclasses one by one
and return.
There are seven distinct blocks as follows:
I: four cells in a straight line.
J: a row of three cells with one added below the right side.
L: a row of three cells with one added below the left side.
O: four cells in a 2×2 square.
S: two stacked horizontal cells with the top one offset to the right.
T: a row of three cells with one added below the center.
Z: two stacked horizontal cells with the top one offset to the left.
You should implement 7 subclasses (IBlock, JBlock, LBlock, OBlock, SBlock, TBlock,
and ZBlock) extend Block class as follows in Block.java.
class IBlock extends Block.
Represents an ‘I’ block on the board. Status of seven shapes are 0. The four cells of 'J' are
[(0,3),(0,4),(0,5)(-1,3)], ‘L’ is [(0,3),(0,4),(0,5)(-1,5)], ‘O’ is [(0,4),(-1,4),(-1,5)(0,5)], ‘S’ is
[(0,3),(0,4),(-1,4)(-1,5)] ,‘T’ is [(0,3),(0,4),(0,5)(-1,4)] and ‘Z’ is [(-1,3),(0,4),(-1,4)(0,5)].
Negative coordinates are allowed, and the colors can be filled by referring to the following
example.
public IBlock(){
super(new Cell(0,4, BlockColor.I_COLOR),
new Cell(0,3, BlockColor.I_COLOR),
new Cell(0,5, BlockColor.I_COLOR),
new Cell(0,6, BlockColor.I_COLOR), ‘I’, 0));
}
Figure 4
class Board
The Board contains 20×10 array cells to store the block types occupy the grid.
Member fields
 BOARD_HEIGHT = 20 and BOARD_WIDTH =10 are static variables.
 private BlockColor [] [] cells = new BlockColor [BOARD_HEIGHT]
[BOARD_WIDTH].
Indicate the layout of such wall.
 private Block activeBlock.
A block which is currently active. To distinguish which block can be operated on.
 private int numFullLinesRemoved=0.
Record the number of Full lines achieved by the user.
Constructor
 public Board ().
It creates an array of cells of size 20*10 and initializes them with
“BlockColor.NO_COLOR”.
Methods
 public BlockColor [] [] getCells().
Return the array of cells of size 20*10.
 public Block activeBlock ().
Return the only active block in the Board.
 public BlockColor blockAt(int x, int y);
Return the color of the cell in the (x, y).
 public void clear ().
Reset the values of the array by setting all colors to “BlockColor.NO_COLOR”, and the
active block to be null.
 public void blockLanded ().
The active block landed on the Board by updating the array. Reset the active block.
 public boolean canMove ().
Return true if no more new blocks can be generated from (0,3)-(0,6).
 public boolean rotate () throws OutOfBoardException.
Try to rotate the active block. If no rotation is possible, return false. Otherwise, return
true.
 public boolean oneLineDown () throws OutOfBoardException.
Try to move the active block down. If no movement is possible, return false.
Otherwise, return true. DropDown method in BoardDisplay calls this method to achieve a
fast drop.
 public boolean moveLeft () throws OutOfBoardException.
Try to move the active block to the left. If no movement is possible, return false.
Otherwise, return true.
 public boolean moveRight () throws OutOfBoardException.
Try to move the active block to the right. If no movement is possible, return false.
Otherwise, return true.
 public boolean newBlock ().
The canMove() method is called first to check if a new Block can be generated. Then
a random block is then generated, and the reference is assigned to the activeBlock.
 public int removeFullLines ().
Remove these full rows. Updates numFullLinesRemoved and returns the number of
rows removed at this method call.
 public int numFullLinesRemoved ().
Return the total number of rows removed.
class InvalidInputException extends Exception.
Just write the constructor and no other implementation is needed within the constructor.
Indicate an invalid input form the keyboard.
class OutOfBoardException extends Exception.
Just write the constructor and no other implementation is needed within the constructor. Prevent
the operation input of the board will go beyond the edge of the wall.

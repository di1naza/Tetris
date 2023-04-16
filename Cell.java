package assignment2Supplied;
public class Cell {


    private int row;
    private int col;
    private BlockColor color;
    
    //a Cell constructor that takes in row, coloumn and shade od the cell as a parameter
    public Cell(int r, int c, BlockColor shade){
        row = r;
        col = c;
        color = shade;
    }
    /*
     * setters and getters
     */
    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public BlockColor getColor(){
        return this.color;
    }

    public void setRow(int r){
        row = r;
    }

    public void setCol(int c){
        col = c;
    }

    public void setColor(BlockColor cellColor){
        color = cellColor;
    }
    /*
     * methods that update the coordinates of the cell
     * 'movement' functions
     */
    public void left(){
        col--;
    }

    public void right(){
        col++;
    }
    
    public void down(){
        row++;
    }

	
	
}

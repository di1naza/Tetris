package assignment2Supplied;

public abstract class Block{

    private Cell[] cells = new Cell[4];
    private char shape;
    private int status;
    
    //Block constructor which takes 4 cells, shape and status as a parameter
    public Block(Cell c1, Cell c2, Cell c3, Cell c4, char bShape, int bStatus){
        cells[0] = c1;
        cells[1] = c2;
        cells[2] = c3;
        cells[3] = c4;
        shape = bShape;
        if (shape == 'O') status = 0;
        status = bStatus % 4;
    }

    public Block(Cell[] cells, char shape, int status){   
        this.cells = cells;
        this.shape = shape;

        if (shape == 'O'){
            this.status = 0;
        }
        else{
            this.status = status%4;
        }
    }
    /*
     * setters and getters
     */
    public Cell[] getCells(){
        return cells;
    }
    public char getShape(){
        return this.shape;
    }

    public int getStatus(){
        return this.status;
    }

    public void setShape(char s){
        shape = s;
    }

    public BlockColor getColor(){
        return cells[0].getColor();
    }
    
    public void setStatus(int s){
        status = s%4;
    }

    /*
     * functions that 'move' blocks by updating their coordinates
     */
    public void moveleft() throws OutOfBoardException{
        for (int i = 0; i < 4; i++) cells[i].left();
    }

    public void moveright() throws OutOfBoardException{
        for (int i = 0; i < 4; i++) cells[i].right();
    }

    public void movedown() throws OutOfBoardException{
        for (int i = 0; i < 4; i++) cells[i].down();
    }

    public Cell findCenter( Cell[] bCells){ //function that finds the center (pivot) cell and the returns it
        int rowSum = 0;
        int colSum = 0;

        for (int i = 0; i < 4; i ++){
            rowSum += bCells[i].getRow();
            colSum += bCells[i].getCol();
        }

        if(shape =='L'){
            if (status == 0 || status == 3) rowSum++;
            if (status == 2 || status == 3) colSum++;
        }
        else if(shape =='J'){
            if (status == 0 || status == 1) rowSum++;
            if (status == 0 || status == 3) colSum++;
        }

        else if (shape == 'T'){
            if(colSum%4 !=0) colSum++;
            if (rowSum%4 != 0) rowSum++;
        }
        else if (shape == 'S' || shape == 'Z' ){
            if (status == 0 || status == 2) rowSum += 2;
            if (status == 1 || status == 3) colSum += 2;
        }

        Cell pivot = new Cell(rowSum/4, colSum/4, BlockColor.I_COLOR); //the formula for finding the pivot cell
        return pivot;
    }
   

    public void rotate_withCenter() throws OutOfBoardException{ //rotates block shapes L,J,S,T,Z that have distinct center cell
        int temp;
       // Cell p = findCenter(cells); //finds a center cell of the block
       Cell p = cells[1];
        for(int i = 0; i < 4; i ++){ //in this loop all the cells rotate in respect to the center cell(pivot)
            if (cells[i] != p){
                cells[i].setRow(cells[i].getRow()-p.getRow());
                cells[i].setCol(cells[i].getCol()-p.getCol());
                temp = cells[i].getRow();
                cells[i].setRow(cells[i].getCol()+p.getRow());
                cells[i].setCol((-temp)+p.getCol());
            }
        }
    }
    /*
    public void rotateI() throws OutOfBoardException {
    // If the I block is vertical
        int tempCol, tempRow;
        if (status == 1 || status == 3) {
            for(int i = 0; i < 4; i ++){
                    tempRow = cells[i].getRow();
                    tempCol = cells[i].getCol();
                    cells[i].setRow(tempCol + 1);
                    cells[i].setCol(tempRow - 1);
                }
    } 
    

    // If the I block is horizontal
        else {
            for(int i = 0; i < 4; i ++){
                    tempRow = cells[i].getRow();
                    tempCol = cells[i].getCol();
                    cells[i].setRow(tempCol -1);
                    cells[i].setCol(tempRow + 1);
                }
        }
    }
     */

    //rotates shapes that do not have a distinct center cell (I and O) and calls the rotate_withCenter()
    public void rotate() throws OutOfBoardException{ 
        int tempRow, tempCol; 
        if(shape == 'O') status = 0;
        else if( shape =='I'){
            //rotateI();
            
            if (status == 1|| status == 3 ){
                for(int i = 0; i < 4; i ++){
                    tempRow = cells[i].getRow();
                    tempCol = cells[i].getCol();
                    cells[i].setRow(tempCol + 3);
                    cells[i].setCol(tempRow - 3);
                }
            }
            else if (status == 0 ){
                for(int i = 0; i < 4; i ++){
                    tempRow = cells[i].getRow();
                    tempCol = cells[i].getCol();
                    cells[i].setRow(tempCol + 3);
                    cells[i].setCol(tempRow - 2);
                }
            }
            else {
                for(int i = 0; i < 4; i ++){
                    tempRow = cells[i].getRow();
                    tempCol = cells[i].getCol();
                    cells[i].setRow(tempCol + 3);
                    cells[i].setCol(tempRow -4);
                }
            }
            

        }else{
            rotate_withCenter(); //call the fucntion that rotates other blocks
        }
        status = (status +1)%4; //updates the block status (0-3)
    }
    /*
     * a methos that generates a random block (of different shapes) for the game
     */
    public static Block randomBlock() {
        int blockType = (int) (Math.random() * 7);
        switch (blockType) {
            case 0:
                return new IBlock();
            case 1:
                return new JBlock();
            case 2:
                return new LBlock();
            case 3:
                return new OBlock();
            case 4:
                return new SBlock();
            case 5:
                return new TBlock();
            case 6:
                return new ZBlock();
            default:
                return null;
        }
    }

    public Block copy(){
        Cell[] copy = new Cell[4];

        for (int i = 0; i<4; i++){
            copy[i] = new Cell(this.getCells()[i].getRow(), this.getCells()[i].getCol(),this.getCells()[i].getColor());
        }
        char shape = this.getShape();
        int status = this.getStatus(); 
        
        if (shape == 'T'){
            return new TBlock(copy,shape,status);
        }
        else if (shape == 'I'){
            return new IBlock(copy,shape,status);
        }
        else if (shape == 'J'){
            return new JBlock(copy,shape,status);
            
        }else if (shape == 'S'){
            return new SBlock(copy,shape,status);
            
        }else if (shape == 'Z'){
            return new ZBlock(copy,shape,status);
        }else if (shape == 'O'){
            return new OBlock(copy,shape,status);
        }
        return new LBlock(copy,shape,status);
        
    }
	
	
}

/* 
 * 
 * Block subclasses with constructors : I, J, L, O, S, T, Z
 * They extend the absrtact class Block
 * Indicate shapes of the block
 * 
 * 
*/

class IBlock extends Block{

    public IBlock(){
        super(new Cell(0,3, BlockColor.I_COLOR),
         new Cell(0,4, BlockColor.I_COLOR),
         new Cell(0,5, BlockColor.I_COLOR),
         new Cell(0,6, BlockColor.I_COLOR), 'I', 0);
        }
    public IBlock(Cell[] cells, char shape, int status){
        super(cells, shape, status);
    }
        

}

class JBlock extends Block{

    public JBlock(){
        super(new Cell(0,3, BlockColor.J_COLOR),
         new Cell(0,4, BlockColor.J_COLOR),
         new Cell(0,5, BlockColor.J_COLOR),
         new Cell(-1,3, BlockColor.J_COLOR), 'J', 0);
        }
    public JBlock(Cell[] cells, char shape, int status){
        super(cells, shape, status);
    }
        

}

class LBlock extends Block{

    public LBlock(){
        super(new Cell(0,3, BlockColor.L_COLOR),
         new Cell(0,4, BlockColor.L_COLOR),
         new Cell(0,5, BlockColor.L_COLOR),
         new Cell(-1,5, BlockColor.L_COLOR), 'L', 0);
        }
    public LBlock(Cell[] cells, char shape, int status){
        super(cells, shape, status);
    }
        

}

class OBlock extends Block{

    public OBlock(){
        super(new Cell(0,4, BlockColor.O_COLOR),
         new Cell(-1,4, BlockColor.O_COLOR),
         new Cell(-1,5, BlockColor.O_COLOR),
         new Cell(0,5, BlockColor.O_COLOR), 'O', 0);
        }
        
    public OBlock(Cell[] cells, char shape, int status){
        super(cells, shape, status);
    }
}

class SBlock extends Block{

    public SBlock(){
        super(new Cell(0,3, BlockColor.S_COLOR),
         new Cell(0,4, BlockColor.S_COLOR),
         new Cell(-1,4, BlockColor.S_COLOR),
         new Cell(-1,5, BlockColor.S_COLOR), 'S', 0);
        }
        
    public SBlock(Cell[] cells, char shape, int status){
        super(cells, shape, status);
    }
}

class TBlock extends Block{

    public TBlock(){
        super(new Cell(0,3, BlockColor.T_COLOR),
         new Cell(0,4, BlockColor.T_COLOR),
         new Cell(0,5, BlockColor.T_COLOR),
         new Cell(-1,4, BlockColor.T_COLOR), 'T', 0);
        }
    public TBlock(Cell[] cells, char shape, int status){
        super(cells, shape, status);
    }
        

}

class ZBlock extends Block{

    public ZBlock(){
        super(new Cell(-1,3, BlockColor.Z_COLOR),
         new Cell(0,4, BlockColor.Z_COLOR),
         new Cell(-1,4, BlockColor.Z_COLOR),
         new Cell(0,5, BlockColor.Z_COLOR), 'Z', 0);
        }
    public ZBlock(Cell[] cells, char shape, int status){
        super(cells, shape, status);
    }

}






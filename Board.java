package assignment2Supplied;


public class Board {
	
	public static final int BOARD_HEIGHT = 20;
    public static final int BOARD_WIDTH = 10;

    private BlockColor [][] cells = new BlockColor [BOARD_HEIGHT][BOARD_WIDTH];
    private Block activeBlock;
    private int numFullLinesRemoved=0;
    /*
     * Board constructor
     */

    public Board(){
        cells = new BlockColor [BOARD_HEIGHT][BOARD_WIDTH];

        for (int i =0; i < BOARD_HEIGHT; i++){
            for (int j =0; j < BOARD_WIDTH; j++){
                cells[i][j] = BlockColor.NO_COLOR;
            }
        }
    }

    /*
     * setters and getters
     */
    public BlockColor [][] getCells(){
        return cells;
    }

    public Block activeBlock(){
        return activeBlock;
    }
    public BlockColor blockAt(int x, int y){
        return cells[x][y];
    }

    public void clear(){
        activeBlock = null;
        for (int i =0; i < BOARD_HEIGHT; i++){
            for (int j =0; j < BOARD_WIDTH; j++){
                cells[i][j] = BlockColor.NO_COLOR;
            }
        }
       

    }
    

    /*
     * other methods
     */
    public void blockLanded(){     //change the cells on the board to the block that has been landed
         Cell[] temp = activeBlock.getCells();
         int r;
         int c;
         BlockColor landedColor = activeBlock.getColor();
         for (int i = 0; i < 4; i++){
            r = temp[i].getRow();
            c = temp[i].getCol(); 
            if (r >= 0 && r < BOARD_HEIGHT && c >= 0 && c < BOARD_WIDTH){
                cells[r][c] = landedColor; 
            }  
         }   
         activeBlock = null;          
    }

    public boolean canMove(){
     //check if the cells on the board have no color
        for(int i = 3; i < 7; i ++){
            if (cells[0][i] != BlockColor.NO_COLOR){
                return false;
     
            }
        }
        return true;
    }

    public boolean isBlockValid(Block b){
        Cell[] temp = b.getCells();
        int r;
        int c;
        
        for (int i =0; i<4; i ++){
            r = temp[i].getRow();
            c = temp[i].getCol();
            if ( r < 0 || r >= BOARD_HEIGHT || c< 0 || c >= BOARD_WIDTH ||this.cells[r][c] != BlockColor.NO_COLOR) {
                return false;
                //throw new OutOfBoardException();
            }
        }
         /* 
        for (int i =0; i<4; i ++){
            r = temp[i].getRow();
            c = temp[i].getCol();
            if (cells[r][c] != BlockColor.NO_COLOR) {
                return false;
            }
        }
        */
        return true;

    }

    

    public boolean rotate() throws OutOfBoardException{
        //if (activeBlock == null) return false;
        Block temp = activeBlock.copy();
        try {
            temp.rotate();
            if (isBlockValid(temp) == false ){
                throw new OutOfBoardException();
            }
            activeBlock.rotate();
            return true;
        } catch (OutOfBoardException e) {
            return false;
        }
    }
    public boolean oneLineDown() throws OutOfBoardException{
        //Block temp = activeBlock.movedown();
        Block temp = activeBlock.copy();
        try {
            temp.movedown();
            if (isBlockValid(temp) == false ){
                throw new OutOfBoardException();
            }
            activeBlock.movedown();
            return true;
        } catch (OutOfBoardException e) {
            return false;
        }
         
    }
    public boolean moveLeft() throws OutOfBoardException{
        //Block temp = activeBlock.moveleft();
        Block temp = activeBlock.copy();
        try {
            temp.moveleft();
            if (isBlockValid(temp) == false ){
                throw new OutOfBoardException();
            }
            activeBlock.moveleft();
            return true;
        } catch (OutOfBoardException e) {
            return false;
        }
        

    }
    public boolean moveRight()throws OutOfBoardException{
        
        //Block temp = activeBlock.moveright();
        Block temp = activeBlock.copy();
        try {
            temp.moveright();
            if (isBlockValid(temp) == false ){
                throw new OutOfBoardException();
            }
            activeBlock.moveright();
            return true;
        } catch (OutOfBoardException e) {
            return false;
        }
        
    }
    public boolean newBlock(){
        if(canMove()){
            activeBlock = Block.randomBlock();
            return true;
        }
        return false;
        
    }

    public int removeFullLines(){
        int numOfLinesRemoved = 0;
        for (int r = BOARD_HEIGHT - 1; r >= 0; r--){
            boolean isRemoved = true;
            for (int c = 0; c< BOARD_WIDTH; c++){
                if (cells[r][c] == BlockColor.NO_COLOR){
                    isRemoved = false;
                    break;
                }
            }
            if (isRemoved){
                numOfLinesRemoved++;
                for (int rowMove = r; rowMove > 0; rowMove--){
                    for (int c=0; c<BOARD_WIDTH; c++){
                        cells[rowMove][c] = cells[rowMove-1][c];
                    }
                }
                for (int c= 0; c< BOARD_WIDTH; c++){
                    cells[0][c] = BlockColor.NO_COLOR;
                }
                r++;
            }
        }
        this.numFullLinesRemoved += numOfLinesRemoved;
        return numOfLinesRemoved;
    }


    public int numFullLinesRemoved(){
        return numFullLinesRemoved;
    }

}

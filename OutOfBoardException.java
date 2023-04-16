package assignment2Supplied;

public class OutOfBoardException extends Exception{
	public OutOfBoardException() {
        super("Operation would go beyond the edge of the board");
    }
}

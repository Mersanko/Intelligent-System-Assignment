/**
 * This class implements a tic-tac-toe player that always places a piece on a
 * blank position taken from the input of the user. You do not need to modify
 * this class.
 * 
 * @author Harchris
 */
public class HumanPlayer implements PlayerInterface {
	/*
	 * This method returns the position of the move made by this player on the
	 * given gameState parameter.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see PlayerInterface#getMove(TicTacToeState)
	 */
	@Override
	public int getMove(TicTacToeState gameState) {
		// return a move taken from the input of the user
		return TicTacToeGame.getInstance().getUserInput();
	}
}

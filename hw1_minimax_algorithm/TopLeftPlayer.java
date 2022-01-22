/**
 * This class implements a tic-tac-toe player that always places a piece on the
 * topmost-leftmost blank position. You do not need to modify this class.
 * 
 * @author Harchris
 */
public final class TopLeftPlayer implements PlayerInterface {
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
		int position;

		// find the topmost-leftmost blank position
		for (position = 0; position < 9; ++position) {
			if (gameState.isValidMove(position, gameState.getCurrentPlayer())) {
				break;
			}
		}

		return position;
	}
}

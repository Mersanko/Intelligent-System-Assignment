/**
 * This class is an immutable implementation for representing a tic-tac-toe game
 * state. An instance of this class stores the current player (the player that
 * will make the next move) in the state. The public constructor of this class
 * will create the starting state where the first player 'X' is the current
 * player. Player 'X' will always be the first player in this implementation and
 * the second player will be player 'O'. This class provides methods that
 * facilitates the game. There is a doMove() method that returns a new state
 * from the given state that incorporates the move done by the current player in
 * the given state. There is an isValidMove() method that determines whether a
 * player can place a mark on the given tile. There is also a getWinner() method
 * that returns the winner of the given state (0 = draw, 1 = player 'X', 2 =
 * player 'O') or -1 if the given state represents a game in progress. A
 * position on the board is represented by an int (0 = top-left, 8 =
 * bottom-right). The positions are labeled in succession from left to right
 * first then from top to bottom. You do not need to modify this class.
 * 
 * @author Harchris
 */
public final class TicTacToeState {
	// the constant for a blank tile
	public static final int PIECE_NONE = 0;

	// first player
	public static final int PIECE_X = 1;

	// second player
	public static final int PIECE_O = 2;

	// the int that holds the game state
	private final int _state;

	/**
	 * The class constructor that creates the initial state of the game (player
	 * 'X' is the current/first player).
	 */
	public TicTacToeState() {
		_state = PIECE_X << (9 * 2);
	}

	/**
	 * A private constructor used by this class to create a new state instance
	 * from the given int encoding.
	 * 
	 * @param state
	 *            The int encoding of the state to be created.
	 */
	private TicTacToeState(int state) {
		_state = state;
	}

	/**
	 * This method returns the current player of the current instance.
	 * 
	 * @return The current player.
	 */
	public int getCurrentPlayer() {
		return (_state >> (9 * 2)) & 0x03;
	}

	/**
	 * This method creates a new state that incorporates the move done by the
	 * current player.
	 * 
	 * @param x
	 *            The position of the move of the current player.
	 * @param player
	 *            The current player.
	 * @return The new state after doing the move.
	 */
	public TicTacToeState doMove(int x, int player) {
		int state, nextPlayer;

		// validate the parameters
		if (player != getCurrentPlayer()) {
			throw new RuntimeException("invalid player");
		}
		if ((x < 0) || (x > 8)) {
			throw new RuntimeException("invalid move");
		}
		if (getPiece(x) != PIECE_NONE) {
			throw new RuntimeException("invalid move");
		}
		if (getWinner() != -1) {
			// no moves after the game is over
			throw new RuntimeException("invalid state");
		}

		state = _state & 0x3FFFF;
		state |= (player << (x * 2));
		// next player
		nextPlayer = (player == PIECE_O) ? PIECE_X : PIECE_O;
		state |= (nextPlayer << (9 * 2));

		return new TicTacToeState(state);
	}

	/**
	 * This method returns the piece at the given board position.
	 * 
	 * @param x
	 *            The board position to get the piece from.
	 * @return The piece at the given position (0 = blank, 1 = 'X', 2 = 'O').
	 */
	public int getPiece(int x) {
		if ((x < 0) || (x > 8)) {
			throw new RuntimeException("invalid position");
		}

		return (_state >> (x * 2)) & 0x03;
	}

	/**
	 * This method determines whether the given player can make a valid move at
	 * the given board position.
	 * 
	 * @param x
	 *            The board position.
	 * @param player
	 *            The player that will make the move.
	 * @return true if the player can make the move at the given position, false
	 *         otherwise.
	 */
	public boolean isValidMove(int x, int player) {
		// validate the parameters
		if (player != getCurrentPlayer()) {
			throw new RuntimeException("invalid player");
		}
		if (getWinner() != -1) {
			throw new RuntimeException("invalid state");
		}

		return getPiece(x) == PIECE_NONE;
	}

	/**
	 * This method determines the winner in the current instance. This method
	 * returns -1 if the game is in progress, 0 if the game ended in a draw, 1
	 * if the game ended with player 'X' winning, and 2 if the game ended with
	 * player 'O' winning.
	 * 
	 * @return The winner as described.
	 */
	public int getWinner() {
		int i;

		// check eight winning conditions
		i = PIECE_NONE;
		if ((getPiece(0) != PIECE_NONE) && (getPiece(0) == getPiece(1))
				&& (getPiece(0) == getPiece(2))) {
			// first row
			i = getPiece(0);
		} else if ((getPiece(3) != PIECE_NONE) && (getPiece(3) == getPiece(4))
				&& (getPiece(3) == getPiece(5))) {
			// second row
			i = getPiece(3);
		} else if ((getPiece(6) != PIECE_NONE) && (getPiece(6) == getPiece(7))
				&& (getPiece(6) == getPiece(8))) {
			// third row
			i = getPiece(6);
		} else if ((getPiece(0) != PIECE_NONE) && (getPiece(0) == getPiece(3))
				&& (getPiece(0) == getPiece(6))) {
			// first column
			i = getPiece(0);
		} else if ((getPiece(1) != PIECE_NONE) && (getPiece(1) == getPiece(4))
				&& (getPiece(1) == getPiece(7))) {
			// second column
			i = getPiece(1);
		} else if ((getPiece(2) != PIECE_NONE) && (getPiece(2) == getPiece(5))
				&& (getPiece(2) == getPiece(8))) {
			// third column
			i = getPiece(2);
		} else if ((getPiece(0) != PIECE_NONE) && (getPiece(0) == getPiece(4))
				&& (getPiece(0) == getPiece(8))) {
			// diagonal 1
			i = getPiece(0);
		} else if ((getPiece(2) != PIECE_NONE) && (getPiece(2) == getPiece(4))
				&& (getPiece(2) == getPiece(6))) {
			// diagonal 2
			i = getPiece(2);
		}

		if (i != PIECE_NONE) {
			// i is the previous player (the winner)
			return i;
		}

		// determine if the game is over
		for (i = 0; i < 9; ++i) {
			if (getPiece(i) == PIECE_NONE) {
				// game is not over
				return -1;
			}
		}

		// the game is a draw
		return PIECE_NONE;
	}
}

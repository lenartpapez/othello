public class GameEngine {

	Board board;

	public GameEngine(Board board) {
		this.board = board;
	}

	public void makeMove(int x, int y, char piece) {
		// Put the piece at x,y
		board.board[x][y] = piece;
		// Figure out the character of the opponent's piece
		char opponent = 'O';
		if (piece == 'O')
			opponent = 'X';
        if (x == 2 || x == 3) {
            // up
            if (checkFlip(x - 1, y, -1, 0, piece, opponent))
                    flipPieces(x - 1, y, -1, 0, piece, opponent);
            if( y == 0 || y == 1) {
                // right
                if (checkFlip(x, y + 1, 0, 1, piece, opponent))
                    flipPieces(x, y + 1, 0, 1, piece, opponent);
                // up-right
                if (checkFlip(x - 1, y + 1, -1, 1, piece, opponent))
                    flipPieces(x - 1, y + 1, -1, 1, piece, opponent);                        
            } else {
                // left
                if (checkFlip(x, y - 1, 0, -1, piece, opponent))
                    flipPieces(x, y - 1, 0, -1, piece, opponent);
                // up-left
                if (checkFlip(x - 1, y - 1, -1, -1, piece, opponent))
                    flipPieces(x - 1, y - 1, -1, -1, piece, opponent);
            }
        } else {
            // down
                if (checkFlip(x - 1, y, -1, 0, piece, opponent))
                    flipPieces(x - 1, y, -1, 0, piece, opponent);
            if( y == 0 || y == 1) {
                // right
                if (checkFlip(x, y + 1, 0, 1, piece, opponent))
                    flipPieces(x, y + 1, 0, 1, piece, opponent);
                // down-right
                if (checkFlip(x + 1, y + 1, 1, 1, piece, opponent))
                    flipPieces(x + 1, y + 1, 1, 1, piece, opponent);
            } else {
                // left
                if (checkFlip(x, y - 1, 0, -1, piece, opponent))
                    flipPieces(x, y - 1, 0, -1, piece, opponent);
                // down-left
                if (checkFlip(x + 1, y - 1, 1, -1, piece, opponent))
                    flipPieces(x + 1, y - 1, 1, -1, piece, opponent);
            }
        }
	}

	public boolean checkFlip(int x, int y, int deltaX, int deltaY,
 		char myPiece, char opponentPiece) {
		if (board.board[x][y] == opponentPiece) {
			while ((x >= 0) && (x < 4) && (y >= 0) && (y < 4)) {
				x += deltaX;
				y += deltaY;
				if (board.board[x][y] == '.') // not consecutive
					return false;
				if (board.board[x][y] == myPiece)
					return true; // At least one piece we can flip
				else {
					// It is an opponent piece, 2 keep scanning in our direction
				}
			}
		}
		return false; // Either no consecutive opponent pieces or hit the edge
	}
		// Flips pieces in the given direction until we don't hit any more opponent pieces.
		// Assumes this is a valid direction to flip (we eventually hit one of our pieces).
		public void flipPieces(int x, int y, int deltaX, int deltaY,
		 	char myPiece, char opponentPiece) {
			while (board.board[x][y] == opponentPiece) {
				board.board[x][y] = myPiece;
				x += deltaX;
				y += deltaY;
			}
		}

		public boolean validMove(int x, int y, char piece) {
			// Check that the coordinates are empty
			if(x > 3 || y > 3) return false;
			if (board.board[x][y] != '.')
				return false;
			// Figure out the character of the opponent's piece
			char opponent = 'O';
			if (piece == 'O')
				opponent = 'X';
			if (x == 2 || x == 3) {
                    // up
                    if (checkFlip(x - 1, y, -1, 0, piece, opponent))
                           return true;
                    if( y == 0 || y == 1) {
                        // right
                        if (checkFlip(x, y + 1, 0, 1, piece, opponent))
                            return true;
                        // up-right
                        if (checkFlip(x - 1, y + 1, -1, 1, piece, opponent))
                            return true;          
                    } else {
                        // left
                        if (checkFlip(x, y - 1, 0, -1, piece, opponent))
                           	return true;
                        // up-left
                        if (checkFlip(x - 1, y - 1, -1, -1, piece, opponent))
                            return true;
                    }
             } else {
                // down
                if (checkFlip(x - 1, y, -1, 0, piece, opponent))
                    return true;
                if( y == 0 || y == 1) {
                    // right
                    if (checkFlip(x, y + 1, 0, 1, piece, opponent))
                        return true;
                    // down-right
                    if (checkFlip(x + 1, y + 1, 1, 1, piece, opponent))
                        return true;
                } else {
                    // left
                    if (checkFlip(x, y - 1, 0, -1, piece, opponent))
                        return true;
                    // down-left
                    if (checkFlip(x + 1, y - 1, 1, -1, piece, opponent))
                        return true;
                }
            }
			return false; // If we get here, we didn't find a valid flip direction
		}
}
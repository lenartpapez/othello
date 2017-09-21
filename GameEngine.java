import java.util.ArrayList;

public class GameEngine {

	Board board;
	int maxDepth = 12;
	int score;
	ScoredMove sm;

	public GameEngine(Board board) {
		this.board = board;
	}

	public void makeMove(Board b, int x, int y, char piece) {
		// Put the piece at x,y
		b.board[x][y] = piece;
		// Figure out the character of the opponent's piece
		char opponent = 'O';
		if (piece == 'O')
			opponent = 'X';
        if (x == 2 || x == 3) {
            // up
            if (checkFlip(b, x - 1, y, -1, 0, piece, opponent))
                    flipPieces(b, x - 1, y, -1, 0, piece, opponent);
            if( y == 0 || y == 1) {
                // right
                if (checkFlip(b, x, y + 1, 0, 1, piece, opponent))
                    flipPieces(b, x, y + 1, 0, 1, piece, opponent);
                // up-right
                if (checkFlip(b, x - 1, y + 1, -1, 1, piece, opponent))
                    flipPieces(b, x - 1, y + 1, -1, 1, piece, opponent);                        
            } else {
                // left
                if (checkFlip(b, x, y - 1, 0, -1, piece, opponent))
                    flipPieces(b, x, y - 1, 0, -1, piece, opponent);
                // up-left
                if (checkFlip(b, x - 1, y - 1, -1, -1, piece, opponent))
                    flipPieces(b, x - 1, y - 1, -1, -1, piece, opponent);
            }
        } else {
            // down
            if (checkFlip(b, x + 1, y, 1, 0, piece, opponent))
                flipPieces(b, x + 1, y, 1, 0, piece, opponent);
            if( y == 0 || y == 1) {
                // right
                if (checkFlip(b, x, y + 1, 0, 1, piece, opponent))
                    flipPieces(b, x, y + 1, 0, 1, piece, opponent);
                // down-right
                if (checkFlip(b, x + 1, y + 1, 1, 1, piece, opponent))
                    flipPieces(b, x + 1, y + 1, 1, 1, piece, opponent);
            } else {
                // left
                if (checkFlip(b, x, y - 1, 0, -1, piece, opponent))
                    flipPieces(b, x, y - 1, 0, -1, piece, opponent);
                // down-left
                if (checkFlip(b, x + 1, y - 1, 1, -1, piece, opponent))
                    flipPieces(b, x + 1, y - 1, 1, -1, piece, opponent);
            }
        }
	}

	public boolean checkFlip(Board b, int x, int y, int deltaX, int deltaY,
 		char myPiece, char opponentPiece) {
		if (b.board[x][y] == opponentPiece) {
			while ((x >= 0) && (x < 4) && (y >= 0) && (y < 4)) {
				x += deltaX;
				y += deltaY;
				if(x == 4 || y == 4) return false;
				if(x == -1 || y == -1) return false;
				if (b.board[x][y] == '.') // not consecutive
					return false;
				if (b.board[x][y] == myPiece)
					return true; // At least one piece we can flip
				else {
					// It is an opponent piece, keep scanning in our direction
				}
			}
		}
		return false; // Either no consecutive opponent pieces or hit the edge
	}
		// Flips pieces in the given direction until we don't hit any more opponent pieces.
		// Assumes this is a valid direction to flip (we eventually hit one of our pieces).
		public void flipPieces(Board b, int x, int y, int deltaX, int deltaY,
		 	char myPiece, char opponentPiece) {
			while (b.board[x][y] == opponentPiece) {
				b.board[x][y] = myPiece;
				x += deltaX;
				y += deltaY;
			}
		}

		public boolean validMove(Board b, int x, int y, char piece) {
			// Check that the coordinates are empty
			if (b.board[x][y] != '.')
				return false;
			// Figure out the character of the opponent's piece
			char opponent = 'O';
			if (piece == 'O')
				opponent = 'X';
			if (x == 2 || x == 3) {
                    // up
                    if (checkFlip(b, x - 1, y, -1, 0, piece, opponent))
                           return true;
                    if( y == 0 || y == 1) {
                        // right
                        if (checkFlip(b, x, y + 1, 0, 1, piece, opponent))
                            return true;
                        // up-right
                        if (checkFlip(b, x - 1, y + 1, -1, 1, piece, opponent))
                            return true;          
                    } else {
                        // left
                        if (checkFlip(b, x, y - 1, 0, -1, piece, opponent))
                           	return true;
                        // up-left
                        if (checkFlip(b, x - 1, y - 1, -1, -1, piece, opponent))
                            return true;
                    }
             } else {
                // down
                if (checkFlip(b, x + 1, y, 1, 0, piece, opponent))
                    return true;
                if( y == 0 || y == 1) {
                    // right
                    if (checkFlip(b, x, y + 1, 0, 1, piece, opponent))
                        return true;
                    // down-right
                    if (checkFlip(b, x + 1, y + 1, 1, 1, piece, opponent))
                        return true;
                } else {
                    // left
                    if (checkFlip(b, x, y - 1, 0, -1, piece, opponent))
                        return true;
                    // down-left
                    if (checkFlip(b, x + 1, y - 1, 1, -1, piece, opponent))
                        return true;
                }
            }
			return false; // If we get here, we didn't find a valid flip direction
		}
		

		public boolean gameOver(Board b) {
			return b.freeSpaces() == 0;
		}

		public boolean isCorner(Move m) {
			return ( (m.getX() == 0 && m.getY() == 0) ||
				(m.getX() == 0 && m.getY() == 3) || 
				(m.getX() == 3 && m.getY() == 0) ||
				(m.getX() == 3 && m.getY() == 3) );
		}

		public ArrayList<Move> getMovesForPlayer(Board b, char piece) {
			ArrayList<Move> moves = new ArrayList<>();
			// Check each square of the board and if we can move there, remember the coords
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					if(b.board[x][y]=='.') {
						if (validMove(b, x, y, piece)) {
							moves.add(new Move(x, y));
					 	}
					}
				}
			}
			return moves;
		}	

		public int getScoreForPlayer(Board b, char piece) {
			int score = 0;
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					if(b.board[x][y]==piece) {
						score++;
					}
				}
			}
			return score;
		}


		public ScoredMove miniMax(Board b, int alpha, int beta, int depth, char player) {
			int value = 0;
			char opponent = 'X';
			// figure out who the player is
			if(player == 'X') {
				opponent = 'O';
			}

			// if no possible moves
			if(getMovesForPlayer(b, player).size() == 0) {
				return new ScoredMove();
			}

			// if depth reached or gameover
			if(depth >= maxDepth || gameOver(b)) {
				value = getScoreForPlayer(b, player) - getScoreForPlayer(b, opponent);
				return new ScoredMove(value);
			}

			ScoredMove moveToMake;
			ScoredMove bestMove = null;

			if(player == 'X') {
				for(Move m : getMovesForPlayer(b, player)) {
					Board bc = (Board)b.clone();
					makeMove(bc, m.x, m.y, player);
					moveToMake = miniMax(bc, alpha, beta, depth + 1, opponent);
					if(bestMove == null || bestMove.score < moveToMake.score) {
						bestMove = moveToMake;
						bestMove.move = m;
					}
					if(moveToMake.score > alpha) {
						alpha = moveToMake.score;
						bestMove = moveToMake;
					}
					if(beta <= alpha) {
						bestMove.score = beta;
						bestMove.move = null;
						return bestMove;
					}
				}
				return bestMove;
			} else {
				for(Move m : getMovesForPlayer(b, player)) {
					Board bc = (Board)b.clone();
					makeMove(bc, m.x, m.y, player);
					moveToMake = miniMax(bc, alpha, beta, depth + 1, opponent);
					if(bestMove == null || bestMove.score > moveToMake.score) {
						bestMove = moveToMake;
						bestMove.move = m;
					}
					if(moveToMake.score < beta) {
						beta = moveToMake.score;
						bestMove = moveToMake;
					}
					if(beta <= alpha) {
						bestMove.score = alpha;
						bestMove.move = null;
						return bestMove;
					}
				}
				return bestMove;
			}	
		}
}
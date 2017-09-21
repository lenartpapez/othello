import java.util.ArrayList;

class Board implements Cloneable {

	char[][] board;
	int alpha;
	int beta;
	int val;
	ArrayList<Board> children;

	public Board() {
 		board = new char[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = '.';
			}
		}
		board[1][2] = 'X';
		board[1][1] = 'O';
		board[2][2] = 'O';
		board[2][1] = 'X';
		children = new ArrayList<>();
	}
	public int freeSpaces() {	
		int count = 0;
		for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(board[i][j] == '.') count++;
				}
		}
		return count;
	}

	public void displayBoard() {
		System.out.println("\n   | 0  1  2  3");
        System.out.println("---|-----------");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%2d |", i);

            for (int j = 0; j < 4; j++) {
                System.out.printf("%2s ", this.board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
	}

	public Object clone() {
        Board b = new Board();
        b.alpha = this.alpha;
        b.beta = this.beta;
        for(int row = 0 ; row < 4; row++){
            for(int col = 0; col < 4; col ++){
                b.board[row][col] = this.board[row][col];
            }
        }
        return b;
   }
}


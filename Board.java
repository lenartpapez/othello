class Board {

	char[][] board;

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
	}

	public void displayBoard() {
		System.out.println("   | 0  1  2  3");
        System.out.println("---|-----------");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%2d |", i);

            for (int j = 0; j < 4; j++) {
                System.out.printf("%2s ", this.board[i][j]);
            }
            System.out.println();
        }
	}
}


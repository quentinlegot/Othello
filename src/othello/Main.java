package othello;

public class Main {

	
	public static void main(String[] args) {
		int p1 = 1, p2 = 2;
		int[][] board = initialize(p1, p2);
		State game = new State(board, p1, p2);
		while(!game.isOver()) {
			int player = game.getCurrentPlayer();
			
		}
	}
	
	public static int[][] initialize(int p1, int p2){
		int[][] board = new int[7][7];
		board[0][0] = p2;
		board[0][6] = p1;
		board[6][0] = p1;
		board[6][6] = p2;
		return board;
	}
	
}

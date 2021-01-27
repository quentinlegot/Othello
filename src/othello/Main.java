package othello;

public class Main {

	
	public static void main(String[] args) {
		String p1 = "B", p2 = "R";
		String[][] board = initialize(p1, p2);
		State game = new State(board, p1, p2);
		while(!game.isOver()) {
			String player = game.getCurrentPlayer();
			
		}
	}
	
	public static String[][] initialize(String p1, String p2){
		String[][] board = new String[7][7];
		board[0][0] = p2;
		board[0][6] = p1;
		board[6][0] = p1;
		board[6][6] = p2;
		return board;
	}
	
}

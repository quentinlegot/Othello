package othello;

import java.awt.Point;
import java.util.ArrayList;

public class Main {

	
	public static void main(String[] args) {
		for (int k=-1; k<2;k++) {
			for (int l=-1; l<2; l++) {
				if (k!=0 || l!=0) {			
						System.out.println(k+";"+l);
					
				}
			}
		}
		int p1 = 1, p2 = 2;
		int[][] board = initialize(p1, p2);
		State game = new State(board, p1, p2,0,0);
		//while(!game.isOver()) {
			int player = game.getCurrentPlayer();
			
		//}
		ArrayList<Point> a = new ArrayList<Point>();
		a.add(new Point(3,3));
		System.out.println(game.getMove(p1).toString());
		//System.out.println(a.get(0).toString());
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

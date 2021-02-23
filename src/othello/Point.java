package othello;

import othello.players.Player;

public class Point {

	private int x;
	private int y;

	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}

	public boolean isJump(Point other, Player[][] board) {
		return (board[(x+other.getX())/2][(y+other.getY())/2] != null);
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	@Override
	public String toString () {
		return "(" + y + ", " + x + ")";
	}
	
}

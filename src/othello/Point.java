package othello;

import othello.players.Player;

public class Point {

	private final int x;
	private final int y;

	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}

	public boolean isJump(Point other, Player[][] board) {
		double value = Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2);
		return (value == 4 || value == 8) && board[(x+other.getX())/2][(y+other.getY())/2] != null;
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

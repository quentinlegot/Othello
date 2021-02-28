package othello;

import othello.players.Player;

public class Point {

	private final int x;
	private final int y;

	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}

	/**
	 * check if {@code other} point is considered as jump
	 * @param other arrival point
	 * @param board current {@link State} situation
	 * @return {@code true} if other is considered as a jump depending of {@code this}, {@code false} otherwise
	 */
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

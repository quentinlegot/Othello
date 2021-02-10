package othello;

public class Point {

	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isJump(Point other) {
		return Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2) == 4;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	@Override
	public String toString () {
		return "("+x+","+y+")";
	}
	
}

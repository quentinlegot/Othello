package othello;

public class Point {

	private int x;
	private int y;

	public Point(int y, int x) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isJump(Point other) {
		double value = Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2);
		return  value == 4 || value == 8;
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

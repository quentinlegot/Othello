package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

public abstract class Player {

	protected final int depth;

	public Player(int depth) {
		this.depth = depth;
	}

	public abstract Pair<Point, Point> play(State board);

}

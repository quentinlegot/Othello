package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

public abstract class Player {

	protected final int id;

	public int getId() {
		return this.id;
	}

	public Player(int id) {
		this.id = id;
	}

	public abstract Pair<Point, Point> play(State board);

}

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

	protected int evaluate(State game){
		Player winner = game.getWinner();
		if(winner == null)
			return 0;
		else if(winner == game.getCurrentPlayer())
			return 1;
		return -1;
	}

}

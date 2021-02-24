package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

public abstract class Player {

	protected final int depth;
	protected int complexity;

	public Player(int depth) {
		this.depth = depth;
		this.complexity = 0;
	}
	
	public int getComplexity () {
		return this.complexity;
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

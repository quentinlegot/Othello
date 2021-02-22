package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

import java.util.LinkedList;
import java.util.Random;

public class RandomPlayer extends Player {

	Random random;

	public RandomPlayer(int id) {
		super(id);
		random = new Random();
	}

	@Override
	public Pair<Point, Point> play(State game, int depth) {
		LinkedList<Pair<Point, Point>> moves = game.getMove(this);
		return moves.get(random.nextInt(moves.size()));
	}

}

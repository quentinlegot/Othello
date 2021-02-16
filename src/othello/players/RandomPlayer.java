package othello.players;

import java.util.ArrayList;
import java.util.Random;

import othello.Pair;
import othello.Point;
import othello.State;

public class RandomPlayer implements Player {

	@Override
	public Pair<Point, Point> play(ArrayList<Pair<Point, Point>> moves, State game, Player player) {
		return moves.get(new Random().nextInt(moves.size()));
	}

}

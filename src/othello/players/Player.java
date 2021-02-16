package othello.players;

import java.util.ArrayList;

import othello.Pair;
import othello.Point;
import othello.State;

public interface Player {

	public Pair<Point, Point> play(ArrayList<Pair<Point, Point>> moves, State board, Player player);

}

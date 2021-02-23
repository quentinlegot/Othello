package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

public class NegamaxPlayer extends Player {


    public NegamaxPlayer(int depth) {
        super(depth);
    }

    @Override
    public Pair<Point, Point> play(State game) {
        int bestValue = Integer.MIN_VALUE;
        Pair<Point, Point> bestMove = null;
        for(Pair<Point, Point> move : game.getMove(game.getCurrentPlayer())) {
            State nextState = game.copy();
            nextState.play(move);
            int value = -negamax(nextState, depth);
            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private Integer negamax(State game, int depth) {
        if(depth == 0 || game.isOver()) {
            return game.getScore(game.getCurrentPlayer());
        }
        int bestValue = Integer.MIN_VALUE;
        for(Pair<Point, Point> move : game.getMove(game.getCurrentPlayer())) {
            State nextState = game.copy();
            bestValue = Math.max(bestValue,-negamax(nextState.play(move),depth-1));

        }
        return bestValue;
    }

}

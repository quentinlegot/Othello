package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

public class NegamaxPlayer extends Player {

    public NegamaxPlayer(int depth) {
        super(depth);
    }

    /**
     * This method will find the best move to try to win the game by searching all possible movement given by
     * {@link State#getMove(Player)} in the limit of {@link Player#depth}
     * @see NegamaxPlayer#negamax(State, int)
     * @see AlphaBetaPlayer#play(State)
     * @see Player#play(State)
     */
    @Override
    public Pair<Point, Point> play(State game) {
        int bestValue = Integer.MIN_VALUE;
        Pair<Point, Point> bestMove = null;
        for(Pair<Point, Point> move : game.getMove(game.getCurrentPlayer())) {
            State nextState = game.play(move);
            int value = -negamax(nextState, this.depth);
            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int negamax(State state, int depth) {
        if(depth == 0 || state.isOver()) {
            return evaluate(state);
        }
        else{
            int m = Integer.MIN_VALUE;
            for (Pair<Point, Point> move : state.getMove(state.getCurrentPlayer())) {
                State nextState = state.play(move);
                complexity++;
                m= Math.max(m,-negamax(nextState,depth-1));
            }
            return m;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof NegamaxPlayer);
    }
}

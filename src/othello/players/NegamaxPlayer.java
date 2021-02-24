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
                m= Math.max(m,-negamax(nextState,depth-1));
            }
            return m;
        }
    }
    private int evaluate(State game){
        Player winner = game.getWinner();
        if(winner == null)
            return 0;
        else if(winner == game.getCurrentPlayer())
            return -1;
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof NegamaxPlayer);
    }
}

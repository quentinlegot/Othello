package othello.players;

import othello.Pair;
import othello.Point;
import othello.State;

public class NegamaxPlayer extends Player {

    public NegamaxPlayer(int id) {
        super(id);
    }

    @Override
    public Pair<Point, Point> play(State game,int depth) {
        int bestValue = Integer.MIN_VALUE;
        Pair<Point, Point> bestMove = null;
        for(Pair<Point, Point> move : game.getMove(game.getCurrentPlayer())) {
            State nextState = game.play(move);
            int value = -negamax(nextState,depth);
            if(value > bestValue){
                bestValue = value;
                bestMove = move;
            }
        }
        System.out.println("Negamax à joué");
        return bestMove;
    }

    private Integer negamax(State game, int depth) {
        if(depth == 0 || game.isOver()) {
            int score1 = game.getScore(game.player1);
            int score2 = game.getScore(game.player2);
            if (game.getCurrentPlayer() == game.player1){
                return score1 >= score2 ? score1 : -score2;
            }
            else{
                return score1 >= score2 ? score2 : -score1;
            }
        }
        int value = Integer.MIN_VALUE;
        for(Pair<Point, Point> move : game.getMove(game.getCurrentPlayer())) {
            value = Math.max(value, -negamax(game.play(move),depth-1));
        }
        return value;
    }

}

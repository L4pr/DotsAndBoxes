package player;

import game.Board;
import game.Game;

import java.util.HashMap;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }
    @Override
    int determineMove(Game game) {
        Board board = game.getBoard();

        int bestMove = -1;
        int bestValue = Integer.MIN_VALUE;

        for (int i = 0; i < 60; i++) {
            game = new Game(board.getClone());
            if (game.isValidMove(i, board)) {
                game.makeMove(i);
                int moveValue = alphaBetaMax(board, Integer.MIN_VALUE, Integer.MAX_VALUE, 5, game.current);
                if (moveValue > bestValue) {
                    bestValue = moveValue;
                    bestMove = i;
                }
            }
        }


        return bestMove;
    }

    int alphaBetaMax (Board board, int alpha, int beta, int depthleft, int maxPlayer) {
        Game game = new Game(board.getClone());
        if (depthleft == 0) {
            int eval = evaluate(game, maxPlayer);
            return eval;
        }
        if (game.isGameOver(board)) {
            int eval = evaluate(game, maxPlayer);
            return eval;
        }
        for (int i = 0; i < 60; i++) {
            game = new Game(board.getClone());
            if (game.isValidMove(i, board)) {
                int pointsMade = game.makeMove(i);
                int score;
                if (pointsMade == 0) {
                    score = alphaBetaMin(game.getBoard(), alpha, beta, depthleft - 1, maxPlayer);
                } else {
                    score = alphaBetaMax(game.getBoard(), alpha, beta, depthleft - 1, maxPlayer);
                }
                if (score >= beta) {
                    return beta;
                }
                if (score > alpha) {
                    alpha = score;
                }
            }
        }
        return alpha;
    }

    int alphaBetaMin (Board board, int alpha, int beta, int depthleft, int maxPlayer) {
        Game game = new Game(board.getClone());
        if (depthleft == 0) {
            int eval = evaluate(game, maxPlayer);
            return eval;
        }
        if (game.isGameOver(board)) {
            int eval = evaluate(game, maxPlayer);
            return eval;
        }
        for (int i = 0; i < 60; i++) {
            game = new Game(board.getClone());
            if (game.isValidMove(i, board)) {
                int pointsMade = game.makeMove(i);
                int score;
                if (pointsMade == 0) {
                    score = alphaBetaMax(game.getBoard(), alpha, beta, depthleft - 1, maxPlayer);
                } else {
                    score = alphaBetaMin(game.getBoard(), alpha, beta, depthleft - 1, maxPlayer);
                }
                if (score <= alpha) {
                    return alpha;
                }
                if (score < beta) {
                    beta = score;
                }
            }
        }
        return beta;
    }

    int evaluate(Game game, int maxPlayer) {
        if (maxPlayer == 1) {
            return game.getBoard().playerPoints[1] - game.getBoard().playerPoints[0];
        } else {
            return game.getBoard().playerPoints[0] - game.getBoard().playerPoints[1];
        }
    }
}

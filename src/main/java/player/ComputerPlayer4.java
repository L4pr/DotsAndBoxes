package player;

import game.Board;
import game.Game;

import java.util.*;
import java.util.stream.Collectors;

public class ComputerPlayer4 extends Player {

    public ComputerPlayer4(String name) {
        super(name);
    }

    private Map<Game, Integer> moveTable;

    private Map<Integer, ComputerPlayer4.TranspositionEntry> transpositionTable; // Transposition table

    // Represents an entry in the transposition table
    private static class TranspositionEntry {
        int depthLeft;
        int score;
        // Other fields like flag (exact, lower-bound, upper-bound) can be added here
    }

    int MAX_DEPTH = 60;
    int valuesSearched = 0;
    @Override
    int determineMove(Game game) {
        valuesSearched = 0;
        moveTable = new HashMap<>();
        transpositionTable = new HashMap<>();
        Board bestBoard = null;
        int maxPlayer = game.current;

        Board board = game.getBoard();
        long startTime = System.currentTimeMillis();
        List<Game> moves = game.generateMoves();
        for (int depth = 1; depth <= MAX_DEPTH; depth++) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - startTime > 1000) {
                break; // Time limit exceeded, exit the loop
            }
            System.out.print(depth + " - ");
            for (Game childGame : moves) {
                int moveValue;
                if (childGame.current == maxPlayer) {
                    moveValue = alphaBetaMax(childGame, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, maxPlayer);
                } else {
                    moveValue = alphaBetaMin(childGame, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, maxPlayer);
                }
                if (moveTable.containsKey(childGame)) {
                    moveTable.replace(childGame, moveValue);
                } else {
                    moveTable.put(childGame, moveValue);
                }
            }
            moves = sortByValue(moveTable);
        }

        System.out.println();
        System.out.println("positions searched: " + valuesSearched);

        int bestValue = Integer.MIN_VALUE;
        Boolean allEqual = true;
        for (Game games : moveTable.keySet()) {
            int move = moveTable.get(games);
            if (move > bestValue) {
                bestValue = moveTable.get(games);
                bestBoard = games.getBoard();
            }
            if (move != bestValue) {
                allEqual = false;
            }
        }

        System.out.println("Evaluation: " + bestValue);

        if (bestBoard != null && !allEqual) {
            long moveLong = bestBoard.getBoard() ^ board.getBoard();
            for (int i = 0; i < 60; i++) {
                if ((moveLong & (1L << (59 - i))) != 0) {
                    return i;
                }
            }
        }

        Game game2 = game.getClone();
        for (int i = 0; i < 60; i++) {
            if (game2.isValidMove(i, game2.getBoard())) {
                if (game2.getBoard().moveMakesBox(i) != 0) {
                    System.out.println("just makes a box");
                    return i;
                }
            }
        }

        System.out.println("Random move chosen");
        return new RandomPlayer("dummy").determineMove(game);
    }

    int alphaBetaMax (Game game, int alpha, int beta, int depthleft, int maxPlayer) {
        valuesSearched += 1;
        Board board = game.getBoard();
        int hashKey = game.hashCode();
        TranspositionEntry storedScoreAndDepth = transpositionTable.get(hashKey);
        if (storedScoreAndDepth != null && storedScoreAndDepth.depthLeft <= depthleft) {
            return storedScoreAndDepth.score;
        }
        if (depthleft == 0 || game.isGameOver(board)) {
            return evaluate(game, maxPlayer);
        }
        for (Game newGame : game.generateMoves()) {
            int score;
            if (newGame.current == maxPlayer) {
                score = alphaBetaMax(newGame, alpha, beta, depthleft - 1, maxPlayer);
            } else {
                score = alphaBetaMin(newGame, alpha, beta, depthleft - 1, maxPlayer);
            }
            if (score >= beta) {
                return beta;
            }
            if (score > alpha) {
                alpha = score;
            }
        }

        TranspositionEntry transEntry = new TranspositionEntry();
        transEntry.depthLeft = depthleft;
        transEntry.score = alpha;
        transpositionTable.put(hashKey, transEntry);

        return alpha;
    }

    int alphaBetaMin (Game game, int alpha, int beta, int depthleft, int maxPlayer) {
        valuesSearched += 1;
        Board board = game.getBoard();
        int hashKey = game.hashCode();
        TranspositionEntry storedScoreAndDepth = transpositionTable.get(hashKey);
        if (storedScoreAndDepth != null && storedScoreAndDepth.depthLeft <= depthleft) {
            return storedScoreAndDepth.score;
        }
        if (depthleft == 0 || game.isGameOver(board)) {
            return evaluate(game, maxPlayer);
        }
        for (Game newGame : game.generateMoves()) {
            int score;
            if (newGame.current == maxPlayer) {
                score = alphaBetaMax(newGame, alpha, beta, depthleft - 1, maxPlayer);
            } else {
                score = alphaBetaMin(newGame, alpha, beta, depthleft - 1, maxPlayer);
            }
            if (score <= alpha) {
                return alpha;
            }
            if (score < beta) {
                beta = score;
            }
        }

        TranspositionEntry transEntry = new TranspositionEntry();
        transEntry.depthLeft = depthleft;
        transEntry.score = beta;
        transpositionTable.put(hashKey, transEntry);

        return beta;
    }

    int evaluate(Game game, int maxPlayer) {
        if (maxPlayer == 1) {
            return game.getBoard().playerPoints[1] - game.getBoard().playerPoints[0];
        } else {
            return game.getBoard().playerPoints[0] - game.getBoard().playerPoints[1];
        }
    }


    public static List<Game> sortByValue(Map<Game, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

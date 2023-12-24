package ui;

import game.Game;

public interface UI {
    int determineMove(Game game);

    GameType determineGameType();

    void showBoard(Game game);


}

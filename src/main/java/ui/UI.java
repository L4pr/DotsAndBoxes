package ui;

import game.Game;

public interface UI {
    int determineMove(Game game);

    GameType determineGameType();

    void showBoard(Game game);

    String[] playerInfo();  // [username,isBot]


}

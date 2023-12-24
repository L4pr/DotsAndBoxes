import game.Client;
import ui.TUI;
import ui.UI;

import java.util.Arrays;

public class UITest {
    public static void main(String[] args) {
        UI ui = new TUI();
        System.out.println((Arrays.toString(ui.playerInfo())));
    }
}

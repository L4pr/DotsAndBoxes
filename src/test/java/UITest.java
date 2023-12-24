import game.Game;
import ui.TUI;
import ui.UI;

public class UITest {
    public static void main(String[] args) {
        UI ui = new TUI();
        System.out.println(ui.determineMove(new Game()));
    }
}

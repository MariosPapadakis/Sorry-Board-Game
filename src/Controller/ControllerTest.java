package Controller;

import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void testInitGame() {
        controller.initGame();
        assertNotNull(controller.getBoard(), "Board should be initialized");
        assertNotNull(controller.getView(), "View should be initialized");
        assertNotNull(controller.getPlayer1_r(), "Player 1 should be initialized");
        assertNotNull(controller.getPlayer2_y(), "Player 2 should be initialized");
        assertNotNull(controller.getPlayerPlaying(), "Current player should be initialized");
    }

    @Test
    void testSwapTurns() {
        controller.initGame();
        Player firstPlayer = controller.getPlayerPlaying();
        controller.swapTurns();
        Player secondPlayer = controller.getPlayerPlaying();
        assertNotEquals(firstPlayer, secondPlayer, "Current player should change after swapTurns is called");
        assertNotNull(secondPlayer, "Current player should not be null after swapTurns is called");
    }

    @Test
    void testGameplay(){
        controller.initGame();
        controller.gameplay();
        assertNotNull(controller.getBoard(), "Board should be initialized");
        assertNotNull(controller.getView(), "View should be initialized");
        assertNotNull(controller.getPlayer1_r(), "Player 1 should be initialized");
        assertNotNull(controller.getPlayer2_y(), "Player 2 should be initialized");
        assertNotNull(controller.getPlayerPlaying(), "Current player should be initialized");
    }

}
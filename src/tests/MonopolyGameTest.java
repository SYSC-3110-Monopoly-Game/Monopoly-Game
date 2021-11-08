package tests;

import Model.MonopolyGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonopolyGameTest {

    MonopolyGame game;

    @BeforeEach
    void setUp() {
        game = new MonopolyGame();
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    public void getBoard(){
        Assertions.assertEquals(game.board, game.getBoard());
    }

    @Test
    public void playerWin() {
        Assertions.assertEquals(false, game.playerWin());
    }

    @Test
    public void getWinner() {
        Assertions.assertEquals(null, game.getWinner());
    }

    @Test
    public void checkBankrupt() {
        boolean result = game.checkBankrupt(game.players.get(0), game.players);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void ifInJail() {
        Assertions.assertEquals(false, game.ifInJail(game.players.get(0)));
    }
}
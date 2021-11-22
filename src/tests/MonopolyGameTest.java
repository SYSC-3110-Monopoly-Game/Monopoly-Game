package tests;

import Model.MonopolyGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void buySquare() {
        game.getPlayerInTurn().setCurrentLocation(MonopolyGame.board.getSquares()[2]);
        game.buySquare();
        Assertions.assertEquals("Baltic Avenue", game.getPlayerInTurn().getProperties().get(0).getName());
    }

    @Test
    void playRound() {
        game.playRound();
        Assertions.assertNotEquals(MonopolyGame.board.startingSquare(), game.getPlayerInTurn().getCurrentLocation());
    }

    @Test
    void getPlayerInTurn() {
        Assertions.assertEquals(game.players.get(0), game.getPlayerInTurn());
    }

    @Test
    void nextTurn() {
        game.nextTurn();
        Assertions.assertEquals(game.players.get(1), game.getPlayerInTurn());
    }

    @Test
    void removeBankruptPlayer() {
        game.removeBankruptPlayer(game.getPlayerInTurn());
        Assertions.assertEquals(3, game.players.size());
    }

    @Test
    void setANDgetDoubleCounter() {
        game.setDoubleCounter(2);
        Assertions.assertEquals(2, game.getDoubleCounter());
    }

    void getPlayerNotInTurn() {
        Assertions.assertEquals(3, game.getPlayersNotInTurn().size());
    }
}
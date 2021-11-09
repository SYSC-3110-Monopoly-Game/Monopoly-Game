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
    public void getWinner() {
        assertNull(game.getWinner());
    }

    @Test
    void buySquare() {
        game.getPlayerInTurn().setCurrentLocation(game.board.getSquares()[2]);
        game.buySquare();
        Assertions.assertEquals("Baltic Avenue", game.getPlayerInTurn().getProperties().get(0).getName());
    }

    @Test
    void sellSquare() {
        game.getPlayerInTurn().setCurrentLocation(game.board.getSquares()[2]);
        game.buySquare();
        game.sellSquare();
        Assertions.assertEquals(0, game.getPlayerInTurn().getProperties().size());
    }

    @Test
    void playRound() {
        game.playRound();
        Assertions.assertNotEquals(game.board.startingSquare(), game.getPlayerInTurn().getCurrentLocation());
    }

    @Test
    void printPlayersInfo() {

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
}
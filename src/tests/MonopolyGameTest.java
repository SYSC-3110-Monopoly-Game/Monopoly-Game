package tests;

import Model.MonopolyGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class MonopolyGameTest {

    MonopolyGame game;

    @BeforeEach
    void setUp() throws ParserConfigurationException, IOException, SAXException {
        game = new MonopolyGame("NewGame.xml");
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void buySquare() throws ParserConfigurationException, SAXException, IOException {
        game.getPlayerInTurn().setCurrentLocation(MonopolyGame.board.getSquares()[2]);
        game.buySquare();
        Assertions.assertEquals("Baltic Avenue", game.getPlayerInTurn().getProperties().get(0).getName());
    }

    @Test
    void playRound() throws ParserConfigurationException, SAXException, IOException {
        game.playRound();
        Assertions.assertNotEquals(MonopolyGame.board.startingSquare(), game.getPlayerInTurn().getCurrentLocation());
    }

    @Test
    void getPlayerInTurn() {
        Assertions.assertEquals(game.players.get(0), game.getPlayerInTurn());
    }

    @Test
    void nextTurn() throws ParserConfigurationException, SAXException, IOException {
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

    @Test
    void getPlayerNotInTurn() {
        Assertions.assertEquals(3, game.getPlayersNotInTurn().size());
    }

    @Test
    void exportXML() {
        /*game.playRound();
        game.buySquare();*/
        game.exportGameToXML("testSaveFile.xml");
    }

    @Test
    void loadXML() throws ParserConfigurationException, IOException, SAXException {
        MonopolyGame game = new MonopolyGame("testSaveFile.xml");
        game.printPlayersInfo();
        game = null;
    }
}
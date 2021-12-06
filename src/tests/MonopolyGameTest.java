package tests;

import Model.AIPlayer;
import Model.MonopolyGame;
import Model.Square;
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
        game.getPlayerInTurn().setCurrentLocation(game.board.getSquares()[2]);
        game.buySquare();
        Assertions.assertEquals("Baltic Avenue", game.getPlayerInTurn().getProperties().get(0).getName());
    }

    @Test
    void playRound() throws ParserConfigurationException, SAXException, IOException {
        game.playRound();
        Assertions.assertNotEquals(game.board.startingSquare(), game.getPlayerInTurn().getCurrentLocation());
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
    void testLoadGame() {
        Square[] map = game.board.getSquares();
        Assertions.assertEquals(game.players.size(), 4);
        for (int i = 0; i < game.players.size(); i++) {
            if (i < 2) {
                Assertions.assertFalse( game.players.get(i) instanceof AIPlayer);
            } else {
                Assertions.assertTrue( game.players.get(i) instanceof AIPlayer);
            }
        }
        Assertions.assertEquals(map.length, 34);
        for (int i = 0; i < map.length; i++) {
            Assertions.assertEquals( map[i].getNumber(), i);
        }
    }

    @Test
    void testExportGameAndLoadGame() throws ParserConfigurationException, IOException, SAXException {
        game.exportGameToXML("testUseFile.xml");
        MonopolyGame game2 = new MonopolyGame("testUseFile.xml");
        Assertions.assertEquals(game.players.size(), game2.players.size());
        for (int i = 0; i < game.players.size(); i++) {
            Assertions.assertEquals(game.players.get(i).getName(), game2.players.get(i).getName());
        }
        Assertions.assertEquals(game.board.getSquares().length, game2.board.getSquares().length);
        for (int i = 0; i < game.board.getSquares().length; i++) {
            Assertions.assertEquals(game.board.getSquareAt(i).getName(), game2.board.getSquareAt(i).getName());
            Assertions.assertEquals(game.board.getSquareAt(i).getNumber(), game2.board.getSquareAt(i).getNumber());
        }
    }
}
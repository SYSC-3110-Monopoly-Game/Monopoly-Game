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
}
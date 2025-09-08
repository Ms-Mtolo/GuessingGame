/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package guessingnumbergame;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DOD
 */
public class GuessingGameTest {
   
     private GuessingGame game;

    @Before
    public void setUp() {
        game = new GuessingGame();
        game.setNumberToGuess(50); // Fix the number for predictable tests
    }

    @Test
    public void testCorrectGuess() {
        game.guess(50);
        assertTrue("Player should win with the correct guess", game.hasWon());
        assertFalse("Game should stop after correct guess", game.shouldContinue());
        assertEquals("Congratulations! You've guessed the number.", game.getFeedback());
    }

    @Test
    public void testGuessTooLow() {
        game.guess(25);
        assertFalse("Player should not win with low guess", game.hasWon());
        assertTrue("Game should continue after wrong guess", game.shouldContinue());
    }

    @Test
    public void testGuessTooHigh() {
        game.guess(75);
        assertFalse("Player should not win with high guess", game.hasWon());
        assertTrue("Game should continue after wrong guess", game.shouldContinue());
    }

    @Test
    public void testGameOverAfterMaxGuesses() {
        // Make 10 wrong guesses
        for (int i = 0; i < 10; i++) {
            game.guess(25);
        }
        assertFalse("Player should lose after 10 wrong guesses", game.hasWon());
        assertFalse("Game should not continue after max guesses", game.shouldContinue());
        assertEquals("Game over! The number was 50.", game.getFeedback());
    }

    @Test
    public void testGuessesStoredCorrectly() {
        game.guess(25);
        game.guess(75);
        int[] guesses = game.getGuesses();
        assertEquals("First guess should be stored", 25, guesses[0]);
        assertEquals("Second guess should be stored", 75, guesses[1]);
    }

    @Test
    public void testNumberOfTriesIncrements() {
        game.guess(25);
        game.guess(75);
        assertEquals("Number of tries should be 2", 2, game.getNumberOfTries());
    }
}


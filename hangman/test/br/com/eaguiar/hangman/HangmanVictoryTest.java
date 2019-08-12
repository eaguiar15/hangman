package br.com.eaguiar.hangman;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

class HangmanVictoryTest {
	WebElement driver;

	@Test
	void testVictory() {
		Hangman hangman = new Hangman();
		assertEquals("word 'dElL' must be true", true, hangman.victory("dElL","DELL"));
		assertEquals("word 'Dell' must be true", true, hangman.victory("Dell","DELL"));
		assertEquals("word 'DELX' must be false", false, hangman.victory("DELX","DELL"));
	}

}

package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AboutCommandTest {

	@Test
	void testConstructor() {
		AboutCommand command = new AboutCommand(null);
		assertNotNull(command);
	}

	@Test
	void testImplementsCommand() {
		AboutCommand command = new AboutCommand(null);
		assertTrue(command instanceof Command);
	}
}

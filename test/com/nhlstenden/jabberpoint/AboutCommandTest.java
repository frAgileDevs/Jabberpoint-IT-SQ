package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.command.AboutCommand;
import com.nhlstenden.jabberpoint.command.Command;
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

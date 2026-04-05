package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SaveFileCommandTest {

	@Test
	void testConstructor() {
		Presentation presentation = new Presentation();
		SaveFileCommand command = new SaveFileCommand(presentation, null);
		assertNotNull(command);
	}

	@Test
	void testImplementsCommand() {
		Presentation presentation = new Presentation();
		SaveFileCommand command = new SaveFileCommand(presentation, null);
		assertTrue(command instanceof Command);
	}
}

package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GoToSlideCommandTest {

	@Test
	void testConstructor() {
		Presentation presentation = new Presentation();
		GoToSlideCommand command = new GoToSlideCommand(presentation, null);
		assertNotNull(command);
	}

	@Test
	void testImplementsCommand() {
		Presentation presentation = new Presentation();
		GoToSlideCommand command = new GoToSlideCommand(presentation, null);
		assertTrue(command instanceof Command);
	}
}

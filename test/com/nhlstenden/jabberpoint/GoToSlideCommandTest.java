package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GoToSlideCommandTest {

	@Test
	void testPageNumberPromptConstant() {
		assertEquals("Page number?", GoToSlideCommand.PAGENR);
	}

	@Test
	void testConstructorStoresPresentation() {
		Presentation presentation = new Presentation();
		GoToSlideCommand command = new GoToSlideCommand(presentation, null);
		assertNotNull(command);
		assertTrue(command instanceof Command);
	}
}

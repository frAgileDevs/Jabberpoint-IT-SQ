package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExitCommandTest {

	@Test
	void testExecuteCallsExitOnPresentation() {
		Presentation mockPresentation = mock(Presentation.class);
		ExitCommand command = new ExitCommand(mockPresentation);
		command.execute();
		verify(mockPresentation).exit(0);
	}

	@Test
	void testImplementsCommand() {
		Presentation mockPresentation = mock(Presentation.class);
		ExitCommand command = new ExitCommand(mockPresentation);
		assertTrue(command instanceof Command);
	}
}

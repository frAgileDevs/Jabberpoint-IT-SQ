package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.command.Command;
import com.nhlstenden.jabberpoint.command.ExitCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {

	/** A test double that overrides exit() to avoid killing the JVM. */
	private static class TestablePresentation extends Presentation {
		int exitCalledWithCode = -1;

		@Override
		public void exit(int n) {
			exitCalledWithCode = n;
		}
	}

	@Test
	void testExecuteCallsExitWithZero() {
		TestablePresentation presentation = new TestablePresentation();
		ExitCommand command = new ExitCommand(presentation);
		command.execute();
		assertEquals(0, presentation.exitCalledWithCode);
	}

	@Test
	void testImplementsCommand() {
		TestablePresentation presentation = new TestablePresentation();
		ExitCommand command = new ExitCommand(presentation);
		assertTrue(command instanceof Command);
	}
}

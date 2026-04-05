package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.command.Command;
import com.nhlstenden.jabberpoint.command.OpenFileCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpenFileCommandTest {

	@Test
	void testConstructor() {
		Presentation presentation = new Presentation();
		OpenFileCommand command = new OpenFileCommand(presentation, null);
		assertNotNull(command);
	}

	@Test
	void testImplementsCommand() {
		Presentation presentation = new Presentation();
		OpenFileCommand command = new OpenFileCommand(presentation, null);
		assertTrue(command instanceof Command);
	}
}

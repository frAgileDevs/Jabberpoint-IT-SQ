package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.command.Command;
import com.nhlstenden.jabberpoint.command.SaveFileCommand;
import com.nhlstenden.jabberpoint.slide.TextItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SaveFileCommandTest {

	@Test
	void testImplementsCommand() {
		Presentation presentation = new Presentation();
		SaveFileCommand command = new SaveFileCommand(presentation, null);
		assertTrue(command instanceof Command);
	}

	@Test
	void testSaveFileConstant() {
		assertEquals("dump.xml", SaveFileCommand.SAVEFILE);
	}

	@Test
	void testErrorMessageConstants() {
		assertEquals("IO Exception: ", SaveFileCommand.IOEX);
		assertEquals("Save Error", SaveFileCommand.SAVEERR);
	}

	@Test
	void testExecuteSavesFile() {
		Presentation presentation = new Presentation();
		presentation.setTitle("Save Test");
		com.nhlstenden.jabberpoint.slide.Slide slide = new com.nhlstenden.jabberpoint.slide.Slide();
		slide.setTitle("Test");
		slide.append(new TextItem(1, "Hello"));
		presentation.append(slide);

		java.awt.Frame frame = new java.awt.Frame();
		SaveFileCommand command = new SaveFileCommand(presentation, frame);
		// execute() saves to dump.xml, should not throw
		assertDoesNotThrow(() -> command.execute());
		frame.dispose();
	}
}

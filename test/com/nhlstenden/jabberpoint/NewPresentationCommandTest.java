package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Frame;

class NewPresentationCommandTest {

	@Test
	void testConstructor() {
		Presentation presentation = new Presentation();
		NewPresentationCommand command = new NewPresentationCommand(presentation, null);
		assertNotNull(command);
	}

	@Test
	void testImplementsCommand() {
		Presentation presentation = new Presentation();
		NewPresentationCommand command = new NewPresentationCommand(presentation, null);
		assertTrue(command instanceof Command);
	}

	@Test
	void testExecuteClearsPresentation() {
		Presentation presentation = new Presentation();
		Frame mockFrame = mock(Frame.class);
		NewPresentationCommand command = new NewPresentationCommand(presentation, mockFrame);

		com.nhlstenden.jabberpoint.slide.Slide slide = new com.nhlstenden.jabberpoint.slide.Slide();
		presentation.append(slide);
		assertEquals(1, presentation.getSize());

		command.execute();

		assertEquals(0, presentation.getSize());
		verify(mockFrame).repaint();
	}
}

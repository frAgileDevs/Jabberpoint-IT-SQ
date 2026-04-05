package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.command.NewPresentationCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.nhlstenden.jabberpoint.slide.Slide;
import java.awt.Frame;

class NewPresentationCommandTest {

	@Test
	void testExecuteClearsPresentation() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		assertEquals(2, presentation.getSize());

		Frame frame = new Frame();
		NewPresentationCommand command = new NewPresentationCommand(presentation, frame);
		command.execute();

		assertEquals(0, presentation.getSize());
		frame.dispose();
	}

	@Test
	void testExecuteResetsSlideNumber() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(1);

		Frame frame = new Frame();
		NewPresentationCommand command = new NewPresentationCommand(presentation, frame);
		command.execute();

		assertEquals(-1, presentation.getSlideNumber());
		frame.dispose();
	}

	@Test
	void testExecuteOnAlreadyEmptyPresentation() {
		Presentation presentation = new Presentation();
		Frame frame = new Frame();
		NewPresentationCommand command = new NewPresentationCommand(presentation, frame);
		command.execute();

		assertEquals(0, presentation.getSize());
		frame.dispose();
	}
}

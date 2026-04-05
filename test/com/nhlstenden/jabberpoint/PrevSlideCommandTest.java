package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.command.PrevSlideCommand;
import com.nhlstenden.jabberpoint.slide.Slide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrevSlideCommandTest {

	@Test
	void testExecuteGoesBackOneSlide() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(1);

		PrevSlideCommand command = new PrevSlideCommand(presentation);
		command.execute();

		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testExecuteDoesNotGoBelowZero() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		PrevSlideCommand command = new PrevSlideCommand(presentation);
		command.execute();

		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testExecuteMultipleTimes() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(2);

		PrevSlideCommand command = new PrevSlideCommand(presentation);
		command.execute();
		command.execute();

		assertEquals(0, presentation.getSlideNumber());
	}
}

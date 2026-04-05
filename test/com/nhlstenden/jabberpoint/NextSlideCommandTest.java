package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.command.NextSlideCommand;
import com.nhlstenden.jabberpoint.slide.Slide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NextSlideCommandTest {

	@Test
	void testExecuteAdvancesOneSlide() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		NextSlideCommand command = new NextSlideCommand(presentation);
		command.execute();

		assertEquals(1, presentation.getSlideNumber());
	}

	@Test
	void testExecuteDoesNotGoPastLastSlide() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		NextSlideCommand command = new NextSlideCommand(presentation);
		command.execute();

		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testExecuteMultipleTimes() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		NextSlideCommand command = new NextSlideCommand(presentation);
		command.execute();
		command.execute();

		assertEquals(2, presentation.getSlideNumber());
	}
}

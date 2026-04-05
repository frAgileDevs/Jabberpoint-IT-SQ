package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.Slide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NextSlideCommandTest {

	@Test
	void testExecuteCallsNextSlide() {
		Presentation mockPresentation = mock(Presentation.class);
		NextSlideCommand command = new NextSlideCommand(mockPresentation);
		command.execute();
		verify(mockPresentation).nextSlide();
	}

	@Test
	void testExecuteAdvancesSlide() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		NextSlideCommand command = new NextSlideCommand(presentation);
		command.execute();

		assertEquals(1, presentation.getSlideNumber());
	}

	@Test
	void testImplementsCommand() {
		Presentation presentation = new Presentation();
		NextSlideCommand command = new NextSlideCommand(presentation);
		assertTrue(command instanceof Command);
	}
}

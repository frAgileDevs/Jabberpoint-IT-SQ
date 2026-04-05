package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.Slide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrevSlideCommandTest {

	@Test
	void testExecuteCallsPrevSlide() {
		Presentation mockPresentation = mock(Presentation.class);
		PrevSlideCommand command = new PrevSlideCommand(mockPresentation);
		command.execute();
		verify(mockPresentation).prevSlide();
	}

	@Test
	void testExecuteGoesBack() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(1);

		PrevSlideCommand command = new PrevSlideCommand(presentation);
		command.execute();

		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testImplementsCommand() {
		Presentation presentation = new Presentation();
		PrevSlideCommand command = new PrevSlideCommand(presentation);
		assertTrue(command instanceof Command);
	}
}

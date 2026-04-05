package com.nhlstenden.jabberpoint.controller;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.slide.Slide;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KeyControllerTest {

	@Test
	void testConstructor() {
		Presentation presentation = new Presentation();
		KeyController controller = new KeyController(presentation);
		assertNotNull(controller);
	}

	@Test
	void testPageDownAdvancesSlide() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		KeyController controller = new KeyController(presentation);
		KeyEvent event = new KeyEvent(
			new java.awt.Component() {},
			KeyEvent.KEY_PRESSED,
			System.currentTimeMillis(),
			0,
			KeyEvent.VK_PAGE_DOWN,
			KeyEvent.CHAR_UNDEFINED
		);
		controller.keyPressed(event);
		assertEquals(1, presentation.getSlideNumber());
	}

	@Test
	void testPageUpGoesBack() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(1);

		KeyController controller = new KeyController(presentation);
		KeyEvent event = new KeyEvent(
			new java.awt.Component() {},
			KeyEvent.KEY_PRESSED,
			System.currentTimeMillis(),
			0,
			KeyEvent.VK_PAGE_UP,
			KeyEvent.CHAR_UNDEFINED
		);
		controller.keyPressed(event);
		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testDownArrowAdvancesSlide() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(0);

		KeyController controller = new KeyController(presentation);
		KeyEvent event = new KeyEvent(
			new java.awt.Component() {},
			KeyEvent.KEY_PRESSED,
			System.currentTimeMillis(),
			0,
			KeyEvent.VK_DOWN,
			KeyEvent.CHAR_UNDEFINED
		);
		controller.keyPressed(event);
		assertEquals(1, presentation.getSlideNumber());
	}

	@Test
	void testUpArrowGoesBack() {
		Presentation presentation = new Presentation();
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(1);

		KeyController controller = new KeyController(presentation);
		KeyEvent event = new KeyEvent(
			new java.awt.Component() {},
			KeyEvent.KEY_PRESSED,
			System.currentTimeMillis(),
			0,
			KeyEvent.VK_UP,
			KeyEvent.CHAR_UNDEFINED
		);
		controller.keyPressed(event);
		assertEquals(0, presentation.getSlideNumber());
	}
}

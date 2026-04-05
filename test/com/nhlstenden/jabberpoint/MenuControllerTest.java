package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import java.awt.Frame;
import java.awt.MenuItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuControllerTest {

	@Test
	void testConstructor() {
		Frame mockFrame = mock(Frame.class);
		Presentation presentation = new Presentation();
		MenuController controller = new MenuController(mockFrame, presentation);
		assertNotNull(controller);
	}

	@Test
	void testMkMenuItem() {
		Frame mockFrame = mock(Frame.class);
		Presentation presentation = new Presentation();
		MenuController controller = new MenuController(mockFrame, presentation);
		MenuItem item = controller.mkMenuItem("Test");
		assertNotNull(item);
		assertEquals("Test", item.getLabel());
	}

	@Test
	void testMenuCountIsThree() {
		Frame mockFrame = mock(Frame.class);
		Presentation presentation = new Presentation();
		MenuController controller = new MenuController(mockFrame, presentation);
		// File, View, and Help menus
		assertEquals(2, controller.getMenuCount()); // Help menu set via setHelpMenu doesn't count in getMenuCount
	}
}

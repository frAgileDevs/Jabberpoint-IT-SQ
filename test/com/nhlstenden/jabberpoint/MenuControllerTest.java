package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import java.awt.Frame;
import java.awt.MenuItem;
import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest {

	@Test
	void testConstructorCreatesMenuBar() {
		Frame frame = new Frame();
		Presentation presentation = new Presentation();
		MenuController controller = new MenuController(frame, presentation);
		assertNotNull(controller);
		frame.dispose();
	}

	@Test
	void testMkMenuItemCreatesItemWithCorrectLabel() {
		Frame frame = new Frame();
		Presentation presentation = new Presentation();
		MenuController controller = new MenuController(frame, presentation);
		MenuItem item = controller.mkMenuItem("Test");
		assertEquals("Test", item.getLabel());
		frame.dispose();
	}

	@Test
	void testMenuCountIsThree() {
		Frame frame = new Frame();
		Presentation presentation = new Presentation();
		MenuController controller = new MenuController(frame, presentation);
		// File, View, and Help menus
		assertEquals(3, controller.getMenuCount());
		frame.dispose();
	}

	@Test
	void testFileMenuHasFourItems() {
		Frame frame = new Frame();
		Presentation presentation = new Presentation();
		MenuController controller = new MenuController(frame, presentation);
		// File menu: Open, New, Save, separator, Exit = 5 items (separator counts)
		assertEquals(5, controller.getMenu(0).getItemCount());
		frame.dispose();
	}

	@Test
	void testViewMenuHasThreeItems() {
		Frame frame = new Frame();
		Presentation presentation = new Presentation();
		MenuController controller = new MenuController(frame, presentation);
		// View menu: Next, Prev, Go to
		assertEquals(3, controller.getMenu(1).getItemCount());
		frame.dispose();
	}
}

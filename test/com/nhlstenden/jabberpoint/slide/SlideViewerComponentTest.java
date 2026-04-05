package com.nhlstenden.jabberpoint.slide;

import com.nhlstenden.jabberpoint.Presentation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlideViewerComponentTest {

	@Test
	void testConstructor() {
		Presentation presentation = new Presentation();
		javax.swing.JFrame frame = new javax.swing.JFrame();
		SlideViewerComponent component = new SlideViewerComponent(presentation, frame);
		assertNotNull(component);
		frame.dispose();
	}

	@Test
	void testGetPreferredSize() {
		Presentation presentation = new Presentation();
		javax.swing.JFrame frame = new javax.swing.JFrame();
		SlideViewerComponent component = new SlideViewerComponent(presentation, frame);
		java.awt.Dimension size = component.getPreferredSize();
		assertNotNull(size);
		assertTrue(size.width > 0);
		assertTrue(size.height > 0);
		frame.dispose();
	}
}

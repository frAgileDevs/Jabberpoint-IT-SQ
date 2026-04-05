package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DemoPresentationTest {

	@Test
	void testLoadFilePopulatesPresentation() {
		Presentation presentation = new Presentation();
		DemoPresentation demo = new DemoPresentation();
		demo.loadFile(presentation, "unused");
		assertEquals("Demo Presentation", presentation.getTitle());
		assertTrue(presentation.getSize() > 0);
	}

	@Test
	void testLoadFileCreatesThreeSlides() {
		Presentation presentation = new Presentation();
		DemoPresentation demo = new DemoPresentation();
		demo.loadFile(presentation, null);
		assertEquals(3, presentation.getSize());
	}

	@Test
	void testSaveFileThrowsException() {
		Presentation presentation = new Presentation();
		DemoPresentation demo = new DemoPresentation();
		assertThrows(IllegalStateException.class, () -> {
			demo.saveFile(presentation, "test");
		});
	}

	@Test
	void testGetDemoAccessor() {
		Accessor accessor = Accessor.getDemoAccessor();
		assertNotNull(accessor);
	}
}

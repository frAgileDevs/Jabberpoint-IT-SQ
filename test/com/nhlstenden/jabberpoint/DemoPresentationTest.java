package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.accessor.DemoPresentation;
import com.nhlstenden.jabberpoint.accessor.PresentationLoader;
import com.nhlstenden.jabberpoint.accessor.PresentationWriter;
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
	void testDemoPresentationIsLoaderOnly() {
		DemoPresentation demo = new DemoPresentation();
		// It can be loaded...
		assertTrue(demo instanceof PresentationLoader);
		// ...but deliberately cannot be saved (Interface Segregation), so it is
		// always substitutable for the type it implements (Liskov).
		assertFalse(demo instanceof PresentationWriter);
	}
}

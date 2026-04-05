package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest {

	@Test
	void testConstructor() {
		XMLAccessor accessor = new XMLAccessor();
		assertNotNull(accessor);
	}

	@Test
	void testExtendsAccessor() {
		XMLAccessor accessor = new XMLAccessor();
		assertTrue(accessor instanceof Accessor);
	}

	@Test
	void testLoadFileWithValidFile() throws IOException {
		Presentation presentation = new Presentation();
		XMLAccessor accessor = new XMLAccessor();
		accessor.loadFile(presentation, "test.xml");
		assertTrue(presentation.getSize() > 0);
		assertNotNull(presentation.getTitle());
	}

	@Test
	void testLoadFileWithInvalidFileLoadsNothing() throws IOException {
		Presentation presentation = new Presentation();
		XMLAccessor accessor = new XMLAccessor();
		// loadFile handles the error internally, so we just verify no slides are loaded
		try {
			accessor.loadFile(presentation, "nonexistent.xml");
		} catch (Exception e) {
			// Some implementations may throw, some may not
		}
		assertEquals(0, presentation.getSize());
	}
}

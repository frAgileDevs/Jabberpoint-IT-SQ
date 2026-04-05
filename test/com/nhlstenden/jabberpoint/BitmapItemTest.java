package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BitmapItemTest {

	@Test
	void testConstructorWithNonexistentFile() {
		// BitmapItem catches IOException internally and prints to stderr
		BitmapItem item = new BitmapItem(1, "nonexistent.png");
		assertNotNull(item);
		assertEquals(1, item.getLevel());
		assertEquals("nonexistent.png", item.getName());
	}

	@Test
	void testDefaultConstructorThrowsNPE() {
		// Default constructor passes null to ImageIO.read(new File(null)) which throws NPE
		assertThrows(NullPointerException.class, () -> new BitmapItem());
	}

	@Test
	void testGetName() {
		BitmapItem item = new BitmapItem(2, "image.jpg");
		assertEquals("image.jpg", item.getName());
	}

	@Test
	void testToString() {
		BitmapItem item = new BitmapItem(3, "pic.png");
		String result = item.toString();
		assertTrue(result.contains("BitmapItem"));
		assertTrue(result.contains("3"));
		assertTrue(result.contains("pic.png"));
	}
}

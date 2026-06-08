package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.BitmapItem;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class BitmapItemTest {

	@Test
	void testConstructorWithExistingFile() {
		BitmapItem item = new BitmapItem(2, "JabberPoint.gif");
		assertEquals(2, item.getLevel());
		assertEquals("JabberPoint.gif", item.getName());
	}

	@Test
	void testConstructorWithNonexistentFile() {
		// BitmapItem catches IOException internally and prints to stderr
		BitmapItem item = new BitmapItem(1, "nonexistent.png");
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
	void testGetBufferedImageForValidFile() {
		BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
		BufferedImage image = item.getBufferedImage();
		assertNotNull(image);
	}

	@Test
	void testGetBufferedImageForMissingFileIsNull() {
		BitmapItem item = new BitmapItem(1, "nonexistent.png");
		assertNull(item.getBufferedImage());
	}

	@Test
	void testToString() {
		BitmapItem item = new BitmapItem(3, "nonexistent.png");
		String result = item.toString();
		assertTrue(result.contains("BitmapItem"));
		assertTrue(result.contains("3"));
		assertTrue(result.contains("nonexistent.png"));
	}
}

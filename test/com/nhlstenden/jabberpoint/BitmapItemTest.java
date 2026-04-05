package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.BitmapItem;
import com.nhlstenden.jabberpoint.slide.utility.Style;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class BitmapItemTest {

	@BeforeAll
	static void initStyles() {
		Style.createStyles();
	}

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
	void testToString() {
		BitmapItem item = new BitmapItem(3, "nonexistent.png");
		String result = item.toString();
		assertTrue(result.contains("BitmapItem"));
		assertTrue(result.contains("3"));
		assertTrue(result.contains("nonexistent.png"));
	}

	@Test
	void testGetBoundingBoxWithValidImage() {
		BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		Rectangle box = item.getBoundingBox(g, null, 1.0f, style);
		assertNotNull(box);
		assertTrue(box.width > 0);
		assertTrue(box.height > 0);
		g.dispose();
	}

	@Test
	void testDrawWithValidImage() {
		BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		assertDoesNotThrow(() -> item.draw(0, 0, 1.0f, g, style, null));
		g.dispose();
	}
}

package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.Slide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

import static org.junit.jupiter.api.Assertions.*;

class TextItemTest {

	@BeforeAll
	static void initStyles() {
		Style.createStyles();
	}

	@Test
	void testConstructorWithParameters() {
		TextItem item = new TextItem(2, "Hello World");
		assertEquals(2, item.getLevel());
		assertEquals("Hello World", item.getText());
	}

	@Test
	void testDefaultConstructor() {
		TextItem item = new TextItem();
		assertEquals(0, item.getLevel());
		assertEquals("No Text Given", item.getText());
	}

	@Test
	void testGetTextWithNullReturnsEmpty() {
		TextItem item = new TextItem(0, null);
		assertEquals("", item.getText());
	}

	@Test
	void testToString() {
		TextItem item = new TextItem(1, "Test");
		String result = item.toString();
		assertTrue(result.contains("TextItem"));
		assertTrue(result.contains("1"));
		assertTrue(result.contains("Test"));
	}

	@Test
	void testGetAttributedString() {
		TextItem item = new TextItem(1, "Test");
		Style style = Style.getStyle(1);
		AttributedString result = item.getAttributedString(style, 1.0f);
		assertNotNull(result);
	}

	@Test
	void testGetBoundingBoxReturnsRectangle() {
		TextItem item = new TextItem(1, "Hello World");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		Rectangle box = item.getBoundingBox(g, null, 1.0f, style);
		assertNotNull(box);
		assertTrue(box.width > 0);
		assertTrue(box.height > 0);
		g.dispose();
	}

	@Test
	void testDrawDoesNotThrowForValidText() {
		TextItem item = new TextItem(1, "Hello");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		assertDoesNotThrow(() -> item.draw(0, 0, 1.0f, g, style, null));
		g.dispose();
	}

	@Test
	void testDrawWithEmptyTextDoesNotThrow() {
		TextItem item = new TextItem(1, "");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		assertDoesNotThrow(() -> item.draw(0, 0, 1.0f, g, style, null));
		g.dispose();
	}

	@Test
	void testDrawWithNullTextDoesNotThrow() {
		TextItem item = new TextItem(1, null);
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		assertDoesNotThrow(() -> item.draw(0, 0, 1.0f, g, style, null));
		g.dispose();
	}

	@Test
	void testBoundingBoxScalesWithScale() {
		TextItem item = new TextItem(1, "Hello World");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		Rectangle box1 = item.getBoundingBox(g, null, 1.0f, style);
		Rectangle box2 = item.getBoundingBox(g, null, 0.5f, style);
		// smaller scale should produce smaller or equal bounding box
		assertTrue(box2.height <= box1.height);
		g.dispose();
	}
}

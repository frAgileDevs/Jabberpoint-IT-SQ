package com.nhlstenden.jabberpoint.slide.renderer;

import com.nhlstenden.jabberpoint.slide.Slide;
import com.nhlstenden.jabberpoint.slide.TextItem;
import com.nhlstenden.jabberpoint.slide.utility.Style;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

import static org.junit.jupiter.api.Assertions.*;

class TextItemRendererTest {

	private final TextItemRenderer renderer = new TextItemRenderer();

	@BeforeAll
	static void initStyles() {
		Style.createStyles();
	}

	@Test
	void testGetAttributedString() {
		TextItem item = new TextItem(1, "Test");
		Style style = Style.getStyle(1);
		AttributedString result = renderer.getAttributedString(item, style, 1.0f);
		assertNotNull(result);
	}

	@Test
	void testGetBoundingBoxReturnsRectangle() {
		TextItem item = new TextItem(1, "Hello World");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		Rectangle box = renderer.getBoundingBox(item, g, null, 1.0f, style);
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
		assertDoesNotThrow(() -> renderer.draw(item, 0, 0, 1.0f, g, style, null));
		g.dispose();
	}

	@Test
	void testDrawWithEmptyTextDoesNotThrow() {
		TextItem item = new TextItem(1, "");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		assertDoesNotThrow(() -> renderer.draw(item, 0, 0, 1.0f, g, style, null));
		g.dispose();
	}

	@Test
	void testDrawWithNullTextDoesNotThrow() {
		TextItem item = new TextItem(1, null);
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		assertDoesNotThrow(() -> renderer.draw(item, 0, 0, 1.0f, g, style, null));
		g.dispose();
	}

	@Test
	void testBoundingBoxScalesWithScale() {
		TextItem item = new TextItem(1, "Hello World");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		Rectangle box1 = renderer.getBoundingBox(item, g, null, 1.0f, style);
		Rectangle box2 = renderer.getBoundingBox(item, g, null, 0.5f, style);
		// smaller scale should produce smaller or equal bounding box
		assertTrue(box2.height <= box1.height);
		g.dispose();
	}
}

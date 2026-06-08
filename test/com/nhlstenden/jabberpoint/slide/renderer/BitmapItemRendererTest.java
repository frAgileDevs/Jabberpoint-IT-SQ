package com.nhlstenden.jabberpoint.slide.renderer;

import com.nhlstenden.jabberpoint.slide.BitmapItem;
import com.nhlstenden.jabberpoint.slide.utility.Style;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class BitmapItemRendererTest {

	private final BitmapItemRenderer renderer = new BitmapItemRenderer();

	@BeforeAll
	static void initStyles() {
		Style.createStyles();
	}

	@Test
	void testGetBoundingBoxWithValidImage() {
		BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
		Style style = Style.getStyle(1);
		BufferedImage img = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		Rectangle box = renderer.getBoundingBox(item, g, null, 1.0f, style);
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
		assertDoesNotThrow(() -> renderer.draw(item, 0, 0, 1.0f, g, style, null));
		g.dispose();
	}
}

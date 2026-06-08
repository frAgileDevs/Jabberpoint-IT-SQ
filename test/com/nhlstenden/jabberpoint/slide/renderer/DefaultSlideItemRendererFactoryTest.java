package com.nhlstenden.jabberpoint.slide.renderer;

import com.nhlstenden.jabberpoint.slide.BitmapItem;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import com.nhlstenden.jabberpoint.slide.TextItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSlideItemRendererFactoryTest {

	private final DefaultSlideItemRendererFactory factory = new DefaultSlideItemRendererFactory();

	@Test
	void testReturnsTextRendererForTextItem() {
		SlideItemRenderer renderer = factory.getRenderer(new TextItem(1, "x"));
		assertTrue(renderer instanceof TextItemRenderer);
	}

	@Test
	void testReturnsBitmapRendererForBitmapItem() {
		SlideItemRenderer renderer = factory.getRenderer(new BitmapItem(1, "nonexistent.png"));
		assertTrue(renderer instanceof BitmapItemRenderer);
	}

	@Test
	void testThrowsForUnregisteredItemType() {
		SlideItem unknown = new SlideItem(1) {
		};
		assertThrows(IllegalArgumentException.class, () -> factory.getRenderer(unknown));
	}

	@Test
	void testCustomRendererCanBeRegistered() {
		SlideItemRenderer custom = new TextItemRenderer();
		factory.registerRenderer(TextItem.class, custom);
		assertSame(custom, factory.getRenderer(new TextItem(1, "x")));
	}
}

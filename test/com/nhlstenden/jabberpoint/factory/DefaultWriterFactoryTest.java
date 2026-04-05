package com.nhlstenden.jabberpoint.factory;

import com.nhlstenden.jabberpoint.BitmapItem;
import com.nhlstenden.jabberpoint.TextItem;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DefaultWriterFactoryTest {

	@Test
	void testConstructor() {
		DefaultWriterFactory factory = new DefaultWriterFactory();
		assertNotNull(factory);
	}

	@Test
	void testGetWrittenTextItem() {
		DefaultWriterFactory factory = new DefaultWriterFactory();
		TextItem textItem = new TextItem(1, "Hello");
		String result = factory.getWrittenTextItem(textItem, 1);
		assertTrue(result.contains("text"));
		assertTrue(result.contains("Hello"));
		assertTrue(result.contains("1"));
	}

	@Test
	void testGetWrittenBitmapItem() {
		DefaultWriterFactory factory = new DefaultWriterFactory();
		BitmapItem bitmapItem = new BitmapItem(2, "image.png");
		String result = factory.getWrittenBitmapItem(bitmapItem, 2);
		assertTrue(result.contains("image"));
		assertTrue(result.contains("image.png"));
		assertTrue(result.contains("2"));
	}

	@Test
	void testGetSlideItemToWriteWithTextItem() {
		DefaultWriterFactory factory = new DefaultWriterFactory();
		TextItem textItem = new TextItem(1, "Test");
		String result = factory.getSlideItemToWrite(textItem, 1);
		assertTrue(result.contains("text"));
		assertTrue(result.contains("Test"));
	}

	@Test
	void testGetSlideItemToWriteWithBitmapItem() {
		DefaultWriterFactory factory = new DefaultWriterFactory();
		BitmapItem bitmapItem = new BitmapItem(1, "pic.png");
		String result = factory.getSlideItemToWrite(bitmapItem, 1);
		assertTrue(result.contains("image"));
		assertTrue(result.contains("pic.png"));
	}

	@Test
	void testParameterNullCheckThrowsOnNull() {
		DefaultWriterFactory factory = new DefaultWriterFactory();
		assertThrows(RuntimeException.class, () -> {
			factory.parameterNullCheck(null);
		});
	}

	@Test
	void testParameterNullCheckDoesNotThrowOnValidItem() {
		DefaultWriterFactory factory = new DefaultWriterFactory();
		TextItem item = new TextItem(1, "Test");
		assertDoesNotThrow(() -> factory.parameterNullCheck(item));
	}

	@Test
	void testGetSlideItemToWriteNullThrows() {
		DefaultWriterFactory factory = new DefaultWriterFactory();
		assertThrows(RuntimeException.class, () -> {
			factory.getSlideItemToWrite(null, 1);
		});
	}
}

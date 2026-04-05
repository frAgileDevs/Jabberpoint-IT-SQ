package com.nhlstenden.jabberpoint.slide;

import com.nhlstenden.jabberpoint.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlideTest {

	private Slide slide;

	@BeforeEach
	void setUp() {
		slide = new Slide();
	}

	@Test
	void testDefaultConstructor() {
		assertNotNull(slide);
		assertEquals(0, slide.getSize());
		assertNull(slide.getTitle());
	}

	@Test
	void testSetAndGetTitle() {
		slide.setTitle("Test Slide");
		assertEquals("Test Slide", slide.getTitle());
	}

	@Test
	void testAppendSlideItem() {
		TextItem item = new TextItem(1, "Test");
		slide.append(item);
		assertEquals(1, slide.getSize());
		assertEquals(item, slide.getSlideItem(0));
	}

	@Test
	void testAppendByLevelAndMessage() {
		slide.append(2, "Level 2 text");
		assertEquals(1, slide.getSize());
		SlideItem item = slide.getSlideItem(0);
		assertTrue(item instanceof TextItem);
		assertEquals(2, item.getLevel());
	}

	@Test
	void testGetSlideItems() {
		slide.append(new TextItem(1, "A"));
		slide.append(new TextItem(2, "B"));
		assertEquals(2, slide.getSlideItems().size());
	}

	@Test
	void testWidthConstant() {
		assertEquals(1200, Slide.WIDTH);
	}

	@Test
	void testHeightConstant() {
		assertEquals(800, Slide.HEIGHT);
	}

	@Test
	void testMultipleAppends() {
		for (int i = 0; i < 5; i++) {
			slide.append(i, "Item " + i);
		}
		assertEquals(5, slide.getSize());
	}
}

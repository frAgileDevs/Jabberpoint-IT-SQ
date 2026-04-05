package com.nhlstenden.jabberpoint.slide;

import com.nhlstenden.jabberpoint.TextItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SlideItemTest {

	@Test
	void testGetLevelFromTextItem() {
		TextItem item = new TextItem(3, "Test");
		assertEquals(3, item.getLevel());
	}

	@Test
	void testDefaultLevelIsZero() {
		TextItem item = new TextItem();
		assertEquals(0, item.getLevel());
	}

	@Test
	void testDifferentLevels() {
		for (int level = 0; level <= 4; level++) {
			TextItem item = new TextItem(level, "Level " + level);
			assertEquals(level, item.getLevel());
		}
	}
}

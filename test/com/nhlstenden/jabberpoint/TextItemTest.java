package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.TextItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextItemTest {

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
}

package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.awt.Font;
import static org.junit.jupiter.api.Assertions.*;

class StyleTest {

	@BeforeEach
	void setUp() {
		Style.createStyles();
	}

	@Test
	void testCreateStylesInitializesStyles() {
		assertNotNull(Style.getStyle(0));
		assertNotNull(Style.getStyle(1));
		assertNotNull(Style.getStyle(2));
		assertNotNull(Style.getStyle(3));
		assertNotNull(Style.getStyle(4));
	}

	@Test
	void testGetStyleClampsToMaxLevel() {
		Style style4 = Style.getStyle(4);
		Style styleBeyond = Style.getStyle(10);
		assertEquals(style4, styleBeyond);
	}

	@Test
	void testStyleConstructor() {
		Style style = new Style(10, Color.green, 24, 5);
		assertEquals(10, style.indent);
		assertEquals(Color.green, style.color);
		assertEquals(24, style.fontSize);
		assertEquals(5, style.leading);
	}

	@Test
	void testToStringContainsValues() {
		Style style = new Style(10, Color.green, 24, 5);
		String result = style.toString();
		assertTrue(result.contains("10"));
		assertTrue(result.contains("24"));
		assertTrue(result.contains("5"));
	}

	@Test
	void testGetFontReturnsScaledFont() {
		Style style = new Style(10, Color.red, 20, 5);
		Font font = style.getFont(2.0f);
		assertNotNull(font);
		assertEquals(40.0f, font.getSize2D(), 0.01f);
	}

	@Test
	void testGetStyleLevel0IsRed() {
		Style style = Style.getStyle(0);
		assertEquals(Color.red, style.color);
	}

	@Test
	void testGetStyleLevel1IsBlue() {
		Style style = Style.getStyle(1);
		assertEquals(Color.blue, style.color);
	}
}

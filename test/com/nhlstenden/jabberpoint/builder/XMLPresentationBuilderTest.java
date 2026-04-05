package com.nhlstenden.jabberpoint.builder;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.factory.DefaultWriterFactory;
import com.nhlstenden.jabberpoint.factory.WriterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XMLPresentationBuilderTest {

	private Presentation presentation;
	private XMLPresentationBuilder builder;
    private WriterFactory writerFactory;

	@BeforeEach
	void setUp() {
		presentation = new Presentation();
        writerFactory = new DefaultWriterFactory();
		builder = new XMLPresentationBuilder(presentation,writerFactory);
	}

	@Test
	void testConstructor() {
		assertNotNull(builder);
		assertEquals(presentation, builder.getPresentation());
	}

	@Test
	void testConstructorRejectsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new XMLPresentationBuilder(null,null);
		});
	}

	@Test
	void testSetPresentationTitle() {
		builder.setPresentationTitle("Test");
		String result = builder.build();
		assertTrue(result.contains("<showtitle>Test</showtitle>"));
	}

	@Test
	void testSetSlideStart() {
		builder.setSlideStart();
		String result = builder.build();
		assertTrue(result.contains("<slide>"));
	}

	@Test
	void testSetSlideFinish() {
		builder.setSlideStart();
		builder.setSlideFinish();
		String result = builder.build();
		assertTrue(result.contains("</slide>"));
	}

	@Test
	void testSetSlideTitle() {
		builder.setSlideStart();
		builder.setSlideTitle("My Slide");
		String result = builder.build();
		assertTrue(result.contains("<title>My Slide</title>"));
	}

	@Test
	void testSetPresentationStartAndEnd() {
		builder.setPresentationStart();
		builder.setPresentationEnd();
		String result = builder.build();
		assertTrue(result.contains("<presentation>"));
		assertTrue(result.contains("</presentation>"));
	}

	@Test
	void testSetSlideElement() {
		builder.setSlideElement("<item kind=\"text\" level=\"1\">Hello</item>");
		String result = builder.build();
		assertTrue(result.contains("<item kind=\"text\" level=\"1\">Hello</item>"));
	}

	@Test
	void testBuildContainsXMLHeaders() {
		String result = builder.build();
		assertTrue(result.contains("<?xml version=\"1.0\"?>"));
		assertTrue(result.contains("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">"));
	}
}

package com.nhlstenden.jabberpoint.builder;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.slide.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DefaultPresentationBuilderTest {

	private Presentation presentation;
	private DefaultPresentationBuilder builder;

	@BeforeEach
	void setUp() {
		presentation = new Presentation();
		builder = new DefaultPresentationBuilder(presentation);
	}

	@Test
	void testConstructor() {
		assertNotNull(builder);
		assertEquals(presentation, builder.getPresentation());
	}

	@Test
	void testConstructorRejectsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new DefaultPresentationBuilder(null);
		});
	}

	@Test
	void testSetPresentationTitle() {
		builder.setPresentationTitle("My Title");
		assertEquals("My Title", presentation.getTitle());
	}

	@Test
	void testSetSlideStartCreatesCurrentSlide() {
		builder.setSlideStart();
		assertNotNull(builder.getCurrentSlide());
	}

	@Test
	void testSetSlideTitleOnCurrentSlide() {
		builder.setSlideStart();
		builder.setSlideTitle("Slide One");
		assertEquals("Slide One", builder.getCurrentSlide().getTitle());
	}

	@Test
	void testSetSlideFinishAppendsToPresentation() {
		builder.setSlideStart();
		builder.setSlideTitle("Slide");
		builder.setSlideFinish();
		assertEquals(1, presentation.getSize());
		assertNull(builder.getCurrentSlide());
	}

	@Test
	void testSetTextItemAddsToSlide() {
		builder.setSlideStart();
		builder.setTextItem(1, "Hello");
		assertEquals(1, builder.getCurrentSlide().getSize());
	}

	@Test
	void testEnsureCurrentSlideThrowsWhenNull() {
		assertThrows(RuntimeException.class, () -> {
			builder.ensureCurrentSlide();
		});
	}

	@Test
	void testSetSlideFinishThrowsWhenNoSlide() {
		assertThrows(RuntimeException.class, () -> {
			builder.setSlideFinish();
		});
	}

	@Test
	void testBuildMultipleSlides() {
		builder.setSlideStart();
		builder.setSlideTitle("First");
		builder.setTextItem(1, "Content 1");
		builder.setSlideFinish();

		builder.setSlideStart();
		builder.setSlideTitle("Second");
		builder.setTextItem(2, "Content 2");
		builder.setSlideFinish();

		assertEquals(2, presentation.getSize());
	}
}

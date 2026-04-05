package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PresentationTest {

	private Presentation presentation;

	@BeforeEach
	void setUp() {
		presentation = new Presentation();
	}

	@Test
	void testDefaultConstructorCreatesEmptyPresentation() {
		assertEquals(0, presentation.getSize());
		assertNull(presentation.getTitle());
	}

	@Test
	void testSetAndGetTitle() {
		presentation.setTitle("Test Title");
		assertEquals("Test Title", presentation.getTitle());
	}

	@Test
	void testAppendIncrementsSize() {
		Slide slide = new Slide();
		presentation.append(slide);
		assertEquals(1, presentation.getSize());
	}

	@Test
	void testAppendMultipleSlides() {
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.append(new Slide());
		assertEquals(3, presentation.getSize());
	}

	@Test
	void testGetSlideReturnsCorrectSlide() {
		Slide slide = new Slide();
		slide.setTitle("First");
		presentation.append(slide);
		assertEquals(slide, presentation.getSlide(0));
	}

	@Test
	void testGetSlideNegativeIndexReturnsNull() {
		assertNull(presentation.getSlide(-1));
	}

	@Test
	void testGetSlideOutOfBoundsReturnsNull() {
		assertNull(presentation.getSlide(0));
	}

	@Test
	void testSetSlideNumber() {
		presentation.append(new Slide());
		presentation.setSlideNumber(0);
		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testNextSlide() {
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(0);
		presentation.nextSlide();
		assertEquals(1, presentation.getSlideNumber());
	}

	@Test
	void testNextSlideAtEndDoesNotAdvance() {
		presentation.append(new Slide());
		presentation.setSlideNumber(0);
		presentation.nextSlide();
		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testPrevSlide() {
		presentation.append(new Slide());
		presentation.append(new Slide());
		presentation.setSlideNumber(1);
		presentation.prevSlide();
		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testPrevSlideAtBeginningDoesNotGoBack() {
		presentation.append(new Slide());
		presentation.setSlideNumber(0);
		presentation.prevSlide();
		assertEquals(0, presentation.getSlideNumber());
	}

	@Test
	void testGetCurrentSlide() {
		Slide slide = new Slide();
		slide.setTitle("Current");
		presentation.append(slide);
		presentation.setSlideNumber(0);
		assertEquals(slide, presentation.getCurrentSlide());
	}

	@Test
	void testGetCurrentSlideWhenNoSlides() {
		assertNull(presentation.getCurrentSlide());
	}

	@Test
	void testClearResetsPresentation() {
		presentation.append(new Slide());
		presentation.setTitle("Title");
		presentation.clear();
		assertEquals(0, presentation.getSize());
	}

	@Test
	void testClearResetsSlideNumberToMinusOne() {
		presentation.append(new Slide());
		presentation.setSlideNumber(0);
		presentation.clear();
		assertEquals(-1, presentation.getSlideNumber());
	}

	@Test
	void testSetSlideNumberWithSlideViewerComponent() {
		javax.swing.JFrame frame = new javax.swing.JFrame();
		com.nhlstenden.jabberpoint.slide.SlideViewerComponent viewer =
				new com.nhlstenden.jabberpoint.slide.SlideViewerComponent(presentation, frame);
		presentation.setShowView(viewer);
		presentation.append(new Slide());
		// Should update the viewer without throwing
		assertDoesNotThrow(() -> presentation.setSlideNumber(0));
		assertEquals(0, presentation.getSlideNumber());
		frame.dispose();
	}

	@Test
	void testGetSlideReturnsNullForTooHighIndex() {
		presentation.append(new Slide());
		assertNull(presentation.getSlide(5));
	}
}

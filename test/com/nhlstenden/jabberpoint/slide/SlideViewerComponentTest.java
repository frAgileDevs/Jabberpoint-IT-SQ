package com.nhlstenden.jabberpoint.slide;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.slide.utility.Style;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerComponentTest {

	@BeforeAll
	static void initStyles() {
		Style.createStyles();
	}

	@Test
	void testConstructor() {
		Presentation presentation = new Presentation();
		JFrame frame = new JFrame();
		SlideViewerComponent component = new SlideViewerComponent(presentation, frame);
		assertNotNull(component);
		frame.dispose();
	}

	@Test
	void testGetPreferredSize() {
		Presentation presentation = new Presentation();
		JFrame frame = new JFrame();
		SlideViewerComponent component = new SlideViewerComponent(presentation, frame);
		Dimension size = component.getPreferredSize();
		assertEquals(Slide.WIDTH, size.width);
		assertEquals(Slide.HEIGHT, size.height);
		frame.dispose();
	}

	@Test
	void testUpdateWithNullSlideDoesNotThrow() {
		Presentation presentation = new Presentation();
		JFrame frame = new JFrame();
		SlideViewerComponent component = new SlideViewerComponent(presentation, frame);
		assertDoesNotThrow(() -> component.update(presentation, null));
		frame.dispose();
	}

	@Test
	void testUpdateWithValidSlide() {
		Presentation presentation = new Presentation();
		presentation.setTitle("Test");
		Slide slide = new Slide();
		slide.setTitle("Slide 1");
		presentation.append(slide);

		JFrame frame = new JFrame();
		SlideViewerComponent component = new SlideViewerComponent(presentation, frame);
		assertDoesNotThrow(() -> component.update(presentation, slide));
		frame.dispose();
	}

	@Test
	void testPaintComponentWithNoSlide() {
		Presentation presentation = new Presentation();
		JFrame frame = new JFrame();
		SlideViewerComponent component = new SlideViewerComponent(presentation, frame);
		component.setSize(Slide.WIDTH, Slide.HEIGHT);

		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		// slideNumber is -1, slide is null — should paint background and return
		assertDoesNotThrow(() -> component.paintComponent(g));
		g.dispose();
		frame.dispose();
	}

	@Test
	void testPaintComponentWithSlide() {
		Presentation presentation = new Presentation();
		presentation.setTitle("Paint Test");
		Slide slide = new Slide();
		slide.setTitle("Visible Slide");
		slide.append(new TextItem(1, "Content"));
		presentation.append(slide);

		JFrame frame = new JFrame();
		SlideViewerComponent component = new SlideViewerComponent(presentation, frame);
		component.setSize(Slide.WIDTH, Slide.HEIGHT);

		// Set slide number and update the component so it has a slide to draw
		component.update(presentation, slide);
		presentation.setSlideNumber(0);

		BufferedImage img = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		assertDoesNotThrow(() -> component.paintComponent(g));
		g.dispose();
		frame.dispose();
	}
}

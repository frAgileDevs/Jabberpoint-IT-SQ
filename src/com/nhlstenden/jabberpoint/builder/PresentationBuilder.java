package com.nhlstenden.jabberpoint.builder;

import com.nhlstenden.jabberpoint.BitmapItem;
import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.slide.Slide;

public abstract class PresentationBuilder {
	private final Presentation presentation;
	private Slide currentSlide;

	public PresentationBuilder(Presentation presentation) {
		if (presentation == null) {
			throw new IllegalArgumentException("presentation must not be null");
		}
		this.presentation = presentation;
		this.currentSlide = null;
	}

	public void setPresentationTitle(String title) {
		presentation.setTitle(title);
	}

	public void startSlide() {
		currentSlide = new Slide();
	}

    public Slide getCurrentSlide()
    {
        return this.currentSlide;
    }

    public void setSlideTitle(String title) {
		ensureCurrentSlide();
		currentSlide.setTitle(title);
	}

	public void addTextItem(int level, String text) {
		ensureCurrentSlide();
		currentSlide.append(level, text);
	}

	public void addBitmapItem(int level, String imageUrl) {
		ensureCurrentSlide();
		currentSlide.append(new BitmapItem(level, imageUrl));
	}

	public void finishSlide() {
		ensureCurrentSlide();
		presentation.append(currentSlide);
		currentSlide = null;
	}

	public Presentation getPresentation() {
		return presentation;
	}

	public void ensureCurrentSlide() {
		if (currentSlide == null) {
			throw new RuntimeException("Current slide is null");
		}
	}

    public void nullParametrChecker()
    {

    }
}

package com.nhlstenden.jabberpoint.builder;

import com.nhlstenden.jabberpoint.slide.BitmapItem;
import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.slide.Slide;
import com.nhlstenden.jabberpoint.slide.SlideItem;

public class DefaultPresentationBuilder extends PresentationBuilder {
    private Slide currentSlide;

    public DefaultPresentationBuilder(Presentation presentation) {
        super(presentation);
        this.currentSlide = null;
    }


    @Override
    public void setSlideTitle(String title) {
        ensureCurrentSlide();
        currentSlide.setTitle(title);
    }


    @Override
    public void setSlideStart() {
        this.currentSlide = new Slide();
    }

    @Override
    public void setSlideFinish() {
        ensureCurrentSlide();
        getPresentation().append(currentSlide);
        this.currentSlide = null;
    }

    public void setBitmapItem(int level, String imageUrl) {
        ensureCurrentSlide();
        currentSlide.append(new BitmapItem(level, imageUrl));
    }

    public void setTextItem(int level, String text) {
        ensureCurrentSlide();
        currentSlide.append(level, text);
    }

    @Override
    public void setSlideItem(SlideItem item) {
        ensureCurrentSlide();
        currentSlide.append(item);
    }

    public Slide getCurrentSlide() {
        return this.currentSlide;
    }

    public void ensureCurrentSlide() {
        if (currentSlide == null) {
            throw new RuntimeException("Current slide is null");
        }
    }
}

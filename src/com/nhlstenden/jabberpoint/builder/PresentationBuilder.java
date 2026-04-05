package com.nhlstenden.jabberpoint.builder;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.slide.SlideItem;

public abstract class PresentationBuilder
{
    private final Presentation presentation;

    public PresentationBuilder(Presentation presentation)
    {
        if (presentation == null) {
            throw new IllegalArgumentException("presentation must not be null");
        }

        this.presentation = presentation;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    public void setPresentationTitle(String title)
    {
        presentation.setTitle(title);
    }

    public abstract void setSlideTitle(String title);
    public abstract void setSlideStart();
    public abstract void setSlideFinish();
    public abstract void setBitmapItem(int level, String name);
    public abstract void setTextItem(int level, String text);
    public abstract void addSlideItem(SlideItem item);
}

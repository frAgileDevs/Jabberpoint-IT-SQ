package com.nhlstenden.jabberpoint.factory;

import com.nhlstenden.jabberpoint.slide.SlideItem;

public abstract class WriterFactory
{
    protected final static String TEXT = "text";
    protected final static String IMAGE = "image";

    public abstract String getSlideItemToWrite(SlideItem slideItem, int level);

    public void parameterNullCheck(SlideItem slideItem)
    {
        if(slideItem == null)
        {
            throw new RuntimeException("Slide item cannot be null");
        }
    }
}

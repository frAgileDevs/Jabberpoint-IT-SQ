package com.nhlstenden.jabberpoint.factory;

import com.nhlstenden.jabberpoint.slide.SlideItem;
import org.w3c.dom.Element;

public class DefaultSlideItemFactory extends SlideItemFactory
{
    public DefaultSlideItemFactory()
    {
    }

    @Override
    public SlideItem createSlideItem(Element element, int level)
    {
        return super.createSlideItem(element, level);
    }
}

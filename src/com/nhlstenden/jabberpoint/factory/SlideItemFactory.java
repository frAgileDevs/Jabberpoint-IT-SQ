package com.nhlstenden.jabberpoint.factory;

import com.nhlstenden.jabberpoint.slide.SlideItem;
import org.w3c.dom.Element;

public abstract class SlideItemFactory {
    public abstract SlideItem createSlideItem(Element element, int level);
}

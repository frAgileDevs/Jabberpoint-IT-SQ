package com.nhlstenden.jabberpoint.factory;

import com.nhlstenden.jabberpoint.BitmapItem;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import com.nhlstenden.jabberpoint.TextItem;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public abstract class SlideItemFactory
{
    protected final static String TEXT = "text";
    protected final static String IMAGE = "image";
    protected static final String KIND = "kind";

    private static final Map<String, SlideItem> itemTypeMap = new HashMap<>();

    public SlideItem createSlideItem(Element element, int level)
    {
        SlideItem slideItem = null;
        String itemType = element.getAttributes().getNamedItem(KIND).getTextContent();
        String content = element.getTextContent();

        switch (itemType) {
            case TEXT -> slideItem = new TextItem(level, content);
            case IMAGE -> slideItem = new BitmapItem(level, content);
            default -> System.err.println("Unknown item type" + itemType);
        }

        return slideItem;
    }

}

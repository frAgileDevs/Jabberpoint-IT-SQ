package com.nhlstenden.jabberpoint.factory;

import com.nhlstenden.jabberpoint.BitmapItem;
import com.nhlstenden.jabberpoint.TextItem;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class DefaultSlideItemFactory extends SlideItemFactory
{
    private final Map<String, BiFunction<Integer, String, SlideItem>> registry = new HashMap<>();

    public DefaultSlideItemFactory()
    {
        registerItem("text", TextItem::new);
        registerItem("image", BitmapItem::new);
    }

    public void registerItem(String type, BiFunction<Integer, String, SlideItem> creator)
    {
        registry.put(type, creator);
    }

    @Override
    public SlideItem createSlideItem(Element element, int level)
    {
        String itemType = element.getAttributes().getNamedItem("kind").getTextContent();
        String content = element.getTextContent();

        BiFunction<Integer, String, SlideItem> creator = registry.get(itemType);

        if (creator == null) {
            System.err.println("Unknown item type: " + itemType);
            return null;
        }

        return creator.apply(level, content);
    }
}

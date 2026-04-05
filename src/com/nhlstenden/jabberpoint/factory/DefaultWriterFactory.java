package com.nhlstenden.jabberpoint.factory;

import com.nhlstenden.jabberpoint.BitmapItem;
import com.nhlstenden.jabberpoint.TextItem;
import com.nhlstenden.jabberpoint.slide.SlideItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class DefaultWriterFactory extends WriterFactory
{
    private final Map<Class<? extends SlideItem>, BiFunction<SlideItem, Integer, String>> WRITER_REGISTRY
            = new HashMap<>();

    public DefaultWriterFactory()
    {
        registerNewElement(TextItem.class, (item, level) ->
                String.format("<item kind=\"%s\" level=\"%d\">%s</item>", TEXT, level, ((TextItem) item).getText()));
        registerNewElement(BitmapItem.class, (item, level) ->
                String.format("<item kind=\"%s\" level=\"%d\">%s</item>", IMAGE, level, ((BitmapItem) item).getName()));
    }

    public void registerNewElement(Class<? extends SlideItem> type, BiFunction<SlideItem, Integer, String> writer)
    {
        WRITER_REGISTRY.put(type, writer);
    }

    @Override
    public String getSlideItemToWrite(SlideItem slideItem, int level)
    {
        parameterNullCheck(slideItem);

        BiFunction<SlideItem, Integer, String> writer = WRITER_REGISTRY.get(slideItem.getClass());

        if (writer == null) {
            System.err.println("Unknown type: " + slideItem.getClass().getName());
            return "Unknown type";
        }

        return writer.apply(slideItem, level);
    }
}

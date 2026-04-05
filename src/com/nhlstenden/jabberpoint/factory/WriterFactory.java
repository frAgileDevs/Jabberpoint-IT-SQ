package com.nhlstenden.jabberpoint.factory;

import com.nhlstenden.jabberpoint.BitmapItem;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import com.nhlstenden.jabberpoint.TextItem;

public abstract class WriterFactory
{
    protected final static String TEXT = "text";
    protected final static String IMAGE = "image";
    protected static final String KIND = "kind";

    public String getSlideItemToWrite(SlideItem slideItem, int level)
    {
        String errorMessage = "Unknown type";
        parameterNullCheck(slideItem);

        return switch (slideItem) {
            case TextItem t -> getWrittenTextItem(t, level);
            case BitmapItem b -> getWrittenBitmapItem(b, level);
            default -> errorMessage;
        };
    }

    public String getWrittenTextItem(TextItem textItem, int level)
    {
        String textItemContent = textItem.getText();

        return String.format("<item kind=\"%s\" level=\"%d\">%s</item>", TEXT, level, textItemContent);
    }

    public String getWrittenBitmapItem(BitmapItem bitmapItem, int level)
    {
        String pictureName = bitmapItem.getName();

        return String.format("<item kind=\"%s\" level=\"%d\">%s</item>", IMAGE, level, pictureName);
    }

    public void parameterNullCheck(SlideItem slideItem)
    {
        if(slideItem == null)
        {
            throw new RuntimeException("Slide item cannot be null");
        }
    }
}

package com.nhlstenden.jabberpoint.builder;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.factory.WriterFactory;
import com.nhlstenden.jabberpoint.slide.SlideItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMLPresentationBuilder extends PresentationBuilder
{
    String[] headers = {
            "<?xml version=\"1.0\"?>",
            "<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">"
    };
    List<String> headerList = new ArrayList<>(Arrays.asList(headers));
    private final WriterFactory writerFactory;

    public XMLPresentationBuilder(Presentation presentation, WriterFactory writerFactory)
    {
        super(presentation);
        this.writerFactory = writerFactory;
    }

    @Override
    public void setPresentationTitle(String title)
    {
        super.setPresentationTitle(title);
        this.headerList.add(String.format("<showtitle>%s</showtitle>",title));
    }

    @Override
    public void setSlideTitle(String title)
    {
        this.headerList.add(String.format("<title>%s</title>", title));
    }

    @Override
    public void setSlideStart()
    {
        this.headerList.add("<slide>");
    }

    @Override
    public void setSlideFinish()
    {
        this.headerList.add("</slide>");
    }

    @Override
    public void setBitmapItem(int level, String name)
    {
        this.headerList.add(String.format("<item kind=\"image\" level=\"%d\">%s</item>", level, name));
    }

    @Override
    public void setTextItem(int level, String text)
    {
        this.headerList.add(String.format("<item kind=\"text\" level=\"%d\">%s</item>", level, text));
    }

    @Override
    public void addSlideItem(SlideItem item)
    {
        this.headerList.add(writerFactory.getSlideItemToWrite(item, item.getLevel()));
    }

    public void setPresentationStart()
    {
        this.headerList.add("<presentation>");
    }

    public void setPresentationEnd()
    {
        this.headerList.add("</presentation>");
    }

    public void setSlideElement(String elementLine)
    {
        this.headerList.add(elementLine);
    }

    public String build() {
        return String.join("\n", headerList);
    }
}

package com.nhlstenden.jabberpoint.builder;

import com.nhlstenden.jabberpoint.Presentation;

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

    public XMLPresentationBuilder(Presentation presentation)
    {
        super(presentation);
    }

    @Override
    public void setPresentationTitle(String title)
    {
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
    public void setBitmapItem(int level, String text)
    {

    }

    @Override
    public void setTextItem(int level, String imageUrl)
    {

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

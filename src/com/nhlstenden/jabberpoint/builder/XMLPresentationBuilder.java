package com.nhlstenden.jabberpoint.builder;

import com.nhlstenden.jabberpoint.Presentation;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class XMLPresentationBuilder
{
    private Presentation presentation;
    String[] headers = {
            "<?xml version=\"1.0\"?>",
            "<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">"
    };
    List<String> headerList = new ArrayList<>(Arrays.asList(headers));

    public XMLPresentationBuilder(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public void setPresentationTitle(String title)
    {
        this.headerList.add(String.format("<showtitle>%s</showtitle>",title));
    }

    public void setSlideTitle(String title)
    {
        this.headerList.add(String.format("<title>%s</title>", title));
    }

    public void setSlide()
    {
        this.headerList.add("<slide></slide>");
    }

    public void setPresentationStart()
    {
        this.headerList.add("<presentation>");
    }

    public void setPresentationEnd()
    {
        this.headerList.add("</presentation>");
    }

    public void setSlideStart()
    {
        this.headerList.add("<slide>");
    }

    public void setSlideEnd()
    {
        this.headerList.add("</slide>");
    }

    public String build() {
        return String.join("\n", headerList);
    }

    public void setSlideElement(String elementLine)
    {
        this.headerList.add(elementLine);
    }

    public List<String> getHeaderList()
    {
        return this.headerList;
    }
}

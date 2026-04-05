package com.nhlstenden.jabberpoint.accessor;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.builder.DefaultPresentationBuilder;
import com.nhlstenden.jabberpoint.builder.PresentationBuilder;
import com.nhlstenden.jabberpoint.builder.XMLPresentationBuilder;
import com.nhlstenden.jabberpoint.slide.Slide;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import com.nhlstenden.jabberpoint.factory.*;

import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * XMLAccessor, reads and writes XML files
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLAccessor extends Accessor {

    /**
     * Default API to use.
     */
    protected static final String DEFAULT_API_TO_USE = "dom";

    /**
     * names of xml tags or attributes
     */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    /**
     * text of messages
     */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    protected DefaultSlideItemFactory slideItemFactory;
    protected DefaultWriterFactory writerFactory;

    protected XMLPresentationBuilder xmlPresentationBuilder;

    public XMLAccessor() {
        this.slideItemFactory = new DefaultSlideItemFactory();
        this.writerFactory = new DefaultWriterFactory();
    }

    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }

    public void loadFile(Presentation presentation, String filename) {
        try {
            Element documentAsElement = getDocumentAsElement(filename);

            DefaultPresentationBuilder presentationBuilder = new DefaultPresentationBuilder(presentation);
            presentationBuilder.setPresentationTitle(getTitle(documentAsElement, SHOWTITLE));

            NodeList slides = documentAsElement.getElementsByTagName(SLIDE);
            for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
                Element xmlSlide = (Element) slides.item(slideNumber);

                presentationBuilder.setSlideStart();
                presentationBuilder.setSlideTitle(this.getTitle(xmlSlide, SLIDETITLE));

                NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
                for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
                    Element item = (Element) slideItems.item(itemNumber);
                    this.loadSlideItem(presentationBuilder, item);
                }

                presentationBuilder.setSlideFinish();
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.err.println(e.getMessage());
        }
    }

    protected void loadSlideItem(PresentationBuilder builder, Element item) {
        int level = 1; // default
        String levelText = item.getAttributes().getNamedItem(LEVEL).getTextContent();

        if (levelText != null) {
            try {
                level = Integer.parseInt(levelText);
            } catch (NumberFormatException x) {
                System.err.println(NFE);
            }
        }
        SlideItem slideItem = slideItemFactory.createSlideItem(item, level);

        if (slideItem != null) {
            builder.setSlideItem(slideItem);
        }
    }

    public void saveFile(Presentation presentation, String filename) {
        File fileToSavePath = getFileToSave(filename);

        this.xmlPresentationBuilder = new XMLPresentationBuilder(presentation, writerFactory);
        this.xmlPresentationBuilder.setPresentationStart();
        this.xmlPresentationBuilder.setPresentationTitle(presentation.getTitle());

        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
            Slide slide = presentation.getSlide(slideNumber);

            this.xmlPresentationBuilder.setSlideStart();
            this.xmlPresentationBuilder.setSlideTitle(slide.getTitle());

            Vector<SlideItem> slideItems = slide.getSlideItems();

            for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
                SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
                xmlPresentationBuilder.setSlideItem(slideItem);
            }
            xmlPresentationBuilder.setSlideFinish();
        }
        xmlPresentationBuilder.setPresentationEnd();
        printNewPresentation(fileToSavePath);
    }

    public Element getDocumentAsElement(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new File(filename));

        return document.getDocumentElement();
    }

    public File getFileToSave(String filename) {
        File defaultFolder = new File("resources");
        this.checkIfFolderExists(defaultFolder);

        return new File(defaultFolder, filename);
    }

    public void checkIfFolderExists(File folder) {
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create directory: ");
            }
        }
    }

    public void printNewPresentation(File fileToSavePath) {
        try {
            PrintWriter printer = new PrintWriter(new FileWriter(fileToSavePath));
            printer.print(this.xmlPresentationBuilder.build());
            printer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

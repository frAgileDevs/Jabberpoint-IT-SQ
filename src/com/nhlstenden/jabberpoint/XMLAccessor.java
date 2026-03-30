package com.nhlstenden.jabberpoint;
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


/** XMLAccessor, reads and writes XML files
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLAccessor extends Accessor {

    /** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";

    /** names of xml tags or attributes */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    /** text of messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    protected static final DefaultSlideItemFactory defaultSlideItemFactory = new DefaultSlideItemFactory();
    protected static final DefaultWriterFactory defaultWriterFactory = new DefaultWriterFactory();

    private String getTitle(Element element, String tagName) {
    	NodeList titles = element.getElementsByTagName(tagName);
    	return titles.item(0).getTextContent();
    }

	public void loadFile(Presentation presentation, String filename) throws IOException {
		int slideNumber, itemNumber, max = 0, maxItems = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(new File(filename)); // Create a JDOM document
			Element doc = document.getDocumentElement();
			presentation.setTitle(getTitle(doc, SHOWTITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			max = slides.getLength();
			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);
				Slide slide = new Slide();
				slide.setTitle(this.getTitle(xmlSlide, SLIDETITLE));
				presentation.append(slide);

				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				maxItems = slideItems.getLength();
				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
					Element item = (Element) slideItems.item(itemNumber);
					loadSlideItem(slide, item);
				}
			}
		}
		catch (IOException iox) {
			System.err.println(iox.toString());
		}
		catch (SAXException sax) {
			System.err.println(sax.getMessage());
		}
		catch (ParserConfigurationException pcx) {
			System.err.println(PCE);
		}
	}

	protected void loadSlideItem(Slide slide, Element item) {
		int level = 1; // default
		String leveltext = item.getAttributes().getNamedItem(LEVEL).getTextContent();

		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println(NFE);
			}
		}

        slide.append(defaultSlideItemFactory.createSlideItem(item, level));
	}

	public void saveFile(Presentation presentation, String filename) throws IOException {
        File defaultFolder = new File("resources");
        this.checkIfFolderExists(defaultFolder);

        File fileToSavePath = new File(defaultFolder, filename);
        PrintWriter out = new PrintWriter(new FileWriter(fileToSavePath));
        XMLPresentationBuilder presentationBuilder = new XMLPresentationBuilder(presentation);

		presentationBuilder.setPresentationStart();
		presentationBuilder.setPresentationTitle(presentation.getTitle());

		for (int slideNumber=0; slideNumber<presentation.getSize(); slideNumber++) {
			Slide slide = presentation.getSlide(slideNumber);
			presentationBuilder.setSlideStart();

            presentationBuilder.setSlideTitle(slide.getTitle());
			Vector<SlideItem> slideItems = slide.getSlideItems();
			for (int itemNumber = 0; itemNumber<slideItems.size(); itemNumber++) {
				SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
                presentationBuilder.setSlideElement(defaultWriterFactory.getSlideItemToWrite(slideItem, slideItem.getLevel()));
			}
            presentationBuilder.setSlideEnd();
		}

        // Debug: show exactly what the builder has accumulated.
        java.util.List<String> headers = presentationBuilder.getHeaderList();
        System.err.println("XMLPresentationBuilder headerList size=" + headers.size());
        for (int i = 0; i < headers.size(); i++) {
            System.err.println("[" + i + "] " + headers.get(i));
        }

		presentationBuilder.setPresentationEnd();
//        out.close();

        try {
            PrintWriter printer = new PrintWriter(new FileWriter(fileToSavePath));
            printer.print(presentationBuilder.build());
            printer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}

    public void checkIfFolderExists(File folder)
    {
        if(!folder.exists())
        {
            boolean created = folder.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create directory: ");
            }
        }
    }

    public File getNewFilePath(String folderName, String fileName)
    {
        return null;
    }

}

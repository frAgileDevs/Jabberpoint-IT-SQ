package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.builder.DefaultPresentationBuilder;
import com.nhlstenden.jabberpoint.builder.PresentationBuilder;

/** A built in demo-presentation
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation extends Accessor {

	public void loadFile(Presentation presentation, String unusedFilename)
	{
		PresentationBuilder builder = new DefaultPresentationBuilder(presentation);
		builder.setPresentationTitle("Demo Presentation");

		builder.startSlide();
		builder.setSlideTitle("JabberPoint");
		builder.addTextItem(1, "The Java Presentation Tool");
		builder.addTextItem(2, "Copyright (c) 1996-2000: Ian Darwin");
		builder.addTextItem(2, "Copyright (c) 2000-now:");
		builder.addTextItem(2, "Gert Florijn and Sylvia Stuurman");
		builder.addTextItem(4, "Starting JabberPoint without a filename");
		builder.addTextItem(4, "shows this presentation");
		builder.addTextItem(1, "Navigate:");
		builder.addTextItem(3, "Next slide: PgDn or Enter");
		builder.addTextItem(3, "Previous slide: PgUp or up-arrow");
		builder.addTextItem(3, "Quit: q or Q");
		builder.finishSlide();

		builder.startSlide();
		builder.setSlideTitle("Demonstration of levels and styles");
		builder.addTextItem(1, "Level 1");
		builder.addTextItem(2, "Level 2");
		builder.addTextItem(1, "Again level 1");
		builder.addTextItem(1, "Level 1 has style number 1");
		builder.addTextItem(2, "Level 2 has style number  2");
		builder.addTextItem(3, "This is how level 3 looks like");
		builder.addTextItem(4, "And this is level 4");
		builder.finishSlide();

		builder.startSlide();
		builder.setSlideTitle("The third slide");
		builder.addTextItem(1, "To open a new presentation,");
		builder.addTextItem(2, "use File->Open from the menu.");
		builder.addTextItem(1, " ");
		builder.addTextItem(1, "This is the end of the presentation.");
		builder.addBitmapItem(1, "JabberPoint.gif");
		builder.finishSlide();
	}

	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new IllegalStateException("Save As->Demo! called");
	}
}

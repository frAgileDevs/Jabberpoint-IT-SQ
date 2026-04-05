package com.nhlstenden.jabberpoint.accessor;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.builder.DefaultPresentationBuilder;

/**
 * A built in demo-presentation
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class DemoPresentation extends Accessor {

    public void loadFile(Presentation presentation, String unusedFilename) {
        DefaultPresentationBuilder builder = new DefaultPresentationBuilder(presentation);
        builder.setPresentationTitle("Demo Presentation");

        builder.setSlideStart();
        builder.setSlideTitle("JabberPoint");
        builder.setTextItem(1, "The Java Presentation Tool");
        builder.setTextItem(2, "Copyright (c) 1996-2000: Ian Darwin");
        builder.setTextItem(2, "Copyright (c) 2000-now:");
        builder.setTextItem(2, "Gert Florijn and Sylvia Stuurman");
        builder.setTextItem(4, "Starting JabberPoint without a filename");
        builder.setTextItem(4, "shows this presentation");
        builder.setTextItem(1, "Navigate:");
        builder.setTextItem(3, "Next slide: PgDn or Enter");
        builder.setTextItem(3, "Previous slide: PgUp or up-arrow");
        builder.setTextItem(3, "Quit: q or Q");
        builder.setSlideFinish();

        builder.setSlideStart();
        builder.setSlideTitle("Demonstration of levels and styles");
        builder.setTextItem(1, "Level 1");
        builder.setTextItem(2, "Level 2");
        builder.setTextItem(1, "Again level 1");
        builder.setTextItem(1, "Level 1 has style number 1");
        builder.setTextItem(2, "Level 2 has style number  2");
        builder.setTextItem(3, "This is how level 3 looks like");
        builder.setTextItem(4, "And this is level 4");
        builder.setSlideFinish();

        builder.setSlideStart();
        builder.setSlideTitle("The third slide");
        builder.setTextItem(1, "To open a new presentation,");
        builder.setTextItem(2, "use File->Open from the menu.");
        builder.setTextItem(1, " ");
        builder.setTextItem(1, "This is the end of the presentation.");
        builder.setBitmapItem(1, "JabberPoint.gif");
        builder.setSlideFinish();
    }

    public void saveFile(Presentation presentation, String unusedFilename) {
        throw new IllegalStateException("This method is not supported");
    }
}

package com.nhlstenden.jabberpoint.slide;

/** <p>A text item.</p>
 * <p>A TextItem only holds the textual data of a slide item. Laying it out and
 * drawing it is delegated to
 * {@link com.nhlstenden.jabberpoint.slide.renderer.TextItemRenderer}.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem extends SlideItem {
	private String text;

	private static final String EMPTYTEXT = "No Text Given";

// a textitem of level level, with the text string
	public TextItem(int level, String string) {
		super(level);
		text = string;
	}

    // an empty textitem
	public TextItem() {
		this(0, EMPTYTEXT);
	}

    // give the text
	public String getText() {
		return text == null ? "" : text;
	}

	public String toString() {
		return "TextItem[" + getLevel() + "," + getText() + "]";
	}
}

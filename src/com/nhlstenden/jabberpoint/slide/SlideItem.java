package com.nhlstenden.jabberpoint.slide;

/** <p>The abstract base class for an item on a slide.</p>
 * <p>A SlideItem only holds the <em>data</em> of the item (such as its level).
 * Drawing that data is the responsibility of a
 * {@link com.nhlstenden.jabberpoint.slide.renderer.SlideItemRenderer},
 * so that data and presentation stay separate responsibilities.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public abstract class SlideItem {
	private int level = 0; // level of the slideitem

	public SlideItem(int lev) {
		level = lev;
	}

	public SlideItem() {
		this(0);
	}

// Give the level
	public int getLevel() {
		return level;
	}
}

package com.nhlstenden.jabberpoint.slide.renderer;

import com.nhlstenden.jabberpoint.slide.SlideItem;
import com.nhlstenden.jabberpoint.slide.utility.Style;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * <p>Draws a {@link SlideItem} and reports the space it needs.</p>
 *
 * <p>Drawing used to live inside the {@code SlideItem} subclasses themselves,
 * which left {@code TextItem} and {@code BitmapItem} responsible for both
 * holding their data <em>and</em> painting it. Moving the drawing here gives
 * each item a single responsibility (its data) and each renderer a single
 * responsibility (how that data is presented).</p>
 */
public interface SlideItemRenderer {

    Rectangle getBoundingBox(SlideItem item, Graphics g, ImageObserver observer, float scale, Style style);

    void draw(SlideItem item, int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}

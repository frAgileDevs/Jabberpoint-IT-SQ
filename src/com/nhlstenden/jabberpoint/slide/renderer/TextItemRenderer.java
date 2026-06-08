package com.nhlstenden.jabberpoint.slide.renderer;

import com.nhlstenden.jabberpoint.slide.Slide;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import com.nhlstenden.jabberpoint.slide.TextItem;
import com.nhlstenden.jabberpoint.slide.utility.Style;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Knows how to lay out and draw a {@link TextItem}.</p>
 */
public class TextItemRenderer implements SlideItemRenderer {

    // build the AttributedString for the item's text
    public AttributedString getAttributedString(TextItem item, Style style, float scale) {
        String text = item.getText();
        AttributedString attrStr = new AttributedString(text);
        if (text.length() > 0) {
            attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
        }
        return attrStr;
    }

    @Override
    public Rectangle getBoundingBox(SlideItem slideItem, Graphics g, ImageObserver observer,
            float scale, Style myStyle) {
        TextItem item = (TextItem) slideItem;
        List<TextLayout> layouts = getLayouts(item, g, myStyle, scale);
        int xsize = 0, ysize = (int) (myStyle.leading * scale);
        Iterator<TextLayout> iterator = layouts.iterator();

        while (iterator.hasNext()) {
            TextLayout layout = iterator.next();
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > xsize) {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0) {
                ysize += bounds.getHeight();
            }
            ysize += layout.getLeading() + layout.getDescent();
        }
        return new Rectangle((int) (myStyle.indent * scale), 0, xsize, ysize);
    }

    @Override
    public void draw(SlideItem slideItem, int x, int y, float scale, Graphics g,
            Style myStyle, ImageObserver o) {
        TextItem item = (TextItem) slideItem;
        if (item.getText().length() == 0) {
            return;
        }
        List<TextLayout> layouts = getLayouts(item, g, myStyle, scale);
        Point pen = new Point(x + (int) (myStyle.indent * scale),
                y + (int) (myStyle.leading * scale));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(myStyle.color);
        Iterator<TextLayout> it = layouts.iterator();
        while (it.hasNext()) {
            TextLayout layout = it.next();
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
    }

    private List<TextLayout> getLayouts(TextItem item, Graphics g, Style s, float scale) {
        List<TextLayout> layouts = new ArrayList<TextLayout>();
        String text = item.getText();
        if (text.length() == 0) {
            return layouts;
        }
        AttributedString attrStr = getAttributedString(item, s, scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (Slide.WIDTH - s.indent) * scale;
        while (measurer.getPosition() < text.length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }
        return layouts;
    }
}

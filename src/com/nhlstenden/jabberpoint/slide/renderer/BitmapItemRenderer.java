package com.nhlstenden.jabberpoint.slide.renderer;

import com.nhlstenden.jabberpoint.slide.BitmapItem;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import com.nhlstenden.jabberpoint.slide.utility.Style;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * <p>Knows how to draw a {@link BitmapItem}'s image.</p>
 */
public class BitmapItemRenderer implements SlideItemRenderer {

    @Override
    public Rectangle getBoundingBox(SlideItem slideItem, Graphics g, ImageObserver observer,
            float scale, Style myStyle) {
        BufferedImage bufferedImage = ((BitmapItem) slideItem).getBufferedImage();
        return new Rectangle((int) (myStyle.indent * scale), 0,
                (int) (bufferedImage.getWidth(observer) * scale),
                ((int) (myStyle.leading * scale)) +
                        (int) (bufferedImage.getHeight(observer) * scale));
    }

    @Override
    public void draw(SlideItem slideItem, int x, int y, float scale, Graphics g,
            Style myStyle, ImageObserver observer) {
        BufferedImage bufferedImage = ((BitmapItem) slideItem).getBufferedImage();
        int width = x + (int) (myStyle.indent * scale);
        int height = y + (int) (myStyle.leading * scale);
        g.drawImage(bufferedImage, width, height,
                (int) (bufferedImage.getWidth(observer) * scale),
                (int) (bufferedImage.getHeight(observer) * scale), observer);
    }
}

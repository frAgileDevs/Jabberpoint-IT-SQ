package com.nhlstenden.jabberpoint.slide.renderer;

import com.nhlstenden.jabberpoint.slide.SlideItem;

/**
 * <p>Supplies the {@link SlideItemRenderer} that knows how to draw a given
 * {@link SlideItem}.</p>
 */
public abstract class SlideItemRendererFactory {
    public abstract SlideItemRenderer getRenderer(SlideItem item);
}

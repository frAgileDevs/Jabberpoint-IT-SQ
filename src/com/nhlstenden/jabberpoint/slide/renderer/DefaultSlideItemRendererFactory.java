package com.nhlstenden.jabberpoint.slide.renderer;

import com.nhlstenden.jabberpoint.slide.BitmapItem;
import com.nhlstenden.jabberpoint.slide.SlideItem;
import com.nhlstenden.jabberpoint.slide.TextItem;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Maps each concrete {@link SlideItem} type to the renderer that draws it.
 * New item types can register their renderer here, mirroring the way
 * {@code DefaultSlideItemFactory} and {@code DefaultWriterFactory} work.</p>
 */
public class DefaultSlideItemRendererFactory extends SlideItemRendererFactory {
    private final Map<Class<? extends SlideItem>, SlideItemRenderer> registry = new HashMap<>();

    public DefaultSlideItemRendererFactory() {
        registerRenderer(TextItem.class, new TextItemRenderer());
        registerRenderer(BitmapItem.class, new BitmapItemRenderer());
    }

    public void registerRenderer(Class<? extends SlideItem> type, SlideItemRenderer renderer) {
        registry.put(type, renderer);
    }

    @Override
    public SlideItemRenderer getRenderer(SlideItem item) {
        SlideItemRenderer renderer = registry.get(item.getClass());

        if (renderer == null) {
            throw new IllegalArgumentException("No renderer registered for " + item.getClass().getName());
        }

        return renderer;
    }
}

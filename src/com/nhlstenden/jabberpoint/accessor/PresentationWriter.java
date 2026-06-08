package com.nhlstenden.jabberpoint.accessor;

import com.nhlstenden.jabberpoint.Presentation;

import java.io.IOException;

/**
 * <p>Writes a {@link Presentation} to a destination.</p>
 *
 * <p>This is the other half of the former {@code Accessor}. Only sources that
 * can actually persist a presentation (such as {@link XMLAccessor}) implement
 * this interface.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 */
public interface PresentationWriter {
    void saveFile(Presentation presentation, String filename) throws IOException;
}

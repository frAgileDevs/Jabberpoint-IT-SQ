package com.nhlstenden.jabberpoint.accessor;

import com.nhlstenden.jabberpoint.Presentation;

import java.io.IOException;

/**
 * <p>Reads presentation data from a source into a {@link Presentation}.</p>
 *
 * <p>This is one half of the former {@code Accessor}. It is segregated from
 * {@link PresentationWriter} so that read-only sources (such as the built-in
 * {@link DemoPresentation}) are not forced to provide a save implementation
 * they cannot honour. This keeps every implementation substitutable for the
 * type it implements (Interface Segregation / Liskov Substitution).</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 */
public interface PresentationLoader {
    void loadFile(Presentation presentation, String filename) throws IOException;
}

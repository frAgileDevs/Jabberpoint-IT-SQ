package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.accessor.XMLAccessor;
import com.nhlstenden.jabberpoint.slide.Slide;
import com.nhlstenden.jabberpoint.slide.TextItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest {

	@Test
	void testLoadFileWithValidFile() throws IOException {
		Presentation presentation = new Presentation();
		XMLAccessor accessor = new XMLAccessor();
		accessor.loadFile(presentation, "test.xml");
		assertTrue(presentation.getSize() > 0);
		assertNotNull(presentation.getTitle());
	}

	@Test
	void testLoadFileSetsTitle() throws IOException {
		Presentation presentation = new Presentation();
		XMLAccessor accessor = new XMLAccessor();
		accessor.loadFile(presentation, "test.xml");
		assertEquals("XML-Based Presentation for Jabberpoint", presentation.getTitle());
	}

	@Test
	void testLoadFileLoadsAllSlides() throws IOException {
		Presentation presentation = new Presentation();
		XMLAccessor accessor = new XMLAccessor();
		accessor.loadFile(presentation, "test.xml");
		assertEquals(5, presentation.getSize());
	}

	@Test
	void testLoadFileLoadsSlideTitles() throws IOException {
		Presentation presentation = new Presentation();
		XMLAccessor accessor = new XMLAccessor();
		accessor.loadFile(presentation, "test.xml");
		assertEquals("JabberPoint XML-Demo", presentation.getSlide(0).getTitle());
	}

	@Test
	void testLoadFileLoadsSlideItems() throws IOException {
		Presentation presentation = new Presentation();
		XMLAccessor accessor = new XMLAccessor();
		accessor.loadFile(presentation, "test.xml");
		Slide firstSlide = presentation.getSlide(0);
		// First slide has 9 text items
		assertTrue(firstSlide.getSize() > 0);
	}

	@Test
	void testLoadFileWithInvalidFileLoadsNothing() {
		Presentation presentation = new Presentation();
		XMLAccessor accessor = new XMLAccessor();
		// loadFile handles the error internally (catches exception, prints to stderr)
		assertDoesNotThrow(() -> accessor.loadFile(presentation, "nonexistent.xml"));
		assertEquals(0, presentation.getSize());
	}

	@Test
	void testSaveFileCreatesFile() throws IOException {
		Presentation presentation = new Presentation();
		presentation.setTitle("Test Save");
		Slide slide = new Slide();
		slide.setTitle("Slide 1");
		slide.append(new TextItem(1, "Hello"));
		presentation.append(slide);

		XMLAccessor accessor = new XMLAccessor();
		accessor.saveFile(presentation, "test_save_output.xml");

		File savedFile = accessor.getFileToSave("test_save_output.xml");
		assertTrue(savedFile.exists());
		assertTrue(savedFile.length() > 0);

		// Clean up
		savedFile.delete();
	}

	@Test
	void testSaveAndLoadRoundTrip() throws IOException {
		// Build a presentation
		Presentation original = new Presentation();
		original.setTitle("Round Trip Test");
		Slide slide = new Slide();
		slide.setTitle("Test Slide");
		slide.append(new TextItem(1, "Item One"));
		slide.append(new TextItem(2, "Item Two"));
		original.append(slide);

		// Save it
		XMLAccessor accessor = new XMLAccessor();
		accessor.saveFile(original, "roundtrip_test.xml");

		File savedFile = accessor.getFileToSave("roundtrip_test.xml");
		assertTrue(savedFile.exists());

		// Copy jabberpoint.dtd to resources/ so the parser can resolve it
		File dtdSource = new File("jabberpoint.dtd");
		File dtdTarget = new File("resources", "jabberpoint.dtd");
		boolean dtdCopied = false;
		if (dtdSource.exists() && !dtdTarget.exists()) {
			Files.copy(dtdSource.toPath(), dtdTarget.toPath());
			dtdCopied = true;
		}

		try {
			// Load it back
			Presentation loaded = new Presentation();
			accessor.loadFile(loaded, savedFile.getPath());

			assertEquals(original.getTitle(), loaded.getTitle());
			assertEquals(original.getSize(), loaded.getSize());
			assertEquals("Test Slide", loaded.getSlide(0).getTitle());
			assertEquals(2, loaded.getSlide(0).getSize());
		} finally {
			// Clean up
			savedFile.delete();
			if (dtdCopied) {
				dtdTarget.delete();
			}
		}
	}

	@Test
	void testCheckIfFolderExistsCreatesFolder(@TempDir File tempDir) {
		XMLAccessor accessor = new XMLAccessor();
		File newFolder = new File(tempDir, "newsubdir");
		assertFalse(newFolder.exists());
		accessor.checkIfFolderExists(newFolder);
		assertTrue(newFolder.exists());
	}

	@Test
	void testCheckIfFolderExistsDoesNothingForExistingFolder(@TempDir File tempDir) {
		XMLAccessor accessor = new XMLAccessor();
		assertTrue(tempDir.exists());
		assertDoesNotThrow(() -> accessor.checkIfFolderExists(tempDir));
	}

	@Test
	void testGetFileToSaveReturnsFileInResourcesFolder() {
		XMLAccessor accessor = new XMLAccessor();
		File result = accessor.getFileToSave("test_output.xml");
		assertEquals("resources", result.getParentFile().getName());
		assertEquals("test_output.xml", result.getName());
	}
}

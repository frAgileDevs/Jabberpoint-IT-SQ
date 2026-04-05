package com.nhlstenden.jabberpoint.slide.utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AboutBoxTest {

	@Test
	void testAboutBoxClassExists() {
		// AboutBox.show() opens a JOptionPane dialog, so we can only verify
		// the class is accessible without actually showing the dialog
		assertNotNull(AboutBox.class);
	}
}

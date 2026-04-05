package com.nhlstenden.jabberpoint.factory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DefaultSlideItemFactoryTest {

	@Test
	void testConstructor() {
		DefaultSlideItemFactory factory = new DefaultSlideItemFactory();
		assertNotNull(factory);
	}
}

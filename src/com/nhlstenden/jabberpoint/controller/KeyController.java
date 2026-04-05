package com.nhlstenden.jabberpoint.controller;

import com.nhlstenden.jabberpoint.Command;
import com.nhlstenden.jabberpoint.ExitCommand;
import com.nhlstenden.jabberpoint.NextSlideCommand;
import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.PrevSlideCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/** <p>This is the KeyController (KeyListener)</p>
 * <p>Uses the Command Pattern to delegate key actions to Command objects.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class KeyController extends KeyAdapter {
	private Command nextSlideCommand;
	private Command prevSlideCommand;
	private Command exitCommand;

	public KeyController(Presentation presentation) {
		nextSlideCommand = new NextSlideCommand(presentation);
		prevSlideCommand = new PrevSlideCommand(presentation);
		exitCommand = new ExitCommand(presentation);
	}

	public void keyPressed(KeyEvent keyEvent) {
		switch(keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				nextSlideCommand.execute();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				prevSlideCommand.execute();
				break;
			case 'q':
			case 'Q':
				exitCommand.execute();
				break;
			default:
				break;
		}
	}
}

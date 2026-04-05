package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.utility.AboutBox;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** <p>The controller for the menu</p>
 * <p>Uses the Command Pattern to delegate actions to Command objects,
 * enabling reuse across menu and keyboard controllers.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	
	private static final long serialVersionUID = 227L;
	
	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";

	public MenuController(Frame frame, Presentation presentation) {
		MenuItem menuItem;

		// File menu
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		addCommandListener(menuItem, new OpenFileCommand(presentation, frame));
		fileMenu.add(menuItem = mkMenuItem(NEW));
		addCommandListener(menuItem, new NewPresentationCommand(presentation, frame));
		fileMenu.add(menuItem = mkMenuItem(SAVE));
		addCommandListener(menuItem, new SaveFileCommand(presentation, frame));
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		addCommandListener(menuItem, new ExitCommand(presentation));
		add(fileMenu);

		// View menu
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		addCommandListener(menuItem, new NextSlideCommand(presentation));
		viewMenu.add(menuItem = mkMenuItem(PREV));
		addCommandListener(menuItem, new PrevSlideCommand(presentation));
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		addCommandListener(menuItem, new GoToSlideCommand(presentation, frame));
		add(viewMenu);

		// Help menu
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		addCommandListener(menuItem, new AboutCommand(frame));
		setHelpMenu(helpMenu);		// needed for portability (Motif, etc.).
	}

	/** Bind a Command to a MenuItem via an ActionListener. */
	private void addCommandListener(MenuItem menuItem, final Command command) {
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				command.execute();
			}
		});
	}

	/** Create a menu item with a keyboard shortcut. */
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}

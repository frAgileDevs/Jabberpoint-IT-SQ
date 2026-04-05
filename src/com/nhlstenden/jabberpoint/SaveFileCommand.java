package com.nhlstenden.jabberpoint;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

// <p>Command to save the current presentation to an XML file.</p>

public class SaveFileCommand implements Command {
	private Presentation presentation;
	private Frame parent;

	protected static final String SAVEFILE = "dump.xml";
	protected static final String IOEX = "IO Exception: ";
	protected static final String SAVEERR = "Save Error";

	public SaveFileCommand(Presentation presentation, Frame parent) {
		this.presentation = presentation;
		this.parent = parent;
	}

	public void execute() {
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.saveFile(presentation, SAVEFILE);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, IOEX + exc,
					SAVEERR, JOptionPane.ERROR_MESSAGE);
		}
	}
}

package com.nhlstenden.jabberpoint.command;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.accessor.PresentationWriter;
import com.nhlstenden.jabberpoint.accessor.XMLAccessor;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

// <p>Command to save the current presentation to an XML file.</p>

public class SaveFileCommand implements Command {
	private Presentation presentation;
	private Frame parent;

	public static final String SAVEFILE = "dump.xml";
	public static final String IOEX = "IO Exception: ";
	public static final String SAVEERR = "Save Error";

	public SaveFileCommand(Presentation presentation, Frame parent) {
		this.presentation = presentation;
		this.parent = parent;
	}

	public void execute() {
		PresentationWriter xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.saveFile(presentation, SAVEFILE);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, IOEX + exc,
					SAVEERR, JOptionPane.ERROR_MESSAGE);
		}
	}
}

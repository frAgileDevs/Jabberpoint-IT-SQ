package com.nhlstenden.jabberpoint.command;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.accessor.Accessor;
import com.nhlstenden.jabberpoint.accessor.XMLAccessor;

import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

// <p>Command to open a presentation file.</p>

public class OpenFileCommand implements Command {
	private Presentation presentation;
	private Frame parent;

	protected static final String TESTFILE = "test.xml";
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";

	public OpenFileCommand(Presentation presentation, Frame parent) {
		this.presentation = presentation;
		this.parent = parent;
	}

	public void execute() {
		presentation.clear();
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.loadFile(presentation, TESTFILE);
			presentation.setSlideNumber(0);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, IOEX + exc,
					LOADERR, JOptionPane.ERROR_MESSAGE);
		}
		parent.repaint();
	}
}

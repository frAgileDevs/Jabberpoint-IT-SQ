package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.slide.utility.AboutBox;

import java.awt.Frame;

// <p>Command to show the About dialog.</p>

public class AboutCommand implements Command {
	private Frame parent;

	public AboutCommand(Frame parent) {
		this.parent = parent;
	}

	public void execute() {
		AboutBox.show(parent);
	}
}

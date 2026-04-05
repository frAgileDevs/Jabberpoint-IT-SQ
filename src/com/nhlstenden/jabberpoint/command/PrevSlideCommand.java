package com.nhlstenden.jabberpoint.command;

// <p>Command to go back to the previous slide.</p>

import com.nhlstenden.jabberpoint.Presentation;

public class PrevSlideCommand implements Command {
	private Presentation presentation;

	public PrevSlideCommand(Presentation presentation) {
		this.presentation = presentation;
	}

	public void execute() {
		presentation.prevSlide();
	}
}

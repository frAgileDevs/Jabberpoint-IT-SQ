package com.nhlstenden.jabberpoint;

// <p>Command to advance to the next slide.</p>

public class NextSlideCommand implements Command {
	private Presentation presentation;

	public NextSlideCommand(Presentation presentation) {
		this.presentation = presentation;
	}

	public void execute() {
		presentation.nextSlide();
	}
}

package com.nhlstenden.jabberpoint;

// <p>Command to exit the application.</p>
public class ExitCommand implements Command {
	private Presentation presentation;

	public ExitCommand(Presentation presentation) {
		this.presentation = presentation;
	}

	public void execute() {
		presentation.exit(0);
	}
}

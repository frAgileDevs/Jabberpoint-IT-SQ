// <p>Command to go back to the previous slide.</p>

public class PrevSlideCommand implements Command {
	private Presentation presentation;

	public PrevSlideCommand(Presentation presentation) {
		this.presentation = presentation;
	}

	public void execute() {
		presentation.prevSlide();
	}
}

import java.awt.Frame;

/** <p>Command to create a new (empty) presentation.</p>
 * @version 1.0 2026/04/05
 */
public class NewPresentationCommand implements Command {
	private Presentation presentation;
	private Frame parent;

	public NewPresentationCommand(Presentation presentation, Frame parent) {
		this.presentation = presentation;
		this.parent = parent;
	}

	public void execute() {
		presentation.clear();
		parent.repaint();
	}
}

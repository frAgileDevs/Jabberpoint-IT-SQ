import java.awt.Frame;
import javax.swing.JOptionPane;

// <p>Command to go to a specific slide number (prompted via dialog).</p>
public class GoToSlideCommand implements Command {
	private Presentation presentation;
	private Frame parent;

	protected static final String PAGENR = "Page number?";

	public GoToSlideCommand(Presentation presentation, Frame parent) {
		this.presentation = presentation;
		this.parent = parent;
	}

	public void execute() {
		String pageNumberStr = JOptionPane.showInputDialog(parent, PAGENR);
		if (pageNumberStr != null) {
			try {
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setSlideNumber(pageNumber - 1);
			} catch (NumberFormatException e) {
				// ignore invalid input
			}
		}
	}
}

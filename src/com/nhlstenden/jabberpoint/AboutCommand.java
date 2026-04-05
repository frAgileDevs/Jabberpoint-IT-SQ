import java.awt.Frame;

/** <p>Command to show the About dialog.</p>
 * @version 1.0 2026/04/05
 */
public class AboutCommand implements Command {
	private Frame parent;

	public AboutCommand(Frame parent) {
		this.parent = parent;
	}

	public void execute() {
		AboutBox.show(parent);
	}
}

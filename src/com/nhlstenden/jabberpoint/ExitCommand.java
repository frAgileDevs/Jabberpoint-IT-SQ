/** <p>Command to exit the application.</p>
 * @version 1.0 2026/04/05
 */
public class ExitCommand implements Command {
	private Presentation presentation;

	public ExitCommand(Presentation presentation) {
		this.presentation = presentation;
	}

	public void execute() {
		presentation.exit(0);
	}
}

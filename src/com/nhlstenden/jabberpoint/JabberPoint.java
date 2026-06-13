package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.accessor.DemoPresentation;
import com.nhlstenden.jabberpoint.accessor.PresentationLoader;
import com.nhlstenden.jabberpoint.accessor.XMLAccessor;
import com.nhlstenden.jabberpoint.slide.SlideViewerFrame;
import com.nhlstenden.jabberpoint.slide.utility.Style;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import javax.swing.JOptionPane;

import java.io.IOException;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

/** JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class JabberPoint {
	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

	private static void runTests() {
		SummaryGeneratingListener listener = new SummaryGeneratingListener();
		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(selectPackage("com.nhlstenden.jabberpoint"))
				.build();

		Launcher launcher = LauncherFactory.create();
		launcher.execute(request, listener);
		TestExecutionSummary summary = listener.getSummary();
		long failed = summary.getTestsFailedCount();

		if (failed > 0) {
			for (TestExecutionSummary.Failure failure : summary.getFailures()) {
				String testClassName = failure.getTestIdentifier().getDisplayName();
				Throwable testException = failure.getException();
				System.err.println("FAILED: " + testClassName + " - " + testException);
			}
			JOptionPane.showMessageDialog(null,
					failed + " test(s) failed. See console for details.",
					JABERR, JOptionPane.ERROR_MESSAGE);
		} else {
			System.out.println("All " + summary.getTestsSucceededCount() + " tests passed.");
		}
	}

	/** The Main Program */
	public static void main(String argv[]) {
		runTests();
		Style.createStyles();
		Presentation presentation = new Presentation();
		new SlideViewerFrame(JABVERSION, presentation);
		try {
			PresentationLoader loader;
			String source;
			if (argv.length == 0) { // a demo presentation
				loader = new DemoPresentation();
				source = "";
			} else {
				loader = new XMLAccessor();
				source = argv[0];
			}
			loader.loadFile(presentation, source);
			presentation.setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					IOERR + ex, JABERR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
}

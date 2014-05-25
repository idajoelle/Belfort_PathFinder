package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private final double SCALE = 7.5; // <Initial scale of map> m = 1 px
	private final int SCALE_SIZE = 30;
	private Dimension screenDimension;
	private int clientWidth;
	private int clientHeight;
	private CustomSplitPane customSplitPane = new CustomSplitPane(true);
	private JMenu File = new JMenu("Fichier");
	private JMenu View = new JMenu("Affichage");
	private JMenu Help = new JMenu("Aide");
	private JMenuItem item1 = new JMenuItem("Quitter");
	private JMenuItem item2 = new JMenuItem("A propos");
	private JCheckBoxMenuItem view1 = new JCheckBoxMenuItem("Animations");
	private JCheckBoxMenuItem view2 = new JCheckBoxMenuItem(
			"Afficher controlPanel");
	private JCheckBoxMenuItem view3 = new JCheckBoxMenuItem(
			"Afficher overlayPanel");
	private GUI gui;
	private MapView belfortMap;
	private ResearchPanel researchPanel;
	private ControlPanel controlPanel = new ControlPanel();
	private OverlayPanel overlayPanel = new OverlayPanel();

	
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border coumpoundBorder = BorderFactory.createCompoundBorder(raisedbevel,
			loweredbevel);

	public GUI(String mapPath) {

		super();

		/* set GUI screen dimension on any client */

		// get and use default appearance of the system
		String nativeLF = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(nativeLF);
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {
		}

		// get client screen dimension
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		screenDimension = toolkit.getScreenSize();
		Insets insets = toolkit.getScreenInsets(getGraphicsConfiguration());

		// calculating the size used on the desktop
		clientWidth = (int) (screenDimension.getWidth() - insets.left - insets.right);
		clientHeight = (int) (screenDimension.getHeight() - insets.top - insets.bottom);
		setPreferredSize(new Dimension(clientWidth, clientHeight));

		
		// instantiate mapView
		MapPanel map = new MapPanel(mapPath, 40);
		//map.add(overlayPanel);
		//map.add(controlPanel);
		belfortMap = new MapView(map);
		belfortMap.setPreferredSize(new Dimension(clientWidth, clientHeight));
		//belfortMap.add(overlayPanel);
		//belfortMap.add(controlPanel);
		belfortMap.setBorder(coumpoundBorder);
		
	
		// instantiate researchPanel
		researchPanel = new ResearchPanel();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(researchPanel);


		// split adding
		customSplitPane.setSplit(.2);
		customSplitPane.setComponentOne(researchPanel);
		customSplitPane.setComponentTwo(belfortMap);
		getContentPane().add(customSplitPane, BorderLayout.CENTER);

		// instantiate GUI
		this.pack();
		this.setTitle("Path Finder");
		this.setLocation(insets.left, insets.top);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// menuBar creation
	public JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		{
			File.add(item1);
			View.add(view1);
			View.addSeparator();
			View.add(view2);
			View.add(view3);
			Help.add(item2);

			menuBar.add(File);
			menuBar.add(View);
			menuBar.add(Help);
		}

		return menuBar;
	}

	public MapView getBelfortMap() {
		return belfortMap;
	}

	public void setBelfortMap(MapView belfortMap) {
		this.belfortMap = belfortMap;
	}

	public ResearchPanel getResearchPanel() {
		return researchPanel;
	}

	public void setResearchPanel(ResearchPanel researchPanel) {
		this.researchPanel = researchPanel;
	}

	public double getSCALE() {
		return SCALE;
	}

	public int getSCALE_SIZE() {
		return SCALE_SIZE;
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public JMenu getFile() {
		return File;
	}

	public void setFile(JMenu file) {
		File = file;
	}

	public JMenu getView() {
		return View;
	}

	public void setView(JMenu view) {
		View = view;
	}

	public JMenu getHelp() {
		return Help;
	}

	public void setHelp(JMenu help) {
		Help = help;
	}

	public JMenuItem getItem2() {
		return item2;
	}

	public void setItem2(JMenuItem item2) {
		this.item2 = item2;
	}

	public JMenuItem getItem1() {
		return item1;
	}

	public void setItem1(JMenuItem item1) {
		this.item1 = item1;
	}

	public JCheckBoxMenuItem getView2() {
		return view2;
	}

	public void setView2(JCheckBoxMenuItem view2) {
		this.view2 = view2;
	}

	public JCheckBoxMenuItem getView1() {
		return view1;
	}

	public void setView1(JCheckBoxMenuItem view1) {
		this.view1 = view1;
	}

	public JCheckBoxMenuItem getView3() {
		return view3;
	}

	public void setView3(JCheckBoxMenuItem view3) {
		this.view3 = view3;
	}



	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}

	public OverlayPanel getOverlayPanel() {
		return overlayPanel;
	}

	public void setOverlayPanel(OverlayPanel overlayPanel) {
		this.overlayPanel = overlayPanel;
	}

	

}

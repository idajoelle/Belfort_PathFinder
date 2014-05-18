package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private Dimension screenDimension;
	private int clientWidth;
    private int clientHeight;
    //
    private CustomSplitPane customSplitPane = new CustomSplitPane(true);
    //Menu bar
    private JMenuBar menuBar = new JMenuBar();
    private JMenu File = new JMenu("Fichier");
    private JMenu View = new JMenu("Affichage");
    private JMenu Help = new JMenu("Aide");
    private JMenuItem item1 = new JMenuItem("Quitter");
    private JMenuItem item2 = new JMenuItem("A propos");
    private JCheckBoxMenuItem view1 = new JCheckBoxMenuItem("Animations");
    private JCheckBoxMenuItem view2 = new JCheckBoxMenuItem("Afficher controlPanel");
    private JCheckBoxMenuItem view3 = new JCheckBoxMenuItem("Afficher overlayPanel");
    
    
   

    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	Border coumpoundBorder = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
	
	public GUI(){
		
		super();
		
		/*set  GUI screen dimension on any client*/
		
		//get and use default appearance of the system
		String nativeLF = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(nativeLF);
		} 
		catch (InstantiationException e) {}
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		catch (IllegalAccessException e) {}
		
		//get client screen dimension
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        screenDimension = toolkit.getScreenSize();
        Insets insets = toolkit.getScreenInsets(getGraphicsConfiguration()); 
        
        //calculating the size used on the desktop
        clientWidth = (int)(screenDimension.getWidth()-insets.left-insets.right); 
        clientHeight = (int)(screenDimension.getHeight()-insets.top-insets.bottom); 
        setPreferredSize(new Dimension(clientWidth,clientHeight));
        
        //instantiate map
        String mapPath="baseline/region_belfort_routes_fleuves_habitats.gif";

        MapPanel belfortMap= new MapPanel(mapPath);
        belfortMap.setBorder(coumpoundBorder);
       
        //instantiate researchPanel
        ResearchPanel researchPanel = new ResearchPanel();
        

      

        
     
        //Menu
        
        this.File.add(item1);
        this.View.add(view1);
        this.View.addSeparator();
        this.View.add(view2);
        this.View.add(view3);
        this.Help.add(item2);
        
        this.menuBar.add(File);
        this.menuBar.add(View);
        this.menuBar.add(Help);
        this.setJMenuBar(menuBar);
        
   
        //instantiate GUI
        this.pack();
        this.setTitle("Path Finder");
		this.setLocation(insets.left, insets.top);
		getContentPane().setLayout(new BorderLayout());
		
		this.getContentPane().add(belfortMap ,BorderLayout.CENTER);
		this.getContentPane().add(researchPanel ,BorderLayout.WEST);
		  customSplitPane.setSplit(.2);
          customSplitPane.setComponentOne(researchPanel);
          customSplitPane.setComponentTwo(belfortMap);
          add(customSplitPane, BorderLayout.CENTER);
		
	
		
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
		       
		
	}

	public JCheckBoxMenuItem getView3() {
		return view3;
	}

	public void setView3(JCheckBoxMenuItem view3) {
		this.view3 = view3;
	}

	
	
	
	

}

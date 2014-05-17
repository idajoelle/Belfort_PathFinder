package view;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GUI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension screenDimension;
	private int clientWidth;
    private int clientHeight;
  
	
	/**
	 * 
	 */
	/**
	 * 
	 */
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
        
   
        //instantiate GUI
        this.pack();
        this.setTitle("Path Finder");
		this.setLocation(insets.left, insets.top);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
		       
		
	}

	
	
	
	

}

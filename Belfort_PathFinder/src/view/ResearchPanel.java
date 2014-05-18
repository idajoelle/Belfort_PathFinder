package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ResearchPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResearchPanel(){
		 super(new BorderLayout(8, 8));
         setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
         setBackground(Color.white);  
         
         JPanel topPanel= new JPanel();           
         JPanel bottomPanel = new JPanel();
            
         
         CustomSplitPane customSplitPane = new CustomSplitPane(false);
         customSplitPane.setSplit(.2);
         customSplitPane.setComponentOne(topPanel);
         customSplitPane.setComponentTwo(bottomPanel);
         add(customSplitPane, BorderLayout.CENTER);
         
	}

}

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.GUI;


public class Main {
public static void init(){
	new GUI();
}
	public static void main(String[] args) {
		
	    SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                  
                }
              init();
            }
        });
		
		

	}

}

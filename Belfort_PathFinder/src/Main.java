import view.GUI;


public class Main {
public static void init(){
	new GUI();
}
	public static void main(String[] args) {
		
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                init();
	            }
	        });

	}

}

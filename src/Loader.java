import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;


public class Loader {
	public static void main(String[] args){
		setUIFont(new javax.swing.plaf.FontUIResource("Open Sans",Font.PLAIN,14));
		JFrame frame = new JFrame("Sales Tax Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scroll = new JScrollPane(new CalculatorPanel());
		frame.add(scroll);
		//frame.add(new CalculatorPanel());
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    }    
}

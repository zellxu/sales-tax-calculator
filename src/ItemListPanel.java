import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ItemListPanel extends JPanel implements ActionListener{
	
	private final int WIDTH=500,HEIGHT=600;
	
	public ItemListPanel(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.add(new JLabel("TEST"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

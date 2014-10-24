import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;


public class CalculatorPanel extends JPanel {
	private final int WIDTH=800,HEIGHT=600; 
	private Calculator calculator;
	private JTextField name;
	private JFormattedTextField price;
	private JComboBox<Item.Category> category;

	public CalculatorPanel(){
		calculator = new Calculator();
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		JPanel itemListPanel = new ItemListPanel();
		this.add(itemListPanel);
		
		JLabel newItemLabel = new JLabel("Add New Item");
		newItemLabel.setFont(new Font("Open Sans",Font.PLAIN,20));
		JLabel nameLabel = new JLabel("Name : ");
		name = new JTextField();
		JLabel categoryLabel = new JLabel("Category : ");
		category = new JComboBox<Item.Category>(Item.Category.values());
		JLabel priceLabel = new JLabel("Price : ");
		price = new JFormattedTextField(new NumberFormatter());
		JButton importAdd = new JButton("Import from file");
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewItem();
			}
			
		});

		
		JPanel actionPanel = new JPanel();
		//actionPanel.setPreferredSize(new Dimension(300,300));
		actionPanel.setLayout(new GridLayout(5,2,5,5));
		actionPanel.add(newItemLabel);
		actionPanel.add(new JLabel(""));
		actionPanel.add(nameLabel);
		actionPanel.add(name);
		actionPanel.add(categoryLabel);
		actionPanel.add(category);
		actionPanel.add(priceLabel);
		actionPanel.add(price);
		actionPanel.add(importAdd);
		actionPanel.add(add);

		
		JLabel subtotalLabel = new JLabel("Subtotal: "+calculator.getSubTotal());
		JLabel taxLabel = new JLabel("Tax: "+calculator.getTax());
		JLabel totalLabel = new JLabel("Total: "+calculator.getTotal());

		JPanel sumPanel = new JPanel();
		sumPanel.setLayout(new GridLayout(3,1));
		sumPanel.add(subtotalLabel);
		sumPanel.add(taxLabel);
		sumPanel.add(totalLabel);
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(3,1));
		
		container.add(actionPanel);
		container.add(new JLabel(""));
		container.add(sumPanel);
		this.add(container);
	}

	private void addNewItem() {
		if(name.getText().isEmpty() || price.getText().isEmpty())
			JOptionPane.showMessageDialog(this,"Name and Price cannot be blank");
		else{
			//calculator.getItems().add(new Item(name.getText(),category.getSelectedItem(),price.getText()));
		}
		System.out.println(Double.parseDouble("1.2"));
			
	}

}

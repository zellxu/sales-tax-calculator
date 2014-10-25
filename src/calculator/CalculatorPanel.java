package calculator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CalculatorPanel extends JPanel {
	private Calculator calculator;
	private JLabel subtotalLabel,taxLabel,totalLabel;
	private JTextField quantity, price;
	private ItemListPanel itemListPanel;
	private JComboBox<Item.Category> category;
	private JFileChooser fc = new JFileChooser();
	
	public CalculatorPanel() {
		calculator = new Calculator();
		fc = new JFileChooser(System.getProperty("user.dir"));
		fc.setFileFilter(new FileNameExtensionFilter("*.txt","txt"));
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(Box.createRigidArea(new Dimension(10,0)));

		itemListPanel = new ItemListPanel(calculator);
		this.add(itemListPanel);
		this.add(Box.createRigidArea(new Dimension(20,0)));
		
		JLabel newItemLabel = new JLabel("Add New Item");
		newItemLabel.setFont(new Font("Open Sans",Font.BOLD,20));
		JLabel quantityLabel = new JLabel("Quantity : ");
		quantity = new JTextField();
		JLabel categoryLabel = new JLabel("Category : ");
		category = new JComboBox<Item.Category>(Item.Category.values());
		JLabel priceLabel = new JLabel("Price : ");
		price = new JTextField();
		JButton importAdd = new JButton("Import from file");
		importAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				importFromFile();
			}
		});
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addNewItem();
			}
		});
		
		//Top right panel group
		JPanel actionPanel = new JPanel();
		//actionPanel.setPreferredSize(new Dimension(300,300));
		actionPanel.setLayout(new GridLayout(5,2,5,5));
		actionPanel.add(newItemLabel);
		actionPanel.add(new JLabel(""));
		actionPanel.add(quantityLabel);
		actionPanel.add(quantity);
		actionPanel.add(categoryLabel);
		actionPanel.add(category);
		actionPanel.add(priceLabel);
		actionPanel.add(price);
		actionPanel.add(importAdd);
		actionPanel.add(add);
		
		subtotalLabel = new JLabel("Subtotal: "+calculator.getSubTotal());
		taxLabel = new JLabel("Tax:        "+calculator.getTax());
		totalLabel = new JLabel("Total:      "+(calculator.getTotal()));

		//Bottom right panel group
		JPanel sumPanel = new JPanel();
		sumPanel.setLayout(new BoxLayout(sumPanel,BoxLayout.Y_AXIS));
		sumPanel.add(subtotalLabel);
		sumPanel.add(Box.createRigidArea(new Dimension(0,25)));
		sumPanel.add(taxLabel);
		sumPanel.add(Box.createRigidArea(new Dimension(0,25)));
		sumPanel.add(totalLabel);
		sumPanel.add(Box.createRigidArea(new Dimension(0,25)));
		
		//Right panel group
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(350,500));
		container.setMaximumSize(new Dimension(350,1080));
		container.setLayout(new BorderLayout());
		container.add(actionPanel,BorderLayout.NORTH);

		container.add(sumPanel,BorderLayout.SOUTH);
		this.add(container);
	}

	private void importFromFile() {
		System.out.println(Arrays.toString(Item.Category.values()));
		int validate = fc.showOpenDialog(this);
		if(validate==JFileChooser.APPROVE_OPTION) {
			FileManager fm = new FileManager(fc.getSelectedFile());
			if(!fm.validateFile()){
				JOptionPane.showMessageDialog(this,"File format incorrect, require *.txt");
				return;
			}
			if(!fm.validateInput()){
				JOptionPane.showMessageDialog(this, fm.getError());
				return;
			}
			int lineNumber = fm.getLineNumber();
			int n=JOptionPane.showConfirmDialog(this, "Are you sure to import total of " + lineNumber + (lineNumber==1 ? " row":" rows") + "?", "Are you sure?", JOptionPane.YES_NO_OPTION);
			if(n!=0)
				return;
			itemListPanel.addRows(fm.importFromFile(calculator));
			updateLabel();
			
		}
	}

	/**
	 * Add one item to the list of items
	 */
	private void addNewItem() {
		if(quantity.getText().isEmpty() || price.getText().isEmpty())
			JOptionPane.showMessageDialog(this,"Quantity and Price cannot be blank");
		else {
			int quantityV=0;
			double priceV=0;
			try {
				quantityV = Integer.parseInt(quantity.getText());
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this,"Quantity need to be an integer number");
				return;
			}
			try {
				priceV = Double.parseDouble(price.getText());
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this,"Price need to be a number");
				return;
			}
			if(quantityV<0 || priceV<0) {
				JOptionPane.showMessageDialog(this,"Quantity and Price cannot be negative");
				return;
			}

			Item i = new Item(quantity.getText(), ((Item.Category) category.getSelectedItem()), price.getText());
			calculator.add(i);
			itemListPanel.addRow(i);
			updateLabel();
		}
	}
	
	/**
	 * Update the tax and total information
	 */
	public void updateLabel() {
		subtotalLabel.setText("Subtotal: "+calculator.getSubTotal());
		taxLabel.setText("Tax:        "+calculator.getTax());
		totalLabel.setText("Total:      "+(calculator.getTotal()));
		repaint();
	}

}

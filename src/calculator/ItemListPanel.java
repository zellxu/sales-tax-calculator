package calculator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 * JPanel that displays all items in the cart.
 * @author Xiang
 * @version 1.0
 *
 */
public class ItemListPanel extends JPanel implements ActionListener {

	private final int WIDTH=500,HEIGHT=500;
	private JButton delete;
	private Calculator calculator;
	private ItemTableModel itm;
	private JTable table;
	private int counter=0;
	private JScrollPane scroll;

	/**
	 * Creates a JPanel that will display all items in the cart.
	 * @param calculator	The calculator object that contains all the items
	 */
	public ItemListPanel(Calculator calculator) {
		this.calculator=calculator;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.add(Box.createRigidArea(new Dimension(0,5)));

		//top action panel
		JLabel itemListLabel = new JLabel("Item List");
		itemListLabel.setFont(new Font("Open Sans",Font.BOLD,20));
		JPanel container = new JPanel();
		container.setMaximumSize(new Dimension(1920,50));
		container.setLayout(new BorderLayout());
		container.add(itemListLabel,BorderLayout.CENTER);

		delete = new JButton("Delete Selected Rows");
		delete.addActionListener(this);
		delete.setFocusable(false);
		container.add(delete, BorderLayout.EAST);
		this.add(container);
		this.add(Box.createRigidArea(new Dimension(0,10)));

		//table panel
		itm = new ItemTableModel();
		table = new JTable(itm);	

		//Add a scroll pane for the table
		scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		//Disable dragging the column to change order
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);

		this.add(scroll);
		this.add(Box.createRigidArea(new Dimension(0,10)));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] rows = table.getSelectedRows();
		if(rows.length>0) {
			int n;
			if(rows.length>1)
				n=JOptionPane.showConfirmDialog(this, "Are you sure to delete these rows?", "Are you sure?", JOptionPane.YES_NO_OPTION);
			else
				n=JOptionPane.showConfirmDialog(this, "Are you sure to delete this row?", "Are you sure?", JOptionPane.YES_NO_OPTION);
			if(n!=0)
				return;
			for(int i : rows) {
				calculator.remove(i-counter);
				itm.removeRow(i-counter);
				counter++;
			}
			((CalculatorPanel) SwingUtilities.getAncestorOfClass(CalculatorPanel.class, this)).updateLabel();
			counter=0;
		}
	}

	/**
	 * Adds the given an item to the table
	 * @param item	the item to add
	 */
	public void addRow(Item item) {
		itm.addRow(item.toArray());
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
	}

	/**
	 * Adds all items in the given ArrayList to the table.
	 * @param items ArrayList containing all items to add
	 */
	public void addRows(ArrayList<Item> items) {
		for(Item i : items)
			itm.addRow(i.toArray());
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
	}

	/**
	 * This class overides the deafult behavior of the DeafaultTableModel
	 * including isCellEditable, columnNames etc.
	 * @author Xiang
	 * @version 1.0
	 *
	 */
	private class ItemTableModel extends DefaultTableModel {
		private String[] columnNames = new String[] {"Quantity","Category","Price"};

		private ItemTableModel() {
			super();
			this.setColumnIdentifiers(columnNames);
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}
}

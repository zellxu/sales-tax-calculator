package calculator;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Data class that represents the actual calculator in this problem set.
 * The calculator has a list of items and will calculate the subtotal,
 * the sum of all individual item's tax values, and the total cost.
 * @author Xiang
 * @version 1.0
 *
 */
public class Calculator {

	private BigDecimal subtotal,tax;
	private ArrayList<Item> items;

	/**
	 * Creates a calculator with empty item and 0 total cost.
	 */
	public Calculator() {
		items = new ArrayList<Item>();
		subtotal = BigDecimal.ZERO;
		tax = BigDecimal.ZERO;
	}

	/**
	 * Returns the subtotal of all items.
	 * @return	the subtotal of all items
	 */
	public BigDecimal getSubTotal() {
		return subtotal;
	}

	/**
	 * Returns the sum of all sales tax for each individual item.
	 * @return	the sum of all sales tax for each individual item
	 */
	public BigDecimal getTax() {
		return round(tax);
	}

	/**
	 * Returns the total cost of all items.
	 * @return	the total cost of all items
	 */
	public BigDecimal getTotal() {
		return subtotal.add(getTax());
	}

	/**
	 * Round the given number to the nearest 0.05 amount.
	 * @param number	the number to be rounded
	 * @return	the rounded value of the given number
	 */
	private BigDecimal round(BigDecimal number) {
		BigDecimal round = number.setScale(1, BigDecimal.ROUND_HALF_UP);
		if(round.compareTo(number)>=0) 
			return round.setScale(2, BigDecimal.ROUND_UP);
		return round.add(new BigDecimal("0.05"));
	}

	/**
	 * Returns all items in an ArrayList.
	 * @return	all items in an ArrayList
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Adds the specified item into the list of items.
	 * @param item	The item to be added
	 */
	public void add(Item item) {
		items.add(item);
		subtotal=subtotal.add(item.getPrice());
		tax=tax.add(item.getTax());
	}

	/**
	 * Removes the specified item index from the list of items.
	 * @param i	The specified item index to be removed
	 */
	public void remove(int i) {
		Item item = items.get(i);
		items.remove(i);
		subtotal=subtotal.subtract(item.getPrice());
		tax=tax.subtract(item.getTax());
	}

}

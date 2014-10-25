package calculator;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Data class that represents an item.
 * Item only has categories defined in the problem set for simplicity assumption.
 * @author Xiang
 * @version 1.0
 *
 */
public class Item {

	private BigDecimal quantity, price, tax;
	private Category category;

	public enum Category {
		BOOK("Book"), MUSICCD("Music CD"), CHOCOLATEBAR("Chocolate Bar"), IMPORTEDCHOCOLATE("Imported box of chocolates"),
		IMPORTEDPERFUME("Imported bottle of perfume"), PERFUME("Bottle of perfume"), HEADACHEPILL("Headache pill");

		private String name;
		private static final HashMap<String, Category> REVERSE;
		static {
			REVERSE = new HashMap<String, Category>();
			for (Category c : Category.values()) {
				REVERSE.put(c.name, c);
				REVERSE.put(c.name.toLowerCase(), c);
			}
		}

		Category(String name) {
			this.name = name;
		}

		public String toString() {
			return name;
		}

		public static Category categoryOf(String s) { 
			return REVERSE.get(s);
		}
	}

	/**
	 * Creates an item with the specified quantity, category and price.
	 * @param quantity	The quantity of this item
	 * @param category	The category of this item
	 * @param price	The price of this item
	 */
	public Item(String quantity, Category category, String price) {
		this.quantity = new BigDecimal(quantity);
		this.category=category;
		this.price = new BigDecimal(price);
		switch(category) {
		case MUSICCD:
		case PERFUME:
			tax = this.price.multiply(new BigDecimal("0.1"));
			break;
		case IMPORTEDCHOCOLATE:
			tax = this.price.multiply(new BigDecimal("0.05"));
			break;
		case IMPORTEDPERFUME:
			tax = this.price.multiply(new BigDecimal("0.15"));
			break;
		default:
			tax=BigDecimal.ZERO;
		}
		tax = tax.multiply(this.quantity);
	}

	/**
	 * Returns the quantity of this item.
	 * @return the quantity of this item
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}

	/**
	 * Returns the price of this item.
	 * @return	the price of this item
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Returns the taxable value of this item.
	 * @return	the taxable value of this item
	 */
	public BigDecimal getTax() {
		return tax;
	}

	/**
	 * Returns the quantity, category and price of this item in an Object array.
	 * @return the quantity, category and price of this item in an Object array
	 */
	public Object[] toArray() {
		return new Object[] {quantity,category,price};
	}

}

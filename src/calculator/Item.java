package calculator;
import java.math.BigDecimal;
import java.util.HashMap;

public class Item {
	private BigDecimal quantity, price, tax;
	private Category category;
	
	enum Category {
		BOOK("Book"), MUSICCD("Music CD"), CHOCOLATEBAR("Chocolate Bar"), IMPORTEDCHOCOLATE("Imported box of chocolates"),
		IMPORTEDPERFUME("Imported bottle of perfume"), PERFUME("Bottle of perfume"), HEADACHEPILL("Headache pill");
		
		private String name;
		private static final HashMap<String, Category> REVERSE;
		static{
			REVERSE = new HashMap<String, Category>();
			for (Category c : Category.values()){
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
		
		public static Category categoryOf(String s){ 
			return REVERSE.get(s);
		}
	}
	
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
				tax=new BigDecimal(0);;
		}
		tax = tax.multiply(this.quantity);
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public BigDecimal getTax() {
		return tax;
	}
	
	public Object[] toArray() {
		return new Object[] {quantity,category,price};
	}
}

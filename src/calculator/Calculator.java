package calculator;
import java.math.BigDecimal;
import java.util.ArrayList;


public class Calculator {
	private BigDecimal subtotal,tax;

	private ArrayList<Item> items;
	
	public Calculator() {
		items = new ArrayList<Item>();
		subtotal = new BigDecimal("0");
		tax = new BigDecimal("0");
	}

	public BigDecimal getSubTotal() {
		return subtotal;
	}

	public BigDecimal getTax() {
		return round(tax);
	}
	
	public BigDecimal getTotal() {
		return subtotal.add(getTax());
	}
	
	private BigDecimal round(BigDecimal number) {
		BigDecimal round = number.setScale(1, BigDecimal.ROUND_HALF_UP);
		if(round.compareTo(number)>=0) 
			return round.setScale(2, BigDecimal.ROUND_UP);
		return round.add(new BigDecimal("0.05"));
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void add(Item item) {
		items.add(item);
		subtotal=subtotal.add(item.getPrice());
		tax=tax.add(item.getTax());
	}

	public void remove(int i) {
		Item item = items.get(i);
		items.remove(i);
		subtotal=subtotal.subtract(item.getPrice());
		tax=tax.subtract(item.getTax());
	}

}

import java.util.ArrayList;


public class Calculator {
	private double subtotal,tax,total;

	private ArrayList<Item> items;
	
	public Calculator(){
		items = new ArrayList<Item>();
		subtotal=0;
		tax=0;
		total=0;
	}

	public double getSubTotal() {
		return subtotal;
	}

	public double getTax() {
		return tax;
	}

	public double getTotal() {
		return total;
	}

	public ArrayList<Item> getItems() {
		return items;
	}
}

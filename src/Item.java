import javax.swing.ComboBoxModel;


public class Item {
	private String name;
	private double price;
	private Category category;
	
	enum Category{
		BOOK("Book"), MUSICCD("Music CD"), CHOCOLATEBAR("Chocolate Bar"), IMPORTEDCHOCOLATE("Imported box of chocolates"),
		IMPORTEDPERFUME("Imported bottle of perfume"), PERFUME("Bottle of perfume"), HEADACHEPILL("Headache pill");
		
		private String name;
		Category(String name){
			this.name = name;
		}
		
		public String toString(){
			return name;
		}
	}
	
	public Item(String name, Category category, double price){
		this.name = name;
		this.category=category;
		this.price = price;
	}
}

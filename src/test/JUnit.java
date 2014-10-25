package test;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import calculator.Calculator;
import calculator.Item;

/**
 * Unit test cases for the Calculator app
 * @author Xiang
 * @version 1.0
 *
 */
public class JUnit {
	private static final int TIMEOUT = 3000;
	private String comment;
	private Calculator calculator;
	private Item item;

	@Before
	public void setUp(){
		calculator = new Calculator();
	}

	@Test(timeout=TIMEOUT)
	public void itemTest01() {
		comment = "item with 0 quantity";
		item = new Item(""+0, Item.Category.BOOK, ""+1.5);
		assertNotNull(comment, item);
	}

	@Test(timeout=TIMEOUT)
	public void itemTest02() {
		comment = "item with 0 price";
		item = new Item(""+1, Item.Category.BOOK, ""+0);
		assertNotNull(comment, item);
	}

	@Test(timeout=TIMEOUT)
	public void itemTest03() {
		comment = "item with large quantity";
		item = new Item("18446744073709551616", Item.Category.BOOK, ""+1.5);
		assertNotNull(comment, item);
		assertEquals(comment, item.getQuantity(), new BigDecimal("18446744073709551616"));
	}

	@Test(timeout=TIMEOUT)
	public void itemTest04() {
		comment = "item tax case 1";
		item = new Item(""+1, Item.Category.BOOK, ""+1);
		calculator.add(item);
		assertEquals(comment, item.getTax(), BigDecimal.ZERO);
	}

	@Test(timeout=TIMEOUT)
	public void itemTest05() {
		comment = "item tax case 2";
		item = new Item(""+1, Item.Category.PERFUME, ""+1);
		calculator.add(item);
		assertEquals(comment, item.getTax(), new BigDecimal("0.1"));
	}

	@Test(timeout=TIMEOUT)
	public void itemTest06() {
		comment = "item tax case 3";
		item = new Item(""+1, Item.Category.IMPORTEDPERFUME, ""+1);
		calculator.add(item);
		assertEquals(comment, item.getTax(), new BigDecimal("0.15"));
	}

	@Test(timeout=TIMEOUT)
	public void itemTest07() {
		comment = "item tax case 4";
		item = new Item(""+1, Item.Category.IMPORTEDCHOCOLATE, ""+1);
		calculator.add(item);
		assertEquals(comment, item.getTax(), new BigDecimal("0.05"));
	}


	@Test(timeout=TIMEOUT)
	public void calculatorTest01() {
		comment = "calculator initialization";
		assertNotNull(comment, calculator);
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest02() {
		comment = "add item";
		item = new Item(""+1, Item.Category.BOOK, ""+0);
		calculator.add(item);
		assertNotNull(comment, calculator.getItems().get(0));
		assertEquals(comment, calculator.getItems().get(0).getQuantity(), item.getQuantity());
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest03() {
		comment = "remove item";
		item = new Item(""+1, Item.Category.BOOK, ""+0);
		calculator.add(item);
		calculator.remove(0);
		assertEquals(comment, calculator.getItems().size(), 0);
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest04() {
		comment = "calculator tax case 1";
		item = new Item(""+1, Item.Category.BOOK, ""+1);
		calculator.add(item);
		assertEquals(comment, calculator.getTax(), BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_UNNECESSARY));
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest05() {
		comment = "calculator tax case 2";
		item = new Item(""+1, Item.Category.PERFUME, ""+1);
		calculator.add(item);
		assertEquals(comment, calculator.getTax(), new BigDecimal("0.10").setScale(2, BigDecimal.ROUND_UNNECESSARY));
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest06() {
		comment = "calculator tax case 3";
		item = new Item(""+1, Item.Category.IMPORTEDPERFUME, ""+1);
		calculator.add(item);
		assertEquals(comment, calculator.getTax(), new BigDecimal("0.20").setScale(2, BigDecimal.ROUND_UNNECESSARY));
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest07() {
		comment = "calculator tax case 4";
		item = new Item(""+1, Item.Category.IMPORTEDCHOCOLATE, ""+1);
		calculator.add(item);
		assertEquals(comment, calculator.getTax(), new BigDecimal("0.10").setScale(2, BigDecimal.ROUND_UNNECESSARY));
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest08() {
		comment = "calculator tax no round up";
		item = new Item(""+1, Item.Category.PERFUME, ""+1.4);
		calculator.add(item);
		assertEquals(comment, calculator.getTax(), new BigDecimal("0.15").setScale(2, BigDecimal.ROUND_UNNECESSARY));
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest09() {
		comment = "calculator tax round up";
		item = new Item(""+1, Item.Category.PERFUME, ""+1.5);
		calculator.add(item);
		assertEquals(comment, calculator.getTax(), new BigDecimal("0.20").setScale(2, BigDecimal.ROUND_UNNECESSARY));
	}

	@Test(timeout=TIMEOUT)
	public void calculatorTest10() {
		comment = "calculator total";
		item = new Item(""+1, Item.Category.PERFUME, ""+1.51111);
		calculator.add(item);
		assertEquals(comment, calculator.getTotal(), new BigDecimal("1.71111"));
	}
}

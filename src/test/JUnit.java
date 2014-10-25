package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import calculator.Calculator;

/**
 * Unit test cases for the CFG generated
 * @author xiangxu
 * @version 1.0
 *
 */
public class JUnit {
	private static final int TIMEOUT = 3000;
	private String comment;
	private Calculator calculator;
	
	@Before
	public void setUp(){
		calculator = new Calculator();
	}
	
	
	@Test(timeout=TIMEOUT)
	public void test01() {
		comment = "test simple assignments";
		
		assertTrue(comment, true);
	}
	
}

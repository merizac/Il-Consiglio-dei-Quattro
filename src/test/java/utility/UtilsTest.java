package utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testIsNumericTrue() {
		assertTrue(Utils.isNumeric("2"));
	}
	
	@Test
	public void testIsNumericFalse() {
		assertFalse(Utils.isNumeric("A"));
	}
}

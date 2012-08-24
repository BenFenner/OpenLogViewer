/**
 *
 */
package org.diyefi.openlogviewer.utils;

import junit.framework.TestCase;

public class SigFigUtilsTest extends TestCase {

	public SigFigUtilsTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testNothing(){
		assertTrue(true);
	}
	
//	public void testRound(){
//		int sigFigs = -1;
//		assertEquals("", SigFigUtils.round(1337D, sigFigs));
//
//		sigFigs = 0;
//		assertEquals("", SigFigUtils.round(1337D, sigFigs));
//
//		sigFigs = 1;
//		assertEquals("0", SigFigUtils.round(0D, sigFigs));
//		assertEquals("1", SigFigUtils.round(1D, sigFigs));
//		assertEquals("2", SigFigUtils.round(2D, sigFigs));
//		assertEquals("5", SigFigUtils.round(5D, sigFigs));
//		assertEquals("9", SigFigUtils.round(9D, sigFigs));
//		assertEquals("10", SigFigUtils.round(10D, sigFigs));
//		assertEquals("10", SigFigUtils.round(11D, sigFigs));
//		assertEquals("20", SigFigUtils.round(15D, sigFigs));
//		assertEquals("20", SigFigUtils.round(16D, sigFigs));
//		assertEquals("20", SigFigUtils.round(19D, sigFigs));
//		assertEquals("20", SigFigUtils.round(20D, sigFigs));
//		assertEquals("20", SigFigUtils.round(21D, sigFigs));
//		assertEquals("0.5", SigFigUtils.round(0.5D, sigFigs));
//		assertEquals("0.3", SigFigUtils.round(1D/3D, sigFigs));
//		assertEquals("0.7", SigFigUtils.round(2D/3D, sigFigs));
//		assertEquals("1", SigFigUtils.round(0.99D, sigFigs));
//		assertEquals("1", SigFigUtils.round(0.999D, sigFigs));
//		assertEquals("1", SigFigUtils.round(0.9999D, sigFigs));
//		assertEquals("2", SigFigUtils.round(1.5D, sigFigs));
//		assertEquals("100000000000000000", SigFigUtils.round(123456789123456789D, sigFigs));
//
//		assertEquals("0", SigFigUtils.round(-0D, sigFigs));
//		assertEquals("-1", SigFigUtils.round(-1D, sigFigs));
//		assertEquals("-2", SigFigUtils.round(-2D, sigFigs));
//		assertEquals("-5", SigFigUtils.round(-5D, sigFigs));
//		assertEquals("-9", SigFigUtils.round(-9D, sigFigs));
//		assertEquals("-10", SigFigUtils.round(-10D, sigFigs));
//		assertEquals("-10", SigFigUtils.round(-11D, sigFigs));
//		assertEquals("-20", SigFigUtils.round(-15D, sigFigs));
//		assertEquals("-20", SigFigUtils.round(-16D, sigFigs));
//		assertEquals("-20", SigFigUtils.round(-19D, sigFigs));
//		assertEquals("-20", SigFigUtils.round(-20D, sigFigs));
//		assertEquals("-20", SigFigUtils.round(-21D, sigFigs));
//		assertEquals("-0.5", SigFigUtils.round(-0.5D, sigFigs));
//		assertEquals("-0.3", SigFigUtils.round(-1D/3D, sigFigs));
//		assertEquals("-0.7", SigFigUtils.round(-2D/3D, sigFigs));
//		assertEquals("-1", SigFigUtils.round(-0.99D, sigFigs));
//		assertEquals("-1", SigFigUtils.round(-0.999D, sigFigs));
//		assertEquals("-1", SigFigUtils.round(-0.9999D, sigFigs));
//		assertEquals("-2", SigFigUtils.round(-1.5D, sigFigs));
//		assertEquals("-100000000000000000", SigFigUtils.round(-123456789123456789D, sigFigs));
//
//
//		sigFigs = 2;
//		assertEquals("0.00", SigFigUtils.round(0D, sigFigs));
//		assertEquals("1.0", SigFigUtils.round(1D, sigFigs));
//		assertEquals("2.0", SigFigUtils.round(2D, sigFigs));
//		assertEquals("5.0", SigFigUtils.round(5D, sigFigs));
//		assertEquals("9.0", SigFigUtils.round(9D, sigFigs));
//		assertEquals("10", SigFigUtils.round(10D, sigFigs));
//		assertEquals("11", SigFigUtils.round(11D, sigFigs));
//		assertEquals("15", SigFigUtils.round(15D, sigFigs));
//		assertEquals("16", SigFigUtils.round(16D, sigFigs));
//		assertEquals("19", SigFigUtils.round(19D, sigFigs));
//		assertEquals("20", SigFigUtils.round(20D, sigFigs));
//		assertEquals("21", SigFigUtils.round(21D, sigFigs));
//		assertEquals("0.50", SigFigUtils.round(0.5D, sigFigs));
//		assertEquals("0.33", SigFigUtils.round(1D/3D, sigFigs));
//		assertEquals("0.67", SigFigUtils.round(2D/3D, sigFigs));
//		assertEquals("0.99", SigFigUtils.round(0.99D, sigFigs));
//		assertEquals("1.0", SigFigUtils.round(0.999D, sigFigs));
//		assertEquals("1.0", SigFigUtils.round(0.9999D, sigFigs));
//		assertEquals("1.5", SigFigUtils.round(1.5D, sigFigs));
//		assertEquals("120000000000000000", SigFigUtils.round(123456789123456789D, sigFigs));
//
//		assertEquals("0.00", SigFigUtils.round(-0D, sigFigs));
//		assertEquals("-1", SigFigUtils.round(-1D, sigFigs));
//		assertEquals("-2", SigFigUtils.round(-2D, sigFigs));
//		assertEquals("-5", SigFigUtils.round(-5D, sigFigs));
//		assertEquals("-9", SigFigUtils.round(-9D, sigFigs));
//		assertEquals("-10", SigFigUtils.round(-10D, sigFigs));
//		assertEquals("-11", SigFigUtils.round(-11D, sigFigs));
//		assertEquals("-15", SigFigUtils.round(-15D, sigFigs));
//		assertEquals("-16", SigFigUtils.round(-16D, sigFigs));
//		assertEquals("-19", SigFigUtils.round(-19D, sigFigs));
//		assertEquals("-20", SigFigUtils.round(-20D, sigFigs));
//		assertEquals("-21", SigFigUtils.round(-21D, sigFigs));
//		assertEquals("-0.5", SigFigUtils.round(-0.5D, sigFigs));
//		assertEquals("-0.33", SigFigUtils.round(-1D/3D, sigFigs));
//		assertEquals("-0.67", SigFigUtils.round(-2D/3D, sigFigs));
//		assertEquals("-0.99", SigFigUtils.round(-0.99D, sigFigs));
//		assertEquals("-1.0", SigFigUtils.round(-0.999D, sigFigs));
//		assertEquals("-1.0", SigFigUtils.round(-0.9999D, sigFigs));
//		assertEquals("-1.5", SigFigUtils.round(-1.5D, sigFigs));
//		assertEquals("-120000000000000000", SigFigUtils.round(-123456789123456789D, sigFigs));
//
//
//		sigFigs = 6;
//		assertEquals("0.000000", SigFigUtils.round(0D, sigFigs));
//		assertEquals("1.00000", SigFigUtils.round(1D, sigFigs));
//		assertEquals("2.00000", SigFigUtils.round(2D, sigFigs));
//		assertEquals("5.00000", SigFigUtils.round(5D, sigFigs));
//		assertEquals("9.00000", SigFigUtils.round(9D, sigFigs));
//		assertEquals("10.0000", SigFigUtils.round(10D, sigFigs));
//		assertEquals("11.0000", SigFigUtils.round(11D, sigFigs));
//		assertEquals("15.0000", SigFigUtils.round(15D, sigFigs));
//		assertEquals("16.0000", SigFigUtils.round(16D, sigFigs));
//		assertEquals("19.0000", SigFigUtils.round(19D, sigFigs));
//		assertEquals("20.0000", SigFigUtils.round(20D, sigFigs));
//		assertEquals("21.0000", SigFigUtils.round(21D, sigFigs));
//		assertEquals("0.500000", SigFigUtils.round(0.5D, sigFigs));
//		assertEquals("0.333333", SigFigUtils.round(1D/3D, sigFigs));
//		assertEquals("0.666667", SigFigUtils.round(2D/3D, sigFigs));
//		assertEquals("0.990000", SigFigUtils.round(0.99D, sigFigs));
//		assertEquals("0.999000", SigFigUtils.round(0.999D, sigFigs));
//		assertEquals("0.999900", SigFigUtils.round(0.9999D, sigFigs));
//		assertEquals("1.50000", SigFigUtils.round(1.5D, sigFigs));
//		assertEquals("123457000000000000", SigFigUtils.round(123456789123456789D, sigFigs));
//
//		assertEquals("0.000000", SigFigUtils.round(-0D, sigFigs));
//		assertEquals("-1.00000", SigFigUtils.round(-1D, sigFigs));
//		assertEquals("-2.00000", SigFigUtils.round(-2D, sigFigs));
//		assertEquals("-5.00000", SigFigUtils.round(-5D, sigFigs));
//		assertEquals("-9.00000", SigFigUtils.round(-9D, sigFigs));
//		assertEquals("-10.0000", SigFigUtils.round(-10D, sigFigs));
//		assertEquals("-11.0000", SigFigUtils.round(-11D, sigFigs));
//		assertEquals("-15.0000", SigFigUtils.round(-15D, sigFigs));
//		assertEquals("-16.0000", SigFigUtils.round(-16D, sigFigs));
//		assertEquals("-19.0000", SigFigUtils.round(-19D, sigFigs));
//		assertEquals("-20.0000", SigFigUtils.round(-20D, sigFigs));
//		assertEquals("-21.0000", SigFigUtils.round(-21D, sigFigs));
//		assertEquals("-0.500000", SigFigUtils.round(-0.5D, sigFigs));
//		assertEquals("-0.333333", SigFigUtils.round(-1D/3D, sigFigs));
//		assertEquals("-0.666667", SigFigUtils.round(-2D/3D, sigFigs));
//		assertEquals("-0.990000", SigFigUtils.round(-0.99D, sigFigs));
//		assertEquals("-0.999000", SigFigUtils.round(-0.999D, sigFigs));
//		assertEquals("-0.999900", SigFigUtils.round(-0.9999D, sigFigs));
//		assertEquals("-1.50000", SigFigUtils.round(-1.5D, sigFigs));
//		assertEquals("-123457000000000000", SigFigUtils.round(-123456789123456789D, sigFigs));
//	}
//
//	public void testTruncate(){
//		int sigFigs = -1;
//		assertEquals("", SigFigUtils.truncate(1337D, sigFigs));
//
//		sigFigs = 0;
//		assertEquals("", SigFigUtils.truncate(1337D, sigFigs));
//
//		sigFigs = 1;
//		assertEquals("0", SigFigUtils.truncate(0D, sigFigs));
//		assertEquals("1", SigFigUtils.truncate(1D, sigFigs));
//		assertEquals("2", SigFigUtils.truncate(2D, sigFigs));
//		assertEquals("5", SigFigUtils.truncate(5D, sigFigs));
//		assertEquals("9", SigFigUtils.truncate(9D, sigFigs));
//		assertEquals("10", SigFigUtils.truncate(10D, sigFigs));
//		assertEquals("10", SigFigUtils.truncate(11D, sigFigs));
//		assertEquals("10", SigFigUtils.truncate(15D, sigFigs));
//		assertEquals("10", SigFigUtils.truncate(16D, sigFigs));
//		assertEquals("10", SigFigUtils.truncate(19D, sigFigs));
//		assertEquals("20", SigFigUtils.truncate(20D, sigFigs));
//		assertEquals("20", SigFigUtils.truncate(21D, sigFigs));
//		assertEquals("0.5", SigFigUtils.truncate(0.5D, sigFigs));
//		assertEquals("0.3", SigFigUtils.truncate(1D/3D, sigFigs));
//		assertEquals("0.6", SigFigUtils.truncate(2D/3D, sigFigs));
//		assertEquals("1", SigFigUtils.truncate(1.5D, sigFigs));
//		assertEquals("100000000000000000", SigFigUtils.truncate(123456789123456789D, sigFigs));
//
//		assertEquals("0", SigFigUtils.truncate(-0D, sigFigs));
//		assertEquals("-1", SigFigUtils.truncate(-1D, sigFigs));
//		assertEquals("-2", SigFigUtils.truncate(-2D, sigFigs));
//		assertEquals("-5", SigFigUtils.truncate(-5D, sigFigs));
//		assertEquals("-9", SigFigUtils.truncate(-9D, sigFigs));
//		assertEquals("-10", SigFigUtils.truncate(-10D, sigFigs));
//		assertEquals("-10", SigFigUtils.truncate(-11D, sigFigs));
//		assertEquals("-10", SigFigUtils.truncate(-15D, sigFigs));
//		assertEquals("-10", SigFigUtils.truncate(-16D, sigFigs));
//		assertEquals("-10", SigFigUtils.truncate(-19D, sigFigs));
//		assertEquals("-20", SigFigUtils.truncate(-20D, sigFigs));
//		assertEquals("-20", SigFigUtils.truncate(-21D, sigFigs));
//		assertEquals("-0.5", SigFigUtils.truncate(-0.5D, sigFigs));
//		assertEquals("-0.3", SigFigUtils.truncate(-1D/3D, sigFigs));
//		assertEquals("-0.6", SigFigUtils.truncate(-2D/3D, sigFigs));
//		assertEquals("-1", SigFigUtils.truncate(-1.5D, sigFigs));
//		assertEquals("-100000000000000000", SigFigUtils.truncate(-123456789123456789D, sigFigs));
//
//
//		sigFigs = 2;
//		assertEquals("0", SigFigUtils.truncate(0D, sigFigs));
//		assertEquals("1", SigFigUtils.truncate(1D, sigFigs));
//		assertEquals("2", SigFigUtils.truncate(2D, sigFigs));
//		assertEquals("5", SigFigUtils.truncate(5D, sigFigs));
//		assertEquals("9", SigFigUtils.truncate(9D, sigFigs));
//		assertEquals("10", SigFigUtils.truncate(10D, sigFigs));
//		assertEquals("11", SigFigUtils.truncate(11D, sigFigs));
//		assertEquals("15", SigFigUtils.truncate(15D, sigFigs));
//		assertEquals("16", SigFigUtils.truncate(16D, sigFigs));
//		assertEquals("19", SigFigUtils.truncate(19D, sigFigs));
//		assertEquals("20", SigFigUtils.truncate(20D, sigFigs));
//		assertEquals("21", SigFigUtils.truncate(21D, sigFigs));
//		assertEquals("0.5", SigFigUtils.truncate(0.5D, sigFigs));
//		assertEquals("0.33", SigFigUtils.truncate(1D/3D, sigFigs));
//		assertEquals("0.66", SigFigUtils.truncate(2D/3D, sigFigs));
//		assertEquals("1.5", SigFigUtils.truncate(1.5D, sigFigs));
//		assertEquals("120000000000000000", SigFigUtils.truncate(123456789123456789D, sigFigs));
//
//		assertEquals("0", SigFigUtils.truncate(-0D, sigFigs));
//		assertEquals("-1", SigFigUtils.truncate(-1D, sigFigs));
//		assertEquals("-2", SigFigUtils.truncate(-2D, sigFigs));
//		assertEquals("-5", SigFigUtils.truncate(-5D, sigFigs));
//		assertEquals("-9", SigFigUtils.truncate(-9D, sigFigs));
//		assertEquals("-10", SigFigUtils.truncate(-10D, sigFigs));
//		assertEquals("-11", SigFigUtils.truncate(-11D, sigFigs));
//		assertEquals("-15", SigFigUtils.truncate(-15D, sigFigs));
//		assertEquals("-16", SigFigUtils.truncate(-16D, sigFigs));
//		assertEquals("-19", SigFigUtils.truncate(-19D, sigFigs));
//		assertEquals("-20", SigFigUtils.truncate(-20D, sigFigs));
//		assertEquals("-21", SigFigUtils.truncate(-21D, sigFigs));
//		assertEquals("-0.5", SigFigUtils.truncate(-0.5D, sigFigs));
//		assertEquals("-0.33", SigFigUtils.truncate(-1D/3D, sigFigs));
//		assertEquals("-0.66", SigFigUtils.truncate(-2D/3D, sigFigs));
//		assertEquals("-1.5", SigFigUtils.truncate(-1.5D, sigFigs));
//		assertEquals("-120000000000000000", SigFigUtils.truncate(-123456789123456789D, sigFigs));
//
//
//		sigFigs = 6;
//		assertEquals("0", SigFigUtils.truncate(0D, sigFigs));
//		assertEquals("1", SigFigUtils.truncate(1D, sigFigs));
//		assertEquals("2", SigFigUtils.truncate(2D, sigFigs));
//		assertEquals("5", SigFigUtils.truncate(5D, sigFigs));
//		assertEquals("9", SigFigUtils.truncate(9D, sigFigs));
//		assertEquals("10", SigFigUtils.truncate(10D, sigFigs));
//		assertEquals("11", SigFigUtils.truncate(11D, sigFigs));
//		assertEquals("15", SigFigUtils.truncate(15D, sigFigs));
//		assertEquals("16", SigFigUtils.truncate(16D, sigFigs));
//		assertEquals("19", SigFigUtils.truncate(19D, sigFigs));
//		assertEquals("20", SigFigUtils.truncate(20D, sigFigs));
//		assertEquals("21", SigFigUtils.truncate(21D, sigFigs));
//		assertEquals("0.5", SigFigUtils.truncate(0.5D, sigFigs));
//		assertEquals("0.333333", SigFigUtils.truncate(1D/3D, sigFigs));
//		assertEquals("0.666666", SigFigUtils.truncate(2D/3D, sigFigs));
//		assertEquals("1.5", SigFigUtils.truncate(1.5D, sigFigs));
//		assertEquals("123456000000000000", SigFigUtils.truncate(123456789123456789D, sigFigs));
//
//		assertEquals("0", SigFigUtils.truncate(-0D, sigFigs));
//		assertEquals("-1", SigFigUtils.truncate(-1D, sigFigs));
//		assertEquals("-2", SigFigUtils.truncate(-2D, sigFigs));
//		assertEquals("-5", SigFigUtils.truncate(-5D, sigFigs));
//		assertEquals("-9", SigFigUtils.truncate(-9D, sigFigs));
//		assertEquals("-10", SigFigUtils.truncate(-10D, sigFigs));
//		assertEquals("-11", SigFigUtils.truncate(-11D, sigFigs));
//		assertEquals("-15", SigFigUtils.truncate(-15D, sigFigs));
//		assertEquals("-16", SigFigUtils.truncate(-16D, sigFigs));
//		assertEquals("-19", SigFigUtils.truncate(-19D, sigFigs));
//		assertEquals("-20", SigFigUtils.truncate(-20D, sigFigs));
//		assertEquals("-21", SigFigUtils.truncate(-21D, sigFigs));
//		assertEquals("-0.5", SigFigUtils.truncate(-0.5D, sigFigs));
//		assertEquals("-0.333333", SigFigUtils.truncate(-1D/3D, sigFigs));
//		assertEquals("-0.666666", SigFigUtils.truncate(-2D/3D, sigFigs));
//		assertEquals("-1.5", SigFigUtils.truncate(-1.5D, sigFigs));
//		assertEquals("-123456000000000000", SigFigUtils.truncate(-123456789123456789D, sigFigs));
//	}
}

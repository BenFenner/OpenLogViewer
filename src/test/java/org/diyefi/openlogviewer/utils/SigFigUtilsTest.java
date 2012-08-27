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

	public void testRoundDecimalPlaces(){
		int sigFigs = -1;
		assertEquals("0", SigFigUtils.roundDecimalPlaces(0D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(1D/3D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(0.5D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(2D/3D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(1.3333333D, sigFigs));
		assertEquals("2", SigFigUtils.roundDecimalPlaces(1.5D, sigFigs));
		assertEquals("2", SigFigUtils.roundDecimalPlaces(1.6666666D, sigFigs));
		assertEquals("22", SigFigUtils.roundDecimalPlaces(22.3333333D, sigFigs));
		assertEquals("23", SigFigUtils.roundDecimalPlaces(22.6666666D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(0.99D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(0.999D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(0.9999D, sigFigs));
		assertEquals("12345678912345678", SigFigUtils.roundDecimalPlaces(12345678912345678D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(0.0001234D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(0.0006789D, sigFigs));

		assertEquals("0", SigFigUtils.roundDecimalPlaces(-0D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(-1D/3D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(-0.5D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-2D/3D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-1.3333333D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-1.5D, sigFigs));
		assertEquals("-2", SigFigUtils.roundDecimalPlaces(-1.6666666D, sigFigs));
		assertEquals("-22", SigFigUtils.roundDecimalPlaces(-22.3333333D, sigFigs));
		assertEquals("-23", SigFigUtils.roundDecimalPlaces(-22.6666666D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-0.99D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-0.999D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-0.9999D, sigFigs));
		assertEquals("-12345678912345678", SigFigUtils.roundDecimalPlaces(-12345678912345678D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(-0.0001234D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(-0.0006789D, sigFigs));

		sigFigs = 0;
		assertEquals("0", SigFigUtils.roundDecimalPlaces(0D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(1D/3D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(0.5D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(2D/3D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(1.3333333D, sigFigs));
		assertEquals("2", SigFigUtils.roundDecimalPlaces(1.5D, sigFigs));
		assertEquals("2", SigFigUtils.roundDecimalPlaces(1.6666666D, sigFigs));
		assertEquals("22", SigFigUtils.roundDecimalPlaces(22.3333333D, sigFigs));
		assertEquals("23", SigFigUtils.roundDecimalPlaces(22.6666666D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(0.99D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(0.999D, sigFigs));
		assertEquals("1", SigFigUtils.roundDecimalPlaces(0.9999D, sigFigs));
		assertEquals("12345678912345678", SigFigUtils.roundDecimalPlaces(12345678912345678D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(0.0001234D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(0.0006789D, sigFigs));

		assertEquals("0", SigFigUtils.roundDecimalPlaces(-0D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(-1D/3D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(-0.5D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-2D/3D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-1.3333333D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-1.5D, sigFigs));
		assertEquals("-2", SigFigUtils.roundDecimalPlaces(-1.6666666D, sigFigs));
		assertEquals("-22", SigFigUtils.roundDecimalPlaces(-22.3333333D, sigFigs));
		assertEquals("-23", SigFigUtils.roundDecimalPlaces(-22.6666666D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-0.99D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-0.999D, sigFigs));
		assertEquals("-1", SigFigUtils.roundDecimalPlaces(-0.9999D, sigFigs));
		assertEquals("-12345678912345678", SigFigUtils.roundDecimalPlaces(-12345678912345678D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(-0.0001234D, sigFigs));
		assertEquals("0", SigFigUtils.roundDecimalPlaces(-0.0006789D, sigFigs));

		sigFigs = 1;
		assertEquals("0.0", SigFigUtils.roundDecimalPlaces(0D, sigFigs));
		assertEquals("0.3", SigFigUtils.roundDecimalPlaces(1D/3D, sigFigs));
		assertEquals("0.5", SigFigUtils.roundDecimalPlaces(0.5D, sigFigs));
		assertEquals("0.7", SigFigUtils.roundDecimalPlaces(2D/3D, sigFigs));
		assertEquals("1.3", SigFigUtils.roundDecimalPlaces(1.3333333D, sigFigs));
		assertEquals("1.5", SigFigUtils.roundDecimalPlaces(1.5D, sigFigs));
		assertEquals("1.7", SigFigUtils.roundDecimalPlaces(1.6666666D, sigFigs));
		assertEquals("22.3", SigFigUtils.roundDecimalPlaces(22.3333333D, sigFigs));
		assertEquals("22.7", SigFigUtils.roundDecimalPlaces(22.6666666D, sigFigs));
		assertEquals("1.0", SigFigUtils.roundDecimalPlaces(0.99D, sigFigs));
		assertEquals("1.0", SigFigUtils.roundDecimalPlaces(0.999D, sigFigs));
		assertEquals("1.0", SigFigUtils.roundDecimalPlaces(0.9999D, sigFigs));
		assertEquals("12345678912345678.0", SigFigUtils.roundDecimalPlaces(12345678912345678D, sigFigs));
		assertEquals("0.0", SigFigUtils.roundDecimalPlaces(0.0001234D, sigFigs));
		assertEquals("0.0", SigFigUtils.roundDecimalPlaces(0.0006789D, sigFigs));

		assertEquals("0.0", SigFigUtils.roundDecimalPlaces(-0D, sigFigs));
		assertEquals("-0.3", SigFigUtils.roundDecimalPlaces(-1D/3D, sigFigs));
		assertEquals("-0.5", SigFigUtils.roundDecimalPlaces(-0.5D, sigFigs));
		assertEquals("-0.7", SigFigUtils.roundDecimalPlaces(-2D/3D, sigFigs));
		assertEquals("-1.3", SigFigUtils.roundDecimalPlaces(-1.3333333D, sigFigs));
		assertEquals("-1.5", SigFigUtils.roundDecimalPlaces(-1.5D, sigFigs));
		assertEquals("-1.7", SigFigUtils.roundDecimalPlaces(-1.6666666D, sigFigs));
		assertEquals("-22.3", SigFigUtils.roundDecimalPlaces(-22.3333333D, sigFigs));
		assertEquals("-22.7", SigFigUtils.roundDecimalPlaces(-22.6666666D, sigFigs));
		assertEquals("-1.0", SigFigUtils.roundDecimalPlaces(-0.99D, sigFigs));
		assertEquals("-1.0", SigFigUtils.roundDecimalPlaces(-0.999D, sigFigs));
		assertEquals("-1.0", SigFigUtils.roundDecimalPlaces(-0.9999D, sigFigs));
		assertEquals("-12345678912345678.0", SigFigUtils.roundDecimalPlaces(-12345678912345678D, sigFigs));
		assertEquals("0.0", SigFigUtils.roundDecimalPlaces(-0.0001234D, sigFigs));
		assertEquals("0.0", SigFigUtils.roundDecimalPlaces(-0.0006789D, sigFigs));


		sigFigs = 2;
		assertEquals("0.00", SigFigUtils.roundDecimalPlaces(0D, sigFigs));
		assertEquals("0.33", SigFigUtils.roundDecimalPlaces(1D/3D, sigFigs));
		assertEquals("0.50", SigFigUtils.roundDecimalPlaces(0.5D, sigFigs));
		assertEquals("0.67", SigFigUtils.roundDecimalPlaces(2D/3D, sigFigs));
		assertEquals("1.33", SigFigUtils.roundDecimalPlaces(1.3333333D, sigFigs));
		assertEquals("1.50", SigFigUtils.roundDecimalPlaces(1.5D, sigFigs));
		assertEquals("1.67", SigFigUtils.roundDecimalPlaces(1.6666666D, sigFigs));
		assertEquals("22.33", SigFigUtils.roundDecimalPlaces(22.3333333D, sigFigs));
		assertEquals("22.67", SigFigUtils.roundDecimalPlaces(22.6666666D, sigFigs));
		assertEquals("0.99", SigFigUtils.roundDecimalPlaces(0.99D, sigFigs));
		assertEquals("1.00", SigFigUtils.roundDecimalPlaces(0.999D, sigFigs));
		assertEquals("1.00", SigFigUtils.roundDecimalPlaces(0.9999D, sigFigs));
		assertEquals("12345678912345678.00", SigFigUtils.roundDecimalPlaces(12345678912345678D, sigFigs));
		assertEquals("0.00", SigFigUtils.roundDecimalPlaces(0.0001234D, sigFigs));
		assertEquals("0.00", SigFigUtils.roundDecimalPlaces(0.0006789D, sigFigs));

		assertEquals("0.00", SigFigUtils.roundDecimalPlaces(-0D, sigFigs));
		assertEquals("-0.33", SigFigUtils.roundDecimalPlaces(-1D/3D, sigFigs));
		assertEquals("-0.50", SigFigUtils.roundDecimalPlaces(-0.5D, sigFigs));
		assertEquals("-0.67", SigFigUtils.roundDecimalPlaces(-2D/3D, sigFigs));
		assertEquals("-1.33", SigFigUtils.roundDecimalPlaces(-1.3333333D, sigFigs));
		assertEquals("-1.50", SigFigUtils.roundDecimalPlaces(-1.5D, sigFigs));
		assertEquals("-1.67", SigFigUtils.roundDecimalPlaces(-1.6666666D, sigFigs));
		assertEquals("-22.33", SigFigUtils.roundDecimalPlaces(-22.3333333D, sigFigs));
		assertEquals("-22.67", SigFigUtils.roundDecimalPlaces(-22.6666666D, sigFigs));
		assertEquals("-0.99", SigFigUtils.roundDecimalPlaces(-0.99D, sigFigs));
		assertEquals("-1.00", SigFigUtils.roundDecimalPlaces(-0.999D, sigFigs));
		assertEquals("-1.00", SigFigUtils.roundDecimalPlaces(-0.9999D, sigFigs));
		assertEquals("-12345678912345678.00", SigFigUtils.roundDecimalPlaces(-12345678912345678D, sigFigs));
		assertEquals("0.00", SigFigUtils.roundDecimalPlaces(-0.0001234D, sigFigs));
		assertEquals("0.00", SigFigUtils.roundDecimalPlaces(-0.0006789D, sigFigs));


		sigFigs = 6;
		assertEquals("0.000000", SigFigUtils.roundDecimalPlaces(0D, sigFigs));
		assertEquals("0.333333", SigFigUtils.roundDecimalPlaces(1D/3D, sigFigs));
		assertEquals("0.500000", SigFigUtils.roundDecimalPlaces(0.5D, sigFigs));
		assertEquals("0.666667", SigFigUtils.roundDecimalPlaces(2D/3D, sigFigs));
		assertEquals("1.333333", SigFigUtils.roundDecimalPlaces(1.3333333D, sigFigs));
		assertEquals("1.500000", SigFigUtils.roundDecimalPlaces(1.5D, sigFigs));
		assertEquals("1.666667", SigFigUtils.roundDecimalPlaces(1.6666666D, sigFigs));
		assertEquals("22.333333", SigFigUtils.roundDecimalPlaces(22.3333333D, sigFigs));
		assertEquals("22.666667", SigFigUtils.roundDecimalPlaces(22.6666666D, sigFigs));
		assertEquals("0.990000", SigFigUtils.roundDecimalPlaces(0.99D, sigFigs));
		assertEquals("0.999000", SigFigUtils.roundDecimalPlaces(0.999D, sigFigs));
		assertEquals("0.999900", SigFigUtils.roundDecimalPlaces(0.9999D, sigFigs));
		assertEquals("12345678912345678.000000", SigFigUtils.roundDecimalPlaces(12345678912345678D, sigFigs));
		assertEquals("0.000123", SigFigUtils.roundDecimalPlaces(0.0001234D, sigFigs));
		assertEquals("0.000679", SigFigUtils.roundDecimalPlaces(0.0006789D, sigFigs));

		assertEquals("0.000000", SigFigUtils.roundDecimalPlaces(-0D, sigFigs));
		assertEquals("-0.333333", SigFigUtils.roundDecimalPlaces(-1D/3D, sigFigs));
		assertEquals("-0.500000", SigFigUtils.roundDecimalPlaces(-0.5D, sigFigs));
		assertEquals("-0.666667", SigFigUtils.roundDecimalPlaces(-2D/3D, sigFigs));
		assertEquals("-1.333333", SigFigUtils.roundDecimalPlaces(-1.3333333D, sigFigs));
		assertEquals("-1.500000", SigFigUtils.roundDecimalPlaces(-1.5D, sigFigs));
		assertEquals("-1.666667", SigFigUtils.roundDecimalPlaces(-1.6666666D, sigFigs));
		assertEquals("-22.333333", SigFigUtils.roundDecimalPlaces(-22.3333333D, sigFigs));
		assertEquals("-22.666667", SigFigUtils.roundDecimalPlaces(-22.6666666D, sigFigs));
		assertEquals("-0.990000", SigFigUtils.roundDecimalPlaces(-0.99D, sigFigs));
		assertEquals("-0.999000", SigFigUtils.roundDecimalPlaces(-0.999D, sigFigs));
		assertEquals("-0.999900", SigFigUtils.roundDecimalPlaces(-0.9999D, sigFigs));
		assertEquals("-12345678912345678.000000", SigFigUtils.roundDecimalPlaces(-12345678912345678D, sigFigs));
		assertEquals("-0.000123", SigFigUtils.roundDecimalPlaces(-0.0001234D, sigFigs));
		assertEquals("-0.000679", SigFigUtils.roundDecimalPlaces(-0.0006789D, sigFigs));
	}

	public void testRoundDecimalPlacesSpeed(){
		long thePast = System.currentTimeMillis();
		int iterations = 0;

		for (int i = 0; i < iterations; i++){
			testRoundDecimalPlaces();
		}
		long elapsedTime = System.currentTimeMillis() - thePast;
		System.out.println("Time to complete " + iterations + " iterations of testRoundDecimalPlaces(): " + elapsedTime + "ms");
	}
}

package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class ComplexFunctionTest2 {

	@Test
	void testComplexFunctionPolynom() {
		String[] polyTest = { "x^3-2x^2+x", "3x^4-4x^3+2", "8-x+x" };
		for (int i = 0; i < polyTest.length; i++) {
			Polynom poly = new Polynom(polyTest[i]);
			ComplexFunction complex = new ComplexFunction(poly);
			// assertEquals(poly,complex); to fix !!
			assertEquals(complex, poly);
		}
	}

	@Test
	void initFromString() {
		String[] complexFunctionString = { "plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)",
				"plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)",
				"div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)",
				"-1.0x^4 +2.4x^2 +3.1", "+0.1x^5 -1.2999999999999998x +5.0",
				"max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)",
				"min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)" };

		for (int j = 0; j < complexFunctionString.length; j++) {
			try {
				ComplexFunction complex = new ComplexFunction();
				complex = (ComplexFunction) complex.initFromString(complexFunctionString[j]);

			} catch (Exception e) {
				fail(complexFunctionString[j]);
			}
		}

	}

	@Test
	void testF() {
		String[] complexFunctionString = { "plus(x^2+5x+6,-x^2-5x-6)", "div(x^2+5x+6,-x^2-5x-6)",
				"times(x^2+5x+6,-x^2-5x-6)", "max(x^2+5x+6,-x^2-5x-6)", "min(x^2+5x+6,-x^2-5x-6)",
				"comp(x^2+5x+6,-x^2-5x-6)" };
		double[] arrAnswer = { 0, -1, -144, 12, -12, 90 };
		for (int i = 0; i < arrAnswer.length; i++) {
			ComplexFunction complex = new ComplexFunction();
			complex = (ComplexFunction) complex.initFromString(complexFunctionString[i]);
			assertEquals(complex.f(1), arrAnswer[i]);
		}
	}

	@Test
	void testCopy() {
		String[] complexFunctionString = { "plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)",
				"plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)",
				"div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)",
				"-1.0x^4 +2.4x^2 +3.1", "+0.1x^5 -1.2999999999999998x +5.0",
				"max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)",
				"min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)" };

		for (int j = 0; j < complexFunctionString.length; j++) {
			try {
				ComplexFunction complex = new ComplexFunction();
				complex = (ComplexFunction) complex.initFromString(complexFunctionString[j]);
				ComplexFunction complexCopy = (ComplexFunction) complex.copy();
				assertEquals(complex,complexCopy);

			} catch (Exception e) {
				fail();
			}
		}

	}



	@Test
	void testEqualsObject() {
		String[] complexFunctionString = {"plus(x^2,x)" ,"-x^5","","plus(plus(plus(1.0x^1,2.0x^2),3.0x^3),4.0x^4)"};
		function[] func = {new Polynom("x^2+x"),new Monom("-x^5"),new Monom(""),new Polynom("4x^4+3x^3+2x^2+x")};
		
		for (int j = 0; j < complexFunctionString.length; j++) {
			try {
				ComplexFunction complex = new ComplexFunction();
				complex = (ComplexFunction) complex.initFromString(complexFunctionString[j]);
				assertEquals(complex,func[j]);

			} catch (Exception e) {
				fail();
			}
		}
	}
	
	
	void testPlus() {//if it works and the f work the all the functions that are the same will work also ( div , mul ..) 
		String[] monomsTest = {"", "x","3.2x^2","-3","-1.5x^2"};
//		function [] complexFunctionTestPlus= {new ComplexFunction(),
//											new Polynom("2x^2+x^3+X"),
//											}
		String textmonoms="plus(x^2+5x+6,-x^2-5x-6)",
				textPolinom="",text="",
				textCF="plus(x^2+5x+6,-x^2-5x-6)";
		ComplexFunction complex2 = new ComplexFunction();
		ComplexFunction complex = new ComplexFunction();
		complex = (ComplexFunction) complex.initFromString("plus(x^2+5x+6,-x^2-5x-6)");
		for (int j = 0; j < monomsTest.length; j++) {
			textmonoms="plus(plus(x^2+5x+6,-x^2-5x-6),"+monomsTest[j]+")";
			try {
				complex2 = (ComplexFunction) complex2.initFromString(textmonoms);
				complex.plus(new Monom(monomsTest[j]));
				assertEquals(complex2,complex);
			} catch (Exception e) {
				fail("testPlus-textmonoms");
		}
			try {
			textPolinom+=monomsTest[j];
			text="plus(plus(x^2+5x+6,-x^2-5x-6),"+textPolinom+")";
			complex2 = (ComplexFunction) complex2.initFromString(text);
			complex.plus(new Polynom(textPolinom));
			assertEquals(complex2,complex);
		} catch (Exception e) {
			fail("testPlus-textPolinom");
	}
		}
	}

}

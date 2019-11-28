package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTestJuinit {

	@Test
	void StringConstructor() {

		// good Monoms
		String[] monomTestArr = { "2", "-x", "-3.2x^2", "0", "2x^233333", "-1", "-0.0X^2", "", "+5x", "-1917" };
		int[] monomPower = { 0, 1, 2, 0, 233333, 0, 2, 0, 1, 0 };
		double[] monomCoff = { 2, -1, -3.2, 0, 2, -1, -0.0, 0, 5, -1917 };
		for (int i = 0; i < monomTestArr.length; i++) {
			Monom testMonom = new Monom(monomTestArr[i]);
			int expectedPower = monomPower[i];
			int actualPower = testMonom.get_power();
			double expectedCoeficient = monomCoff[i];
			double actualCoeficient = testMonom.get_coefficient();
			assertEquals(expectedPower, actualPower, "The Monom:" + monomTestArr[i]);
			assertEquals(expectedCoeficient, actualCoeficient, "The Monom:" + monomTestArr[i]);
			// Eror acurd on +x

		}
		// Bad Monoms
		String[] badMoonomArr = { "2x^2.5", "--x", "-", "4x^3.0", "(2x^3)", "$4x^2", "Boaz",
				"x^22222222222222222222222222222222222222222222222" };

		for (int i = 0; i < badMoonomArr.length; i++) {

			try {
				Monom badMonom = new Monom(badMoonomArr[i]);
				fail("iligal Monom was created  "+badMoonomArr[i]);
			}

			catch (Exception e ) {
			
			}

		}

	}

	@Test
	void copyConstructor() {
		String[] monomTestArr = { "2", "-x", "-3.2x^2", "0", "2x^233333", "-1", "-0.0X^2", "", "x", "-1917" };
		for (int i = 0; i < monomTestArr.length; i++) {
			Monom testMonom = new Monom(monomTestArr[i]);
			Monom copytMonom = new Monom(testMonom);
			assertEquals(testMonom.get_coefficient(), copytMonom.get_coefficient(), testMonom.toString());
			assertEquals(testMonom.get_power(), copytMonom.get_power(), testMonom.toString());
		}

	}

	@Test
	void derivativeConstractor() {
		String[] monomTestArr = { "2", "-x", "-3.2x^2", "5x^8", "0x^2", "x", "x^45", "0", "", };
		int[] monomPower = { 0, 0, 1, 7, 1, 0, 44, 0, 0 };
		double[] monomCoff = { 0, -1, -6.4, 40, 0, 1, 45, 0, 0 };
		for (int i = 0; i < monomTestArr.length; i++) {
			Monom testMonom = new Monom(monomTestArr[i]);
			testMonom = testMonom.derivative();
			int expectedPower = monomPower[i];
			int actualPower = testMonom.get_power();
			double expectedCoeficient = monomCoff[i];
			double actualCoeficient = testMonom.get_coefficient();
			assertEquals(expectedPower, actualPower, monomTestArr[i]);
			assertEquals(expectedCoeficient, actualCoeficient, monomTestArr[i]);
			// Eror acurd on +x

		}

	}
	
	
	@Test
	void fTest() {
		String[] testArrF = { "2", "1.5x^2", "-1.5x^2", "0" };	
		double[] inValueF = { 1, 1.5, -1.5, 0};
		
		
		double[][] afterF = {{2,2,2,2}
							,{1.5,3.375,3.375,0}
							,{-1.5,-3.375,-3.375,-0.0}
							,{0,0,0,0}};
		
		for (int i = 0; i < testArrF.length;i++) {
			for (int j = 0; j < inValueF.length; j++) {
			Monom testMonom = new Monom(testArrF[i]);
			double actual=testMonom.f(inValueF[j]);
			assertEquals(afterF[i][j] ,actual,testArrF[i]+"Value:"+inValueF[j]+"="+actual +"="+afterF[i][j]);
			}
	}
}
	
	@Test
	void addTest() {
		String[] monomTestArr = { "3", "1.5x^2", "-1.5x^3", "0" };	
		String[] monomTOAddArr = { "7", "-2.5x^2", "-1.5x^3", "x^3" };
		String[] exeptedMonomArr = {"10","-1.0x^2","-3.0x^3","x^3"};
		String[] badMonomArr = {"10x^2","-1.0x^3","-3.0x^1","x"};
		for (int i = 0; i < monomTestArr.length; i++) {
			Monom testMonomLeft = new Monom(monomTestArr[i]); 
			Monom testMonomRight = new Monom(monomTOAddArr[i]);
			testMonomLeft.add(testMonomRight); // the add result 
			Monom exeptedMonom = new Monom(exeptedMonomArr[i]);
			assertEquals(exeptedMonom.get_power() ,testMonomLeft.get_power(),"Monom ");
			assertEquals(exeptedMonom.get_coefficient() ,testMonomLeft.get_coefficient(),"Monom : "+monomTestArr[i]+" and Monom "+monomTOAddArr[i]);
		}
		
		for (int i = 0; i < badMonomArr.length-1; i++) {
			Monom testMonom = new Monom(monomTestArr[i]);
			Monom diffPower = new Monom(badMonomArr[i]);
			try {
				testMonom.add(diffPower);
				fail("iligal Monom was added  "+monomTestArr[i]+" "+monomTestArr[i]);
			}

			catch (Exception e ) {
			
			}


		}

	
	}
	
	@Test
	void multyplyTest() {
		String[] monomTestArr = { "3", "1.5x^2", "-1.5x^3", "0" ,"-x^5"};	
		String[] monomTOAddArr = { "7", "-2.5x^3", "-1.5x^3", "x^3","0" };
		String[] exeptedMonomArr = {"21","-3.75x^5","2.25x^6","0","0"};
		for (int i = 0; i < monomTestArr.length; i++) {
			Monom testMonomLeft = new Monom(monomTestArr[i]); 
			Monom testMonomRight = new Monom(monomTOAddArr[i]);
			testMonomLeft.multipy(testMonomRight); // the add result 
			Monom exeptedMonom = new Monom(exeptedMonomArr[i]);
			assertEquals(exeptedMonom.get_power() ,testMonomLeft.get_power(),"Monom ");
			assertEquals(exeptedMonom.get_coefficient() ,testMonomLeft.get_coefficient(),"Monom : "+monomTestArr[i]+" and Monom "+monomTOAddArr[i]);
		}


	
	}
	
	
	
	
	
	
	
	
	
}

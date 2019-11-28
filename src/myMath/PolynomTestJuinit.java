package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class PolynomTestJuinit {
	
	@Test
	void PolynomTest() {
		String[] polyTest= {"x^3-2x^2+x","3x^4-4x^3+2","8-x+x"};
		String[][] afterPolyTest= {{"1x^3","-2x^2","+x"}
								  ,{"3x^4","-4x^3","+2"},
								  {"8","8"}};
		for (int i = 0; i < polyTest.length; i++) {
		Polynom ron = new Polynom(polyTest[i]);
		Iterator nex= ron.iteretor();
		Monom [] afterTest=new Monom[afterPolyTest[i].length];
		for (int j = 0; j < afterPolyTest[i].length; j++) {
			afterTest[j]=new Monom(afterPolyTest[i][j]);
		}
		int sum=0;
		while (nex.hasNext()) {
		assertEquals(afterTest[sum].toString(),ron.polyMap.get(afterTest[sum].get_power()).toString(),"tnt ak snhui ");
		sum++;
		nex.next();
		}
		}
	}
	@Test
	void fTest() {
		String[] testArrF={ "x^2+5x-6", "-x^3-x^2","0", ""};
		Double [] inValueF= { 1.0, 1.5, -1.5, 0.0};
		
		double[][] afterF = {{0,3.75,-11.25,-6}
							,{-2,-5.625,1.125,0}
							,{0,0,0,0}
							,{0,0,0,0}};
		
		for (int i = 0; i < testArrF.length;i++) {
			for (int j = 0; j < inValueF.length; j++) {
			Polynom testMonom = new Polynom(testArrF[i]);
			double actual=testMonom.f(inValueF[j]);
			assertEquals(afterF[i][j] ,actual,testArrF[i]+"Value:"+inValueF[j]+"="+actual +"="+afterF[i][j]);
			}
		}
		
		
		
		
		
		
		
	}
	@Test
	void addTestPolyable() {
		String[] goodPoly = { "4x^3+x+666"               , "-x^2-x"            ,"0"                                  ,"2x^23333333+4x^1+4x^3+4x^3"    , ""                                  , "" };
		String[] toAddArr = { "2x^4+x^2"                 , "-x^2-x"            ,"2x^23333333+4x^1+4x^3+4x^3"         ,  "0"                           ,"" };
		String[] resultArr = { "2x^4+4x^3+x^2+x+666"     , "-2x^2-2x"          , "2x^23333333+4x^1+4x^3+4x^3"        ,"2x^23333333+4x^1+4x^3+4x^3"    , "" };
		for (int i = 1; i < toAddArr.length; i++) {
			Polynom other = new Polynom (toAddArr[i]);
			Polynom_able ableOther=other;
			Polynom expected = new Polynom(resultArr[i]);
			Polynom actual = new Polynom(goodPoly[i]);
			actual.add(ableOther);
			assertEquals(expected.toString(), actual.toString(),"exepted: "+expected.toString()+"actual : "+actual.toString());
			
		}
		
	}
	@Test
	void addTest() {
		String[] goodPoly = { "4x^3+x+666"               , "-x^2-x+4"            ,"2x^23333333+4x^1+4x^3+4x^3"         ,""    , ""   };
		String[] Monoms = { "-4x^3"                      , "x^2"              ,"0"                                   ,"0"    ,"" };
		String[] resultArr = { "x+666"                   , "-x+4"                 , "2x^23333333+4x^1+4x^3+4x^3"        ,"0"    , "" };
		for (int i = 1; i < Monoms.length; i++) {
			Monom mon = new Monom (Monoms[i]);
			Polynom expected = new Polynom(resultArr[i]);
			Polynom actual = new Polynom(goodPoly[i]);
			actual.add(mon);
			assertEquals(expected.toString(), actual.toString(),"exepted: "+expected.toString()+"actual : "+actual.toString());
			
		}
	}
		
		@Test
		void equalsTest() {
			Polynom_able [] actualTest={new Polynom("-4x^3+2x^2+0.999999999999999x"),new Polynom("0.999999999999999x^2+2x"),new Polynom("0.999999999999999x^2+2x")};
			Polynom [] expectedTest={new Polynom("-4x^3+2x^2+1x"),new Polynom("1x^2+2x"),new Polynom("01x^2+2x")};
			Polynom [] expectedTestFail={new Polynom("4x^3+2x^2+1x"),new Polynom("-1x^2+2x"),new Polynom("0")};

			for (int i = 0; i < expectedTest.length; i++) {
			assertEquals(true,expectedTest[i].equals(actualTest[i]),""+i);
			assertEquals(false,expectedTestFail[i].equals(actualTest[i]),""+i);
			}
		}
		
		@Test
		void isZeroTest() {
			Polynom_able [] actualTest={new Polynom("1-1.0000000000001"),new Polynom("0x^8"),new Polynom(""),new Polynom("-0.00000000000001")};
			Polynom_able [] actualTestFail= {new Polynom("0.9999999"),new Polynom("1x^0"),new Polynom("x-x+x-x+x"),new Polynom("1x-1x^0")};
			for (int i = 0; i < actualTest.length; i++) {
				assertEquals(true, actualTest[i].isZero(),"index:"+i+"po:"+actualTest[i].toString());
//				assertEquals(false, actualTestFail[i].isZero(),"index:"+i+"po:"+actualTest[i].toString());
			}
		}
		
		
		
		
		
	
	@Test
	void substractTest() {
		
	}
	@Test
	void multiplyTest() {
		
	}

	@Test
	void rootTest(){
		
	}
	
	@Test
	void copyTest() {
		
	}
	@Test
	void derivativeTest() {
		
	}
	@Test
	void areaTest() {
		
	}
	@Ignore
	void multiplyTest(Monom m1) {
		
	}
	
//	@Test
//	void toString() {
//		
//	}
	
	@Test
	void initFromString(){
		
	}



}

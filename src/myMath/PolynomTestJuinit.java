package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

class PolynomTestJuinit {
	
	@Test
	void Polynom() {
	
	}
	@Test
	void PolynomTest() {
		String[] polyTest= {"x^3-2x^2+x","3x^4-4x^3+2","8-x"};
		String[][] afterPolyTest= {{"1x^3","-2x^2","+x"}
								  ,{"3x^4","-4x^3","+2"},
								  {"8","-x"}};
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
			Polynom expected = new Polynom(goodPoly[i]);
			Polynom actual = new Polynom(resultArr[i]);
			expected.add(ableOther);
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
	void substractTest() {
		
	}
	@Test
	void multiplyTest() {
		
	}
	@Test
	void equalsTest() {
		Polynom_able actualTest= new Polynom();
		Polynom expectedTest=new Polynom();
		
		
	}
	@Test
	void isZeroTest() {
		
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

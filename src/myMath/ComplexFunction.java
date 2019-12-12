package myMath;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.management.RuntimeErrorException;
import org.junit.jupiter.engine.discovery.predicates.IsPotentialTestContainer;



/**
 * ComplexFunction is a function made of connecting simple ( Monom or Polynom ) functions with an Operation with the tree database.
 * altroght ComplexFunction can input an Monom it will change it to Polynom because Polynom contains all the MOnom methods you wont feel the difference as user but it's still important to know .
 * Plus: plus(f1(x), f2(x)),  Times: mul(f1(x), f2(x)), Divid: div(f1(x), f2(x)), Max: max(f1(x), f2(x)), Min: min(f1(x), f2(x)), Comp: comp(f1(x), f2(x)) == f1(f2(x))
 * this class contains multiple methods on this function such as f , equals , and more...
 * the ComplexFunction is the type function and have all the function interface methods included.
 * fore more information read the wiki.
 * @author Simon Piklov and Ron Leib 
 *
 */
public class ComplexFunction implements complex_function {

	
	// the tree values 

	private Operation symbol; // the operation enum
	private Polynom poly; // the Polynom (should be present only if the enum is null ! that mean only in
	// the last level of the tree )
	private ComplexFunction left; // reference the right function
	private ComplexFunction right; // reference the left function
	
	
	/**
	 * ComplexFunction constractor of onre ( Monom or Polynom or ComplexFunction ) functions with an null as operation 
	 * @param func
	 */
	public ComplexFunction(function func ) {
		this.symbol=null;
		if (func instanceof Monom ||func instanceof Polynom ) { // the case func is Monom or polynom (change it to polynom)
			this.poly= new Polynom(func.toString());
			
		}
		else if (func instanceof ComplexFunction) { // the case it's  a ComplexFunction put it to the left 
			
			this.left = (ComplexFunction) func.copy();
		}
		else {
			throw new RuntimeException(
					" You need insert an a Monom ,  Polynom or ComplexFunction !  ");
		}
		
	}
	
	
	//helping function to make the copy in place 
	private void voidCopy (ComplexFunction other) { // use the copy method for copy 
		
		this.left=(ComplexFunction) other.left.copy();
		this.right=(ComplexFunction) other.right.copy();
		this.symbol = other.symbol;
		
	}
	
	/**
	 * ComplexFunction defualt constractor all the tree params as null 
	 */
	public ComplexFunction() {
		this.left = null;
		this.right = null;
		this.symbol = null;
		this.poly = new Polynom("0");

	}
	
	
	/**
	 * ComplexFunction constractor for string and left and right function when the operation can be one of the params listed bellow or null , if it us a nuul only one function can be in the CompleFunction 
	 * @param operation - string of the type : Plus, Times, Divid, Max, Min, Comp  ,times , div ,mul. there is no importance for small or big letters 
	 * @param  function of the the type Monom or Polynom or ComplexFunction
	 * @param  function of the the type Monom or Polynom or ComplexFunction
	 */
	public ComplexFunction (String operation , function left , function right ) { //call the Operation constractor 
		this(ifOperation(operation),left,right);
	
	}

	/**
	 *ComplexFunction contractor for Operation and left and right function when the operation can be one of the params listed bellow or null , if it us a nuul only one function can be in the CompleFunction  
	 * @param symbol Operation :   Plus, Times, Divid, Max, Min, Comp  important ! None and Error are Not supported and if you will put them you will get an exception 
	 * @param left -  function of the the type Monom or Polynom or ComplexFunction
	 * @param right -  function of the the type Monom or Polynom or ComplexFunction
	 */
	public  ComplexFunction(Operation symbol, function left, function right) {

		if ((left == null && right == null && symbol == null)) {
			//if all the params are null make it an empty ComplexFunction
			this.left = null;
			this.right = null;
			this.symbol = null;
			this.poly = new Polynom("0");
			return;
		}
		
		

		if (((left == null || right == null) && symbol != null) || (left != null && right != null && symbol == null)) { // the case it two functions with mo operation between 
			throw new RuntimeException(
					" You cannot insert an null ! insert a Monom ,  Polynom or ComplexFunction bouth to left and right.   ");
		} else {
			if (symbol != null)
				this.symbol = symbol; // make the symbol this symbol 
			/////// left///////
			if (left != null) {
				if (left instanceof Monom || left instanceof Polynom) { // if it's a Polynom or Monom downcast it to
					// Polynom
					Polynom temp = new Polynom(left.toString()); // deep copy it with a string 
					this.left = new ComplexFunction(); // constract  a new function 
					this.left.poly = temp;
				} else if (left instanceof ComplexFunction) { // if right is ComplexFunction downcast it to
					// ComplexFunction
					this.left = (ComplexFunction) left.copy();
				}
			}
			/////// right///////
			if (right != null) {
				if (right instanceof Monom || right instanceof Polynom) { // if it's a Monom or Polynom downcast it to
					// Polynom
					Polynom temp = new Polynom(right.toString());// deep copy it with a string 
					this.right = new ComplexFunction(); // constract  a new function
					this.right.poly = temp; 
				} else if (right instanceof ComplexFunction) { // if left is ComplexFunction downcast it to
					// ComplexFunction
					this.right = (ComplexFunction) right.copy();
				}
			}
		}
	}
	


	
		/** take the x and calculating f(x) at the inputed x,the fanction 
	 * @param x  :  an double to put in the domain function 
	 */

	public double f(double x) {
		
	
		
		if (this.symbol == null && this.poly == null && this.symbol == null) // the case it's (null, null , null)
			return 0;

		if (this.symbol == null && this.poly != null) { // if there is no operator the stoping condition return the x at
			// this point.
			return this.poly.f(x);
		} else { // the case right left or bouth are complexFunctions

			switch (this.symbol) { // Plus, Times, Divid, Max, Min, Comp , None, Error

			case Plus: {
				if (left != null && right != null) { // bouth are none null functions
					return (this.left.f(x) + this.right.f(x));
				} else if (left == null && right != null && this.symbol == null) { // the case its of the kind
					// (null,f(x),null)
					return this.right.f(x);
				} else {
					return this.left.f(x); //// the case its of the kind (f(x),null,null)
				}
			}

			case Times: {
				if (left != null && right != null) { // bouth are none null functions
					return (this.left.f(x) * this.right.f(x));
				} else if (left == null && right != null && this.symbol == null) { // the case its of the kind
					// (null,f(x),null)
					return this.right.f(x);
				} else {
					return this.left.f(x); //// the case its of the kind (f(x),null,null)
				}
			}

			case Divid: {
				if (right.f(x) == 0) {
					throw new RuntimeException("f(" + x + ") is 0 , and cant devied by zero !  ");
				}
				if (left != null && right != null) { // bouth are none null functions
					return (this.left.f(x) / this.right.f(x));
				} else if (left == null && right != null && this.symbol == null) { // the case its of the kind
					// (null,f(x),null)
					return this.right.f(x);
				} else {
					return this.left.f(x); //// the case its of the kind (f(x),null,null)
				}
			}

			case Min: {
				if (left != null && right != null) { // bouth are none null functions
					return Math.min(this.left.f(x), this.right.f(x));
				} else if (left == null && right != null && this.symbol == null) { // the case its of the kind
					// (null,f(x),null)
					return this.right.f(x);
				} else {
					return this.left.f(x); //// the case its of the kind (f(x),null,null)
				}
			}

			case Max: {
				if (left != null && right != null) { // bouth are none null functions
					return Math.max(this.left.f(x), this.right.f(x));
				} else if (left == null && right != null && this.symbol == null) { // the case its of the kind
					// (null,f(x),null)
					return this.right.f(x);
				} else {
					return this.left.f(x); //// the case its of the kind (f(x),null,null)
				}
			}

			case Comp: {
				if (left != null && right != null) { // bouth are none null functions
					return left.f(this.right.f(x)); // compose the right funcion value to the left one

				} else if (left == null && right != null && this.symbol == null) { // the case its of the kind
					// (null,f(x),null)
					return this.right.f(x);
				} else {
					return this.left.f(x); //// the case its of the kind (f(x),null,null)
				}
			}
			case None: {

				System.out.println("You used an None Operation ");
				break;
			}

			case Error: {
				System.out.println("You used an Eror Operation ");
				break;
			}

			}

		}
		return 0;
	}
//Plus, Times, Divid, Max, Min, Comp , None, Error
	
	/**
	 * 
	 * @param checker - a string that represent a Operation 
	 * @return Operation of the string 
	 */
	public static Operation ifOperation(String checker) {
		checker=checker.toLowerCase();
		if (checker.equals("plus"))
			return Operation.Plus;
		else if (checker.equals("mul")|(checker.equals("times")))
			return Operation.Times;
		else if (checker.equals("div")||(checker.equals("divid")))
			return Operation.Divid;
		else if (checker.equals("max"))
			return Operation.Max;
		else if (checker.equals("min"))
			return Operation.Min;
		else if (checker.equals("comp"))
			return Operation.Comp;
		else if (checker.equals("None"))
			throw new RuntimeException("You enterd a unsepurted scine ! ");
		else if (checker.equals("Error"))
			throw new RuntimeException("You enterd a unsepurted scine ! ");
		else
			 throw new RuntimeException("You enterd a wrong scine ! ");
	}

	// plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x 4.0)),2.0)
	// plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x 4.0)),2.0)

	
	
	
	/**recursive function to make a Complexfunction out of string example for a good String :"plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0+ 4.0)),2.0)";
	 * it's imortant that the operation is one of the suppurted one you can read the building counstractor ComplexFunction (String operation , function left , function right ) for more information or the wiki.
	 * 
	 * @param complexString - that represent the function "string_Operation(f1,f2)" 
	 */
	public function initFromString(String complexString) {
		
		try {
			
			boolean debugFlag = false;
			complexString = complexString.replaceAll("\\s+", ""); // delete all the spaces

			ComplexFunction currNode = new ComplexFunction(); // make a new node 

			if (isPolynomm(complexString) == true) {// the stoping case is that we get to a string that we can make to a polynom 
				currNode.poly = new Polynom(complexString); // make it a polynom 
				return currNode;
			}

			String devide = devideLeftAndRight(complexString); // devid the sting with the helping function 
			int firstParenthesis = complexString.indexOf("(");
			String scine = complexString.substring(0, firstParenthesis); // make substring from start to firstParenthesis , it will give
																// as a substring tat will return the scine of the operation
			complexString = complexString.substring(firstParenthesis + 1, complexString.length() - 1);
			if (!devide.equals("-1")) {
				String left = devide.substring(0, devide.indexOf("_"));
				String right = devide.substring(devide.indexOf("_") + 1, devide.length());

				currNode.symbol = ifOperation(scine); // set the symbol of curr node to cinde with a helping function
				if (debugFlag) System.out.println("left is : " + left + " right is :  " + right);
				currNode.left = (ComplexFunction) initFromString(left); // recurisve call 
				currNode.right = (ComplexFunction) initFromString(right);// recurisve call

			}

			else {
				throw new RuntimeException("Parenthesis number don't mutch ! ");
			}
			
			return currNode;
			
		} catch (Exception e) {
			throw new RuntimeException("unlegit String read user menual ! "+complexString);
		}
		

		
	}

	// plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x 4.0)),2.0)
	// helping function to separatee the right and the left function with "_" as separator 
	
	/**
	 * helping function to separatee the right and the left function with "_" as separator 
	 * @param the sting 
	 * @return -1 if unlegit or  right and the left function as string  with "_" as separator with out the operation betwwen the function 
	 */
	private static String devideLeftAndRight(String s) {
		String left = ""; // the left substing 
		String right = ""; // th right substing 
		int countParenthesis = 0;
		int indexOfFirstParenthesis = s.indexOf("("); // the index Of First Parenthesis
		for (int i = 0; i < s.length(); i++) { //a loop in all the sting chars to see that the 
			if (s.charAt(i) == '(') {
				countParenthesis++;
			} else if (s.charAt(i) == ')') {
				countParenthesis--;
			}

			else if ((countParenthesis < 0)) // if Parenthesis unlegit 
				return "-1"; // the case it is unligit String
			else if (countParenthesis == 1 && s.charAt(i) == ',') {
				right = s.substring(indexOfFirstParenthesis + 1, i);
				left = s.substring(i + 1, s.length() - 1);
				return (right + "_" + left);
			}

		}
		if (countParenthesis != 0)
			return "-1";
		else
			return "-2";

	}

	private static boolean isPolynomm(String s) {
		try {
			new Polynom(s);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * a deep copy function that return a coplex function. should be use as ComplexFunction aCopy = (ComplexFunction) a.copy();
	 */
	public function copy() {
		if (this.symbol == null) { // the case it's Monom or Polynom 
			Polynom temp = new Polynom(this.poly.toString()); // make a new deep copy of Polynom 
			ComplexFunction copyNew = new ComplexFunction(); // make a new complexfunction 
			copyNew.poly = temp; // put the poly in 
			return copyNew;
		} else {
			ComplexFunction copyNew = new ComplexFunction(this.symbol, this.left.copy(), this.right.copy()); // do recursive call to left and right 
			return copyNew;
		}
	}

	/** function to change the cuurent coplxfunction symbol to plus , move current node left and f1 right 
	 * @param f1 - the functiob input can be of the the type Monom or Polynom or ComplexFunction 
	 */
	public void plus(function f1) {
		
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Plus,left,right);
		this.voidCopy(copy);
		
	
		
	}

	/** function to change the cuurent coplxfunction symbol to Times , move current node left and f1 right 
	 * @param f1 - the functiob input can be of the the type Monom or Polynom or ComplexFunction 
	 */
	public void mul(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Times,left,right);
		this.voidCopy(copy);
	}

	/** function to change the cuurent coplxfunction symbol to Divid , move current node left and f1 right 
	 * @param f1 - the functiob input can be of the the type Monom or Polynom or ComplexFunction 
	 */
	public void div(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Divid,left,right);
		this.voidCopy(copy);

	}

	/** function to change the cuurent coplxfunction symbol to Max , move current node left and f1 right 
	 * @param f1 - the functiob input can be of the the type Monom or Polynom or ComplexFunction 
	 */
	public void max(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Max,left,right);
		this.voidCopy(copy);

	}
	
	/** function to change the cuurent coplxfunction symbol to Min , move current node left and f1 right 
	 * @param f1 - the functiob input can be of the the type Monom or Polynom or ComplexFunction 
	 */
	public void min(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Min,left,right);
		this.voidCopy(copy);

	}

	
	/** function to change the cuurent coplxfunction symbol to Comp , move current node left and f1 right 
	 * @param f1 - the functiob input can be of the the type Monom or Polynom or ComplexFunction 
	 */
	public void comp(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Comp,left,right);
		this.voidCopy(copy);

	}
	
	

	// Gets a Operation and Returns a Name for Operation
	
	/**
	 * a helping function to cunvert Operation to string 
	 * @param checker
	 * @return the sting of the operation 
	 */
	public  String nameOperation(Operation checker) {
		if (checker.equals(Operation.Plus))
			return "plus";
		else if (checker.equals(Operation.Times))
			return "mul";
		else if (checker.equals(Operation.Divid))
			return "div";
		else if (checker.equals(Operation.Max))
			return "max";
		else if (checker.equals(Operation.Min))
			return "min";
		else if (checker.equals(Operation.Comp))
			return "comp";
		else if (checker.equals(Operation.None))
			return "None";
		else if (checker.equals(Operation.Error))
			return "Error";
		else
			 throw new RuntimeException("You enterd a wrong scine ! ");
	}

	
	public String toString() {
		String ans = "";

		if (this.symbol != null)
			return ans += this.nameOperation(this.symbol) + "(" + this.left.toString() + "," + this.right.toString()
					+ ")";
		else if (this.poly != null)
			return this.poly.toString();
		return ans;
	}


	
	

	/**
	 * this function returns true or false but because coplex functions havw operation like max and min that can return right or left depending on f(x) so we can not know if two functions are alwaws equal thats why we 
	 * the function check a lot of point's as the user input but stiil the answer my not alwas be true , if you want a reeal eccurcy take a as much wide range as you can. 
	 * @param obj of the the type Monom or Polynom or ComplexFunction	
	 * @param accurcy the steps of the function 
	 * @param from what point to what point to check the equals
	 * @value not always true ! only in the checked point there is absolute  coralation between the functions 
	 * @return true or false ,not always true ! only in the checked point there is absolute  correlation between the functions 
	 */
	public boolean equals(Object obj, double accurcy,double width) {
		
		width=Math.abs(width); // makr it in absolute value 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Polynom)&&!(obj instanceof Monom)&&!(obj instanceof ComplexFunction))
			return false;
		function other=(function)obj;
		for (double i = -width; i<width;i+=accurcy) {
			try {
				if(Math.abs(this.f(i)-other.f(i))<=0.001){ // if the distense between the f is biger than epsilonn 
				}
				else {
					return false; 
				}
				
				
			} catch (RuntimeException e) { 
				continue;// for the devid by zero problem just continue 
			}
			
		
		}
		
		
		return true; 
	}
	
	/**
	 * this function returns true or false with defualt range and step .   because complex functions have operation like max and min that can return right or left depending on f(x) so we can not know if two functions are always equal thats why we 
	 * the function check a lot of point's as the user input but stiil the answer my not alwas be true , if you want a reeal eccurcy take a as much wide range as you can with the custum equals function . 
	 * @param obj of the the type Monom or Polynom or ComplexFunction	
	 * accurcy the steps of the function defualt (0.5)
	 *  from what point to what point to check the equals defualt 1000
	 * @value not always true ! only in the checked point there is absolute  coralation between the functions 
	 * @return true or false ,not always true ! only in the checked point there is absolute  correlation between the functions 
	 */
	public boolean equals(Object obj) {
		int a =0;
		return equals( obj, 0.5,1000) ; // the defualt step
		
	}
	



	@Override
	public function left() {
		return this.left();
	}

	@Override
	public function right() {
		return this.right();
	}

	@Override
	public Operation getOp() {

		return this.getOp();
	}

	public static void main(String[] args) {
		Polynom cak1 = new Polynom("5x+2x^2+1");
		Polynom cak2 = new Polynom("5x");
		ComplexFunction a = new ComplexFunction(Operation.Plus, cak1, cak2); // left: 1.0x^0+5.0x^1+2.0x^2 right: 5.0x^1
		ComplexFunction b = new ComplexFunction(Operation.Plus, a, cak2);
		ComplexFunction aCopy = (ComplexFunction) a.copy();
		System.out.println("left: " + a.left.poly.toString() + "  right: " + a.right.poly.toString());
		System.out.println("left: " + aCopy.left.poly.toString() + "  right: " + aCopy.right.poly.toString());
		System.out.println("left left : " + b.left.left.poly.toString() + " left  right: "
				+ b.left.right.poly.toString() + "  right: " + b.right.poly.toString());
		ComplexFunction bCopy = (ComplexFunction) b.copy();
		System.out.println("left left : " + bCopy.left.left.poly.toString() + " left  right: "
				+ bCopy.left.right.poly.toString() + "  right: " + bCopy.right.poly.toString());
		System.out.println("a f(x) is " + a.f(1));
		System.out.println("b f(x) is " + b.f(1));
		ComplexFunction c = new ComplexFunction(Operation.Min, b, a);
		System.out.println("b*a f(x) is " + c.f(1));
		System.out.println("\n pluss function : \n ");
		b.plus(a);
		System.out.println(b.f(1));

		System.out.println("\n leftandright function  ");
		String test = "plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0+ 4.0)),2.0)";

		String simple = ("plus(x^2+5,x^4)");
		System.out.println(devideLeftAndRight(simple));

		System.out.println("\ndevide test : ");
		System.out.println(test);
		System.out.println(devideLeftAndRight(test));
		ComplexFunction f = new ComplexFunction();
		f=(ComplexFunction) f.initFromString(test);
		System.out.println(test);
		  
		System.out.println(f.toString());
		System.out.println(f.f(1));
		System.out.println("equals test");
		
		
		Polynom mon = new Polynom ("x^2-2");
		System.out.println(mon.f(1));
		ComplexFunction com = new ComplexFunction(Operation.Plus,new Polynom("x^2"),new Polynom("x^2"));
		System.out.println(com.f(1));
		System.out.println();
		System.out.println(com.equals(mon));
		ComplexFunction g = new ComplexFunction();
		g=(ComplexFunction) f.initFromString("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
		System.out.println(g);
		System.out.println(g.f(3));
		ComplexFunction com2 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction ron = new ComplexFunction("plus", cak1, cak2);
		System.out.println(ron.toString());

		
		Polynom p1 = new Polynom("5x");
		Polynom p2 = new Polynom("10x");
		String s="Divid";
		ComplexFunction t = new ComplexFunction("Divid", p1, p2);
		System.out.println(t);
		
		System.out.println(g.f(2));
		ComplexFunction p = new ComplexFunction();
		p.voidCopy(g);
		System.out.println(p.f(2));
		
		ComplexFunction ss = new ComplexFunction(Operation.Plus,new Monom("x"),new Monom("2x^2"));
		ss.plus(new Monom("3x^3"));
		ss.plus(new Monom("4x^4"));
		
		System.out.println(ss.toString());

	}
}

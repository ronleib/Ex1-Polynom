package myMath;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.management.RuntimeErrorException;
import org.junit.jupiter.engine.discovery.predicates.IsPotentialTestContainer;

public class ComplexFunction implements complex_function {

	private ComplexFunction up;// reference to app Node
	private Operation symbol; // the operation enum
	private Polynom poly; // the Polynom (should be present only if the enum is null ! that mean only in
	// the last level of the tree )
	private ComplexFunction left; // reference the right function
	private ComplexFunction right; // reference the left function
	private ComplexFunction root; // reference the left function
	
	

	public ComplexFunction getUp() {
		return up;
	}

	public void setUp(ComplexFunction up) {
		this.up = up;
	}

	public Operation getSymbol() {
		return symbol;
	}

	public void setSymbol(Operation symbol) {
		this.symbol = symbol;
	}

	public Polynom getPoly() {
		return poly;
	}

	public void setPoly(Polynom poly) {
		this.poly = poly;
	}

	public ComplexFunction getLeft() {
		return left;
	}

	public void setLeft(ComplexFunction left) {
		this.left = left;
	}

	public ComplexFunction getRight() {
		return right;
	}

	public void setRight(ComplexFunction right) {
		this.right = right;
	}

	
	public ComplexFunction(Polynom poly) {
		this.poly=poly;
		
	}
	
	private void voidCopy (ComplexFunction other) {
		
		this.left=(ComplexFunction) other.left.copy();
		this.right=(ComplexFunction) other.right.copy();
		this.symbol = other.symbol;
		
	}
	
	public ComplexFunction() {
		this.left = null;
		this.right = null;
		this.symbol = null;
		this.up = null;
		this.poly = new Polynom("0");
		this.root= null;
	}
	
	public ComplexFunction (String a , function left , function right ) {
		this(ifOperation(a),left,right);
		
	}


	
	
	
	public  ComplexFunction(Operation symbol, function left, function right) {

		if ((left == null && right == null && symbol == null)) {
			// , should be defualt constracotr
			this.left = null;
			this.right = null;
			this.symbol = null;
			this.up = null;
			this.poly = new Polynom("0");
			this.root=this;
			return;
		}
		
		

		if (((left == null || right == null) && symbol != Operation.None) || (left != null && right != null && symbol == null)) {
			throw new RuntimeException(
					" You cannot insert an null ! insert a Monom ,  Polynom or ComplexFunction bouth to left and right.   ");
		} else {
			this.root=this;
			if (symbol != null)
				this.symbol = symbol;
			/////// left///////
			if (left != null) {
				if (left instanceof Monom || left instanceof Polynom) { // if it's a Polynom or Monom downcast it to
					// Polynom
					Polynom temp = new Polynom(left.toString());
					this.left = new ComplexFunction();
					this.left.poly = temp;
					this.left.up = this;
				} else if (left instanceof ComplexFunction) { // if right is ComplexFunction downcast it to
					// ComplexFunction
					this.left = (ComplexFunction) left.copy();
					this.left.up = this;
				}
			}
			/////// right///////
			if (right != null) {
				if (right instanceof Monom || right instanceof Polynom) { // if it's a Monom or Polynom downcast it to
					// Polynom
					Polynom temp = new Polynom(right.toString());
					this.right = new ComplexFunction();
					this.right.poly = temp;
					this.right.up = this;
				} else if (right instanceof ComplexFunction) { // if left is ComplexFunction downcast it to
					// ComplexFunction
					this.right = (ComplexFunction) right.copy();
					this.right.up = this;
				}
			}
		}
	}
	


	@Override
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
	static Operation ifOperation(String checker) {
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
			return Operation.None;
		else if (checker.equals("Error"))
			return Operation.Error;
		else
			 throw new RuntimeException("You enterd a wrong scine ! ");
	}

	// plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x 4.0)),2.0)
	// plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x 4.0)),2.0)

	@Override
	public function initFromString(String s) {
		
		try {
			
			boolean debugFlag = false;
			s = s.replaceAll("\\s+", ""); // delete all the spaces

			ComplexFunction currNode = new ComplexFunction();

			if (isPolynomm(s) == true) {// the stoping case
				currNode.poly = new Polynom(s);
				return currNode;
			}

			String devide = devideLeftAndRight(s);
			int firstParenthesis = s.indexOf("(");
			String scine = s.substring(0, firstParenthesis); // make substring from start to firstParenthesis , it will give
																// as a substring tat will return the scine of the operation
			s = s.substring(firstParenthesis + 1, s.length() - 1);
			if (!devide.equals("-1")) {
				String left = devide.substring(0, devide.indexOf("_"));
				String right = devide.substring(devide.indexOf("_") + 1, devide.length());

				currNode.symbol = ifOperation(scine); // set the symbol of curr node to cinde with a helping function
				if (debugFlag)
					System.out.println("left is : " + left + " right is :  " + right);
				currNode.left = (ComplexFunction) initFromString(left);
				currNode.right = (ComplexFunction) initFromString(right);

			}

			else {
				throw new RuntimeException("Parenthesis number don't mutch ! ");
			}
			
			return currNode;
			
		} catch (Exception e) {
			throw new RuntimeException("unlegit String read user menual ! "+s);
		}
		

		
	}

	// plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x 4.0)),2.0)
	private static String devideLeftAndRight(String s) {
		String left = "";
		String right = "";
		int countParenthesis = 0;
		int indexOfFirstParenthesis = s.indexOf("(");
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				countParenthesis++;
			} else if (s.charAt(i) == ')') {
				countParenthesis--;
			}

			else if ((countParenthesis < 0))
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

	@Override
	public function copy() {
		if (this.symbol == null) {
			Polynom temp = new Polynom(this.poly.toString());
			ComplexFunction copyNew = new ComplexFunction();
			copyNew.poly = temp;
			return copyNew;
		} else {
			ComplexFunction copyNew = new ComplexFunction(this.symbol, this.left.copy(), this.right.copy());
			return copyNew;
		}
	}

	@Override
	public void plus(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Plus,left,right);
		this.voidCopy(copy);
		
	
		
	}

	@Override
	public void mul(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Times,left,right);
		this.voidCopy(copy);
	}

	@Override
	public void div(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Divid,left,right);
		this.voidCopy(copy);

	}

	@Override
	public void max(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Max,left,right);
		this.voidCopy(copy);

	}
	
	@Override
	public void min(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Min,left,right);
		this.voidCopy(copy);

	}

	
	@Override
	public void comp(function f1) {
		
		ComplexFunction left = (ComplexFunction) this.copy();
		function right = f1.copy();
		ComplexFunction copy = new ComplexFunction(Operation.Comp,left,right);
		this.voidCopy(copy);

	}
	
	

	// Gets a Operation and Returns a Name for Operation
	private String nameOperation(Operation checker) {
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
				
				
			} catch (RuntimeException e) { // for the devid by zero problem just continue 
				
			}
			
		
		}
		
		
		return true; 
	}
	
	@Override
	public boolean equals(Object obj) {
		int a =0;
		return equals( obj, 0.5,1000) ; // the defualt step
		
	}
	



	@Override
	public function left() {
		return this.getLeft();
	}

	@Override
	public function right() {
		return this.getRight();
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
		

	}
}

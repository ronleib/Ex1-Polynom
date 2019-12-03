package myMath;

public class ComplexFunction implements complex_function {

	private ComplexFunction up;// reference to app Node
	private Operation symbol; // the operation enum
	private Polynom poly; // the Polynom (should be present only if the enum is null ! that mean only in
	// the last level of the tree )
	private ComplexFunction left; // reference the right function
	private ComplexFunction right; // reference the left function

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

	public ComplexFunction() {
		this.left = null;
		this.right = null;
		this.symbol = null;
		this.up = null;
		this.poly = new Polynom("0");
	}

	public ComplexFunction(Operation symbol, function left, function right) {

		if ((left == null && right == null && symbol == null)) {
			// , should be defualt constracotr
			this.left = null;
			this.right = null;
			this.symbol = null;
			this.up = null;
			this.poly = new Polynom("0");
			return;
		}

		if (((left == null || right == null) && symbol != null) || (left != null && right != null && symbol == null)) {
			throw new RuntimeException(
					" You cannot insert an null ! insert a Monom ,  Polynom or ComplexFunction bouth to left and right.   ");
		} else {
			if (symbol != null)
				this.symbol = symbol;
			/////// left///////
			if (left != null) {
				if (left instanceof Monom || left instanceof Polynom) { // if it's a Polynom or Monom downcast it to
																		// Polynom
					Polynom temp = new Polynom(left.toString());
					this.left = new ComplexFunction(null, null, null);
					this.left.poly = temp;
					this.left.up = this;
				} else if (left instanceof ComplexFunction) { // if right is ComplexFunction downcast it to
																// ComplexFunction
					this.left = (ComplexFunction) left;
					this.left.up = this;
				}
			}
			/////// right///////
			if (right != null) {
				if (right instanceof Monom || right instanceof Polynom) { // if it's a Monom or Polynom downcast it to
																			// Polynom
					Polynom temp = new Polynom(right.toString());
					this.right = new ComplexFunction(null, null, null);
					this.right.poly = temp;
					this.right.up = this;
				} else if (right instanceof ComplexFunction) { // if left is ComplexFunction downcast it to
																// ComplexFunction
					this.right = (ComplexFunction) right;
					this.right.up = this;
				}
			}
		}
	}

	@Override
	public double f(double x) {
		if (this.symbol == null && this.poly == null&&this.symbol==null) return 0; 
		
		if (this.symbol == null && this.poly != null) { // if there is no operator the stoping condition return the x at
														// this point.
			return this.poly.f(x);
		} else { // the case right left or bouth are complexFunctions

			switch (this.symbol) { // Plus, Times, Divid, Max, Min, Comp , None, Error

			case Plus: {
				if (left!=null&&right!=null) { // bouth are none null functions 
				return (this.left.f(x)+this.right.f(x));
				}
				else if (left==null&&right!=null&&this.symbol==null) { //the case its of the kind (null,f(x),null)
				return this.right.f(x);
				}
				else {
					return this.left.f(x); ////the case its of the kind (f(x),null,null)
				}
			}

			case Times: {
				if (left!=null&&right!=null) { // bouth are none null functions 
				return (this.left.f(x)*this.right.f(x));
				}
				else if (left==null&&right!=null&&this.symbol==null) { //the case its of the kind (null,f(x),null)
				return this.right.f(x);
				}
				else {
					return this.left.f(x); ////the case its of the kind (f(x),null,null)
				}
			}
			
			case Divid: {
				if (left!=null&&right!=null) { // bouth are none null functions 
				return (this.left.f(x)/this.right.f(x));
				}
				else if (left==null&&right!=null&&this.symbol==null) { //the case its of the kind (null,f(x),null)
				return this.right.f(x);
				}
				else {
					return this.left.f(x); ////the case its of the kind (f(x),null,null)
				}
			}
			
			

			case Min: {
				break;
			}

			case Comp: {
				System.out.println("not ready yet ");
			}
			case None: {

				System.out.println("You used an Eror Operation ");
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

	@Override
	public function initFromString(String s) {
		int i = 0, sum = 0;
		String copyS = "";
		boolean flag = false;
		ComplexFunction copyNew = new ComplexFunction(null, null, null);
		while (i < s.length()) {
			if (s.charAt(i) == '(') {
				sum++;
				i++;
			} else if (s.charAt(i) == ')') {
				sum--;
				i++;
				if (sum < 0) {
					throw new RuntimeException("is no`t eblibol");
				}
				Polynom temp = new Polynom(copyS);
				if (flag == false) {
					flag = true;
					copyNew.poly = temp;
				}

				else if (s.charAt(i) == '+') {
					copyNew.plus(temp);
				}
			} else {
				copyS += s.charAt(i);
				i++;
			}
		}
		return copyNew;
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
		this.right = new ComplexFunction(this.symbol, this.right, this.left);
		this.left = new ComplexFunction(null, f1, null);
		this.symbol = Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		this.right = new ComplexFunction(this.symbol, this.right, this.left);
		this.left = new ComplexFunction(null, f1, null);
		this.symbol = Operation.Times;
	}

	@Override
	public void div(function f1) {
		this.right = new ComplexFunction(this.symbol, this.right, this.left);
		this.left = new ComplexFunction(null, f1, null);
		this.symbol = Operation.Divid;
	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comp(function f1) {
		// calculate left and the essing it to the right function

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
		
		System.out.println(b.f(1));

	}
}

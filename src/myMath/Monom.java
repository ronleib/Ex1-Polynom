package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real
 * number and a is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * 
 * @author Boaz
 *
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}

	/**
	 * The derivative of a function of a real variable measures the sensitivity to
	 * change of the function value (output value) with respect to a change in its
	 * argument (input value). Derivatives are a fundamental tool of calculus. this
	 * function is doing derivative by the fprmula "xn=nxn–1"
	 **/
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	public boolean isZero() {
		return (Math.abs(this.get_coefficient())<=EPSILON);
	}
	// * add your code below **

	public Monom() {
		this._coefficient = 0;
		this._power = 0;
	}

	/**
	 * The monom constractor. make a monom out of string.
	 * 
	 * @param String of a simply of the type a*x^b , where a is is double and b is a
	 *               positive integer.} a unvalid Polynom example :
	 *               {4*x^4,2x^3.0,4x^^4,$4x^4}
	 **/
	public Monom(String s) {

		if (s == ""||s=="0") {
			this.getNewZeroMonom();
			return;
		}
			; // if the input is an empty String
		double coff = 0;
		int pow = 0;
		String sCoff = "";
		String sPow = "";
		s = s.toLowerCase();
		if(s.charAt(0)=='+') {// if the first char is + remove it to prevent error on string such as +5x 
			s=s.substring(1);
		}
		try {
			if (!s.contains("x")) { // the case x do not contins x with at list one power only real number
				// Double.parseDouble(s)>=0||Double.parseDouble(s)<0)
				coff = Double.parseDouble(s);
				this.set_power(0);
				this.set_coefficient(coff);
			}
			// }

			else { // the case there is at least x in the firsst power

				if (s.charAt(0) == 'x') { // if x is the first char
					this.set_coefficient(1);
					if (s.length() == 1) {
						this.set_power(1);
					} // if it only of the form 5x
					else {
						sPow = s.substring(s.indexOf('^') + 1); // locate the power
						pow = Integer.parseInt(sPow); // make is an int
						this.set_power(pow); // set the power
					}
				}

				else if (s.charAt(0) == '-' && s.charAt(1) == 'x') { // if its of the form -x
					this.set_coefficient(-1); // add -1 to the coeficent
					if (s.length() == 2) {
						this.set_power(1);
					} // if it of the form -5x
					else {
						sPow = s.substring(s.indexOf('^') + 1); // locate the power
						pow = Integer.parseInt(sPow); // make it a string
						this.set_power(pow); // set th power
					}
				}

				else if (s.charAt(s.length() - 1) == 'x') {// the case its -782x (more than only one dig )
					this.set_power(1);
					sCoff = s.substring(0, s.indexOf("x"));// locate the power
					coff = Double.parseDouble(sCoff);
					this.set_coefficient(coff);// set th powe
				} else {
					sCoff = s.substring(0, s.indexOf("x"));
					coff = Double.parseDouble(sCoff); // set the coeficent
					this.set_coefficient(coff);
					sPow = s.substring(s.indexOf('^') + 1);// locate the power
					pow = Integer.parseInt(sPow);// make it a string
					this.set_power(pow);// set th power
				}
			}
		} catch (Exception e) { // incase there where an valid input such as %^&
			{
				throw new RuntimeException("you put wrong input!:read functhion manual "+s);
			}
		}

	}

	/**
	 * function to add to monoms with the same power
	 * 
	 * @param an Monom with the same Power as the main Monom
	 * 
	 */
	public void add(Monom toAdd) {
		
		if (this.isZero()) {// if our Monom is zero then our Monom is the Monom were trying to add 
			this._coefficient=toAdd._coefficient;
			this._power=toAdd._power;
			return;
		}
		if(toAdd._coefficient==0 ) return; // the case we are adding a zero

		// case 1 they have same coeficent
		if (this._power == toAdd._power) {
			this._coefficient += toAdd._coefficient; // adding both coefficient
		}

		// when the user tries to add a difrrent coeficent monoms that woud have make a
		// polynom
		else {
			{
				throw new RuntimeException("your addind a diffrent coeficent monom, use the polynom function ! "
						+ toAdd._power + "and" + this._power);
			}

		}

	}

	/**
	 * function to multipy to monoms with the same power
	 * 
	 * @param an Monom with the same Power as the main Monom
	 * 
	 */
	public void multipy(Monom other) {
		if (other._coefficient == 0 ||this._coefficient==0) {
			this._coefficient = 0;
			this._power = 0;
		} else {
			this._coefficient = this._coefficient * other._coefficient;
			this._power = this._power + other._power;
		}
	}

	/**
	 * creat a String out of the monom
	 **/
	public String toString() {
		String ans = this._coefficient + "x^" + this._power;
		return ans;
	}

	
	public boolean equals(Object other) {
		
		if (other instanceof Monom) {
		try {
		 Monom otherMonom = new Monom (other.toString());
		double powerDifrence = Math.abs(otherMonom._power-this._power);
		double coffDifrence = Math.abs(otherMonom._coefficient-this._coefficient);
		if((powerDifrence<=EPSILON)&&(coffDifrence<=EPSILON)) return true;
		}
		
		catch (Exception e) {
			return false;
		}}
		
		return false;
	
	}

	// * Private Methods and Data **

	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;



	@Override
	public function initFromString(String s) {
		
		return new Monom(s);
	}

	@Override
	public function copy() {
		Monom ansMonom = new Monom();
		ansMonom._coefficient=this._coefficient;
		ansMonom._power=get_power();
		return ansMonom;
	}
	public static void main(String[] args) {
		Monom a = new Monom("2");
		System.out.println(a.isZero());
		
		
	}

}
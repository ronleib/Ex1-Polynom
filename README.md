**Polynom**

Welcome to Polynom's wiki. This project help you to learn about polynom. You can create function: 
polynom, monom, and complex function. First, i will explain about monom calss. 
monoms are the base of polynoms, monom defined in this form: ax^b , a is real number and b is natural number. 
You can init monom object by using this constructor: Monom firstMonom = new Monom(3,4); -> create 3x^4. You can also create by string Monom firstMonom = new Monom("3x") equals to Monom firstMonom = new Monom("3x^1");.
More examples: Monom firstMonom = new Monom("-2"); equals to Monom firstMonom = new Monom("-2x^0"); equals to Monom firstMonom = new Monom(2,0);. 
to init a monom using the string constructor, dont use spaces.

The project is mainly about Monom’s Polynoms and ComplexFunctions and support multiple methods such as 
calculating the function value of x at the point, 
root and area for the Polynom function type, 
Draw a graph of a function,
equal between a function and much more.

***
**Monom class**


In mathematics, a monomial is, roughly speaking, a polynomial which has only one term. 
Two definitions of a monomial may be encountered.
In the picture you can see the Monom of x^3 drawn by the draw function of the project 


<a href="http://www.siz.co.il/"><img src="http://up419.siz.co.il/up1/g4zqwdtyzgzz.png" border="0" alt="CCCCC" /></a>
 
Monom defined in this form:
ax^b , a is real number and b is natural number. You can init monom object by using this constructor: 
`Monom firstMonom = new Monom(3,4);` -> create 3x^4. 
You can also create by string `Monom firstMonom = new Monom("3x")` equals to `Monom firstMonom = new Monom("3x^1");`.
More examples:
`Monom firstMonom = new Monom("-2");` equals to `Monom firstMonom = new Monom("-2x^0");`
equals to `Monom firstMonom = new Monom(2,0);`. **to init a monom using the string constructor, dont use spaces**.

Monom class implements the interface "function" , so you can use the functions: f(x) - return the value of f(x),
initFromString that return monom from string, clone() is deep copy of the monom, and equals. equals function return true if
the subtraction of two monoms less then EPSILON. for example
`Monom firstMonom = new Monom("2x^2");
Monom secondMonom = new Monom("1.999999999999x^2");
boolean eq = firstMonom.equals(secondMonom);` -> eq = ture.
Equals function can compare monom and polynom, 
Equals function able to compare monom and polynom, by using polynom's equals function. (Its allow because a.equals(b) = b.equals(a)). Monom class also contains the add and multiply function. for example
`Monom firstMonom = new Monom("-2x");
Monom secondMonom = new Monom("-2x");
firstMonom.mult(secondMonom);` -> firstMonom = 4x^2

***
**Polynomial**


In mathematics, a polynomial is an expression consisting of variables (also called indeterminates) and coefficients, 
That involves only the operations of addition, subtraction, multiplication, and non-negative integer exponents of variables. 
In the picture you can see the Polynom of x^4+5x+1 drawn by the draw function of the project 


<a href="http://www.siz.co.il/"><img src="http://up419.siz.co.il/up1/inc2xmjjhtgm.png" border="0" alt="POLINOM" /></a>


**To init polynom from string, enter legal monoms, with "+" or "-" between each monom **
polynom implements polynom_able, which means that you can use add, mult, sub,derivative... you can see java doc html to read more about this functions.
an example to use this functions:
```java
Polynom p1 = new Polynom("3x^7+25x^2-22");
Polynom p2 = new Polynom("-10x^7+25x^3+10");
System.out.println("p1= "+p1.toString());
System.out.println("p2= "+p2.toString());
Polynom_able p3 = p1.copy();
p1.substract(p2);
System.out.println("("+p3.toString()+") - ("+p2.toString() +") = " +p1.toString());
System.out.println("Before mult: "+p1);
Polynom_able mult = p1.copy();
p1.multiply(new Monom("3x^2"));
System.out.println("After mult: "+p1.toString());
p1 = new Polynom("2x^5-3x");
System.out.println("Before mult: "+p1);
p1.multiply(new Polynom("-8x^4+2"));
System.out.println("After mult "+p1.toString());
```
you will get this output:
```java
p1= 3.0x^7+25.0x^2-22.0x^0
p2= -10.0x^7+25.0x^3+10.0x^0
(3.0x^7+25.0x^2-22.0x^0) - (-10.0x^7+25.0x^3+10.0x^0) = 13.0x^7-25.0x^3+25.0x^2-32.0x^0
Before mult: 13.0x^7-25.0x^3+25.0x^2-32.0x^0
After mult: 39.0x^9-75.0x^5+75.0x^4-96.0x^2
Before mult: 2.0x^5-3.0x^1
After mult -16.0x^9+28.0x^5-6.0x^1
```
The equals function return true if this polynom subtract other polynom less then Monon.EPSILON. Equals can compare polynom with monom, by this way: 1. create new polynom from the monom, then return equals of the polynoms.
Polynom class also implement count_function interface. this interface contains two functions: area and root.
Both of them get range(x0,x1), and area return the positive area, by using Riman's sum.

**Complexfunction**
***

The lest type of function(this class also implements function interface). Complex function represent 2 functions(monom,polynom or complex funcftion) with operation. The operations are: plus, mult, div, comp,none,min,max.
Operation enum contains this values. 
**Operation**
Plus: plus(f1,f2) = f1+f2
Times: mult(f1,f2) = f1*f2
Divide: div(f1,f2) = f1/f2
Comp: f1(f2)
Min: Min(f1,f2)
Max: Max(f1,f2)
None: f1
Eror: illegal parameters from constructor.
The implementation of this class is recursive method. First create complex function object

**You can use initFromString to create complex function from string, use this operations: plus, mult, div, min, max,comp, none.**
The class complex function contains the methods: plus,mult,div... the algorithm is recursive:
if f2 is null, make f2 to be the parameter of the function.
else make f1 to be the clone of this complex function, and f2 to the parameter. 
Both of cases, the operation changed to the value of the function.
The function left() and right() return the left and the right function of the complex function.
f(X)- return the value of the complex function while x is the parameter of the function f. To do this, I calculate the left side in the right side recursively by calling f(left).f(x) and f(right).f(x), and then apply the operation between two numbers. The function comp is unusual , first I calculate the right side in x, and then calculate the left side by using f(rightSideSum).

<a href="http://www.siz.co.il/"><img src="http://up419.siz.co.il/up1/g4zqwdtyzgzz.png" border="0" alt="CCCCC" /></a>


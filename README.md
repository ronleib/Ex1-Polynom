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

***
**Polynomial**


In mathematics, a polynomial is an expression consisting of variables (also called indeterminates) and coefficients, 
That involves only the operations of addition, subtraction, multiplication, and non-negative integer exponents of variables. 
In the picture you can see the Polynom of x^4+5x+1 drawn by the draw function of the project 


<a href="http://www.siz.co.il/"><img src="http://up419.siz.co.il/up1/inc2xmjjhtgm.png" border="0" alt="POLINOM" /></a>


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


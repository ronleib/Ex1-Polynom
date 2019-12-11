package myMath;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.management.RuntimeErrorException;
import org.junit.jupiter.engine.discovery.predicates.IsPotentialTestContainer;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import myMath.ComplexFunction;
import myMath.GUI_params;
import myMath.Polynom;
import myMath.Range;
import myMath.StdDraw;
import myMath.function;
import myMath.functions;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Functions_GUI implements functions {
	static ArrayList<function> arrFunc = new ArrayList<function>();
	
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};

	@Override
	public int size() {
		return this.arrFunc.size();
	}

	@Override
	public boolean isEmpty() {
		return this.isEmpty();
	}

	@Override
	public boolean contains(Object o) {

		return arrFunc.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		return this.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(function e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return arrFunc.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return arrFunc.contains(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {

		return arrFunc.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return arrFunc.retainAll(c);
	}

	@Override
	public void clear() {
		arrFunc.clear();
	}


	@Override
	public  void  initFromFile(String file) throws IOException {
		ComplexFunction toInheretComplex = new ComplexFunction();
		String[] arr= null;
	    ArrayList<String> stringFunctions = new ArrayList<String>();
		   try 
		    { 
		        FileInputStream inputStream = new FileInputStream(file); 
		        DataInputStream data_input = new DataInputStream(inputStream); 
		        BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input)); 
		        String str_line; 

		        while ((str_line = buffer.readLine()) != null) 
		        { 
		            str_line = str_line.trim(); 
		            if ((str_line.length()!=0))  
		            { 
		            	stringFunctions.add(str_line);
		            } 
		        }
 
		        arr = (String[])stringFunctions.toArray(new String[stringFunctions.size()]);
		    }catch (Exception e) {
		
			}
		   
		   for (int j = 0; j < arr.length; j++) {
				try { // the case ite a complex function 
				function temp =new ComplexFunction();
				temp=temp.initFromString(arr[j]);
				arrFunc.add(temp);
				} 
					catch (Exception e) { // the case its Monom or Polynom
					arrFunc.add(new Polynom().initFromString(arr[j]));
				}			   
		}
		
		   
		   return ;

	}

	@Override
	public void saveToFile(String file) throws IOException {
		Iterator<function> saveTo = arrFunc.iterator();
		StringBuilder sBuld = new StringBuilder();
		
		while(saveTo.hasNext()) {
			function save = saveTo.next();
			sBuld.append(save.toString());
				sBuld.append("\n");	
		}
		try{
			PrintWriter pw = new PrintWriter(new File(file));
			pw.write(sBuld.toString());
			pw.close();
		} 
		catch (Exception e){
			e.printStackTrace();
			return;
		}

	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// number of line segments to plot
		int n = resolution;
		StdDraw.setCanvasSize(width, height);
		int size = arrFunc.size();
		double[] x = new double[n+1];
		double[][] yy = new double[size][n+1];
		double x_step = (rx.get_max()-rx.get_min())/n;
		double x0 = rx.get_min();
		for (int i=0; i<=n; i++) {
			x[i] = x0;
			for(int a=0;a<size;a++) {

				yy[a][i] = arrFunc.get(a).f(x[i]);
			}
			x0+=x_step;
		}
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		
				
				//////// vertical lines
				StdDraw.setPenColor(Color.LIGHT_GRAY);
				for (int i = 0; i <= n; i=i+10) {
					StdDraw.line(x[i], ry.get_min(), x[i], ry.get_max());
				}
				//////// horizontal  lines
				for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) {
					StdDraw.line(rx.get_min(), i, rx.get_max(), i);
				}
				//////// x axis		
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.setPenRadius(0.005);
			   StdDraw.line(rx.get_min(), 0,rx.get_max(), 0);
				StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
				for (int i = 0; i <= n; i=i+10) {
					StdDraw.text((int)x[i]-0.07, +0.3, Integer.toString(i-n/2));
				}
				//////// y axis	
				StdDraw.line(x[n/2], ry.get_min(), x[n/2], ry.get_max());
				for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) {
					StdDraw.text((int)x[n/2]+0.25, i, Integer.toString((int) i));
				}
		
		
		
		
		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);
		
			System.out.println(a+") "+Colors[a]+"  f(x)= "+arrFunc.get(a));
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}	



	}
	
	public void drawFunctions(int Width,int Height,double[] x ,double[]y,int Resolution){
		if(x.length<1||y.length<1) 	throw new RuntimeException("must enter 2 params ");
		Range _x = new Range(x[0], x[1]);
		Range _y = new Range(y[0], y[1]);
		drawFunctions( Width, Height,_x ,_y, Resolution);

	}

	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
		
		// 1. JSON file to Java object
		try {
			GUI_params params = (GUI_params) gson.fromJson(new FileReader(json_file), GUI_params.class);
			drawFunctions(params.Width,params.Height,params.Range_X,params.Range_Y,params.Resolution);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		
	}


	public function get(int i) {
		return  arrFunc.get(i);
		
	}
	

	public static void main(String[] args) throws IOException {
		Functions_GUI func = new Functions_GUI();
		func.initFromFile("C:\\Users\\semen\\Documents\\function_file.txt");
		for (int i = 0; i < arrFunc.size(); i++) {
			System.out.println(arrFunc.get(i));
		}
		
			int w=1000, h=600, res=200;
			Range rx = new Range(-10,10);
			Range ry = new Range(-5,15);
			//func.drawFunctions(w,h,rx,ry,res);
			func.drawFunctions("C:\\Users\\semen\\Documents\\GUI_params (3).txt");
			
			
		
	}



}

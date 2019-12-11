//package myMath;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import javax.management.RuntimeErrorException;
//import org.junit.jupiter.engine.discovery.predicates.IsPotentialTestContainer;
//
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class Functions_GUI implements functions {
//	static ArrayList<function> arrFunc = new ArrayList<function>();
//
//	@Override
//	public int size() {
//		return this.arrFunc.size();
//	}
//
//	@Override
//	public boolean isEmpty() {
//		return this.isEmpty();
//	}
//
//	@Override
//	public boolean contains(Object o) {
//
//		return arrFunc.contains(o);
//	}
//
//	@Override
//	public Iterator<function> iterator() {
//		return this.iterator();
//	}
//
//	@Override
//	public Object[] toArray() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <T> T[] toArray(T[] a) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean add(function e) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean remove(Object o) {
//		return arrFunc.remove(o);
//	}
//
//	@Override
//	public boolean containsAll(Collection<?> c) {
//		return arrFunc.contains(c);
//	}
//
//	@Override
//	public boolean addAll(Collection<? extends function> c) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean removeAll(Collection<?> c) {
//
//		return arrFunc.removeAll(c);
//	}
//
//	@Override
//	public boolean retainAll(Collection<?> c) {
//		return arrFunc.retainAll(c);
//	}
//
//	@Override
//	public void clear() {
//		arrFunc.clear();
//	}
//
//
//	@Override
//	public  void  initFromFile(String file) throws IOException {
//		ComplexFunction toInheretComplex = new ComplexFunction();
//		String[] arr= null;
//	    ArrayList<String> stringFunctions = new ArrayList<String>();
//		   try 
//		    { 
//		        FileInputStream inputStream = new FileInputStream(file); 
//		        DataInputStream data_input = new DataInputStream(inputStream); 
//		        BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input)); 
//		        String str_line; 
//
//		        while ((str_line = buffer.readLine()) != null) 
//		        { 
//		            str_line = str_line.trim(); 
//		            if ((str_line.length()!=0))  
//		            { 
//		            	stringFunctions.add(str_line);
//		            } 
//		        }
// 
//		        arr = (String[])stringFunctions.toArray(new String[stringFunctions.size()]);
//		    }catch (Exception e) {
//		
//			}
//		   
//		   for (int j = 0; j < arr.length; j++) {
//				try { // the case ite a complex function 
//				function temp =new ComplexFunction();
//				temp=temp.initFromString(arr[j]);
//				arrFunc.add(temp);
//				} 
//					catch (Exception e) { // the case its Monom or Polynom
//					arrFunc.add(new Polynom().initFromString(arr[j]));
//				}			   
//		}
//		
//		   
//		   return ;
//
//	}
//
//	@Override
//	public void saveToFile(String file) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void drawFunctions(String json_file) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public function copy() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public static void main(String[] args) throws IOException {
//		Functions_GUI func = new Functions_GUI();
//		func.initFromFile("C:\\Users\\semen\\Documents\\function_file.txt");
//		for (int i = 0; i < arrFunc.size(); i++) {
//			System.out.println(arrFunc.get(i));
//			
//		}
//
//	}
//}

package restart;
import java.io.*;
import java.util.Scanner;

class Stack {
	double stack[];
	 int top;
	
	public Stack (int n) {
		stack = new double[n];
		top = -1;
	}
	public boolean sEmpty() {
		return top < 0;
	}
	public boolean sFull() {
		return top > stack.length - 1;
	}
	public void push(double i) {
		if(sFull()) {
			return;
		}
		top ++;
		stack[top] = i;
	}
	public double pop() {
		if(sEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		top --;
		return stack[top + 1];
	}
	
	
}

public class Main {
	
	static int num = 1;

	static double value;
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		Stack stk = new Stack(num);
	
		
		
		while(!str.equals("$")) {
			num ++;
			
			if(num > 2) {
				Stack nstk = new Stack(num);
				for(int k = 0 ; k <= stk.top; k++) {
					nstk.push(stk.stack[k]);
					//nstk.stack[k] = stk.stack[k];
				}
				stk = nstk;
			}
			if(str.equals("+")) {
				double a = stk.pop();
				double b = stk.pop();
				double result = b + a;
				stk.push(result);
				value = result;
			}else if(str.equals("-")) {
				double a = stk.pop();
				double b = stk.pop();
				double result = b - a;
				stk.push(result);
				value = result;
			}else if(str.equals("*")) {
				double a = stk.pop();
				double b = stk.pop();
				double result = b * a;
				stk.push(result);
				value = result;
			}else if(str.equals("/")) {
				double a = stk.pop();
				double b = stk.pop();
				double result = b / a;
				stk.push(result);
				value = result;
			}else {
				double operand = Double.parseDouble(str);
				stk.push(operand);
			}
			
			str = scan.next();
		}
		System.out.println(value);

	}

}

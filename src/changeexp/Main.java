package changeexp;
import java.io.*;
import java.util.Scanner;

class Stack{
	String stack[];
	int top;
	
	public Stack(int n) {
		stack = new String[n];
		top = -1;
	}
	public boolean sEmpty() {
		return top < 0;
	}
	public boolean sFull() {
		return top > stack.length -1;
	}
	public void push(String i) {
		if(sFull()) {
			return;
		}
		top ++;
		stack[top] = i;
	}
	public String pop() {
		if(sEmpty()) {
			return "";
		}
		top --;
		return stack[top + 1];
	}
	public void delete() {
		if(sEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		top--;
	}
	
	public String peek() {
		if(sEmpty()) {
			return "-1";
		}
		return stack[top];
	}
}




public class Main {
	
	static String out = "0";
	public static int changeout(String a) {
		if(a.equals("*") || a.equals("/")) {
			out = "2";
		}else if(a.equals("(")) {
			out = "3";
		}else if(a.equals("+") || a.equals("-")){
			out = "1";
		}
		int value = Integer.parseInt(out);
		return value;
	}
	static String in = "0";
	public static int changein(String a) {
		if(a.equals("*") || a.equals("/")) {
			in = "2";
		}
		else if(a.equals("+") || a.equals("-")) {
			in = "1";
		}else if(a.equals("(")) {
			in = "0";
		}else {
			in = "-1";
		}
		int value = Integer.parseInt(in);
		return value;
	}
	
	
	static int num = 1;
	static String[] result = new String[1];
	public static String[] increase(String[] s) {
		String[] newresult = new String[result.length +1];
		for(int k = 0; k < result.length;k++) {
			newresult[k] = s[k];
		}
		s = newresult;
		result = s;
		return result;
	}
	
	static int count = 0;
	

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		String str = scan.next();
		Stack save = new Stack(num);
		while (!str.equals("$")) {
			num++;
			if(num >2) {
				Stack newsave = new Stack(num);
				for(int k = 0; k <= save.top; k++) {
					newsave.push(save.stack[k]);
				}
				save = newsave;
			}
			if(str.equals("(")) {
				save.push(str);
			}else if(str.equals("*")||str.equals("/")||str.equals("+")||str.equals("-")) {
				while (changeout(str) <= changein(save.peek())) {
					increase(result);
					result[count] = result[count-1] + " " + save.pop();
					count++;
				}
				save.push(str);
			}
			
			else if(str.equals(")")) {
				while(!save.peek().equals("(")){
					increase(result);
					result[count] = result[count-1] + " " + save.pop();
					count++;
				}
				save.delete();
			}else {
				if(count == 0) {
					increase(result);
					result[count] = str;
					count++;
				}else {
					increase(result);
					result[count] = result[count-1] + " " + str;
					count++;
				}
			}
			
			
			str = scan.next();
		}
		while(save.top >= 0) {
			increase(result);
			result[count] = result[count-1] + " " + save.pop();
			count++;
		}
		System.out.println(result[count-1]);
	}

}

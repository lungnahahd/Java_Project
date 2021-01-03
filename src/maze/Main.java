package maze;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
class Stack{
	int stack[];
	int top;
	
	public Stack(int n) {
		stack = new int[n];
		top = -1;
	}
	public boolean sEmpty() {
		return top < 0;
	}
	public boolean sFull() {
		return top > stack.length -1;
	}
	public void push(int a) {
		if(sFull()) {
			return;
		}
		top++;
		stack[top] = a;
	}
	public int pop() {
		if(sEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		top--;
		return stack[top + 1];
	}
	public void delete() {
		if(sEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		top--;
	}
	public int peek() {
		if(sEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		return stack[top];
	}
}

class Point {
	public int x, y;
}


public class Main {
	
	public static int dir = 1;
	public static int a,b;
	public static int next_a, next_b;
	
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int sizeY = scan.nextInt();
		int sizeX = scan.nextInt();
		
		
		
		Point start = new Point();
		start.x = scan.nextInt();
		start.y = scan.nextInt();
		
		Point exit = new Point();
		exit.x = scan.nextInt();
		exit.y = scan.nextInt();
		
		int maze[][] = new int[sizeY][sizeX];
		int mark[][] = new int[sizeY][sizeX];
		mark[start.y][start.x] = 1;
		Stack ysave = new Stack(sizeX*sizeY);
		Stack xsave = new Stack(sizeX*sizeY);
		Stack dirsave = new Stack(sizeX*sizeY);
		for(int i = 0; i < sizeY; i++) {
			for(int j = 0; j < sizeX; j++) {
				maze[i][j] = scan.nextInt();
			}
		}
		
		int dirguide[][] = new int [4][2];
		dirguide[0][0] = dirguide[3][1] = -1;
		dirguide[1][1] = dirguide[2][0] = 1;
		
		xsave.push(start.x);
		ysave.push(start.y);
		dirsave.push(dir);
		while(!xsave.sEmpty()) {
			a = ysave.pop();
			b = xsave.pop();
			dir = dirsave.pop();
			while(dir <=3) {
				next_a = a + dirguide[dir][0];
				next_b = b + dirguide[dir][1];
				if(next_a == exit.y && next_b == exit.x) {
					maze[exit.y][exit.x] = 3;
					while (xsave.top >= 0) {
						int p = ysave.pop();
						int q = xsave.pop();
						maze[p][q] = 4;
					}
					maze[a][b] = 4;
					maze[start.y][start.x] = 2;
					break;
				}
				if(maze[next_a][next_b] == 0 && mark[next_a][next_b] == 0) {
					mark[next_a][next_b] = 1;
					xsave.push(b);
					ysave.push(a);
					dirsave.push(dir);
					a = next_a;
					b = next_b;
					dir = 0;
				}else {
					dir = dir + 1;
				}
			}
			
		}
		
		String change[][] = new String[sizeY][sizeX];
		for(int i = 0; i < sizeY; i++) {
			for(int j = 0; j < sizeX; j++) {
				if (maze[i][j] == 0) {
					change[i][j] = "0";
				}else if(maze[i][j] == 1) {
					change[i][j] = "1";
				}else if(maze[i][j] == 2) {
					change[i][j] = "S";
				}else if(maze[i][j] == 3) {
					change[i][j] = "F";
				}else {
					change[i][j] = "*";
				}
			}
		}
		String bowl[] = new String[sizeX];
		bowl[0] = "1";
		String print[] = new String[sizeY];
		for(int i = 0; i < sizeY; i++) {
			for(int j = 1; j < sizeX; j++) {
				bowl[j] = bowl[j-1] + " " + change[i][j];
			}
			print[i] = bowl[sizeX-1];
		}
		for(int i = 0; i < sizeY ; i ++) {
			System.out.println(print[i]);
		}
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		

	}

}

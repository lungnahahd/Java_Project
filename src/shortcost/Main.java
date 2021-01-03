package shortcost;
import java.io.*;
import java.util.Scanner;



class Graph {
	int noVertex; // ������ ����
	int [][] m; // ���� ���
	public static final int NONE = 999; // ��ξ���
	String[] check;
	
	class Stack{
		int[] stack = new int[noVertex];
		int top = -1;
		public boolean isFull() {
			return top > noVertex;
		}
		public void push(int num) {
			if(isFull()) {
				return;
			}
			top++;
			stack[top] = num;
		}
		
	}
	
	
	
	public void loadData(Scanner scan) {
		int noVertex = scan.nextInt(); // ������ ����
		
		this.noVertex = noVertex;
		check = new String[noVertex];
		for(int i = 0; i < noVertex; i++) {
			check[i] = "f";
		}
		m = new int[noVertex][noVertex];
		
		//������ ���� �׷��� ����
		for(int i = 0; i < noVertex; i++) {
			for(int j = 0; j < noVertex; j++) {
				m[i][j] = scan.nextInt(); //����ġ
			}
			
			
		}
	}
	
	//�ʿ��� �޼ҵ峪 �ʵ带 �߰�
	int min = -1;
	public int[] shortestPath(int startVertex) {
		int[] dist = new int[noVertex];
		//Stack save = new Stack();
		for(int i = 0; i < dist.length; i++) {
			dist[i] = m[startVertex][i];	
		}
		check[startVertex] = "t";
		//save.push(startVertex);
		while(true) {
			for(int i = 0; i < noVertex; i ++) {
				if(check[i].equals("f")) {
					if(min == -1) {
						min = i;
					}
					if(dist[min] > dist[i]) {
						min = i;
					}
				}
			}
			if(min == -1) {
				break;
			}
			check[min] = "t";
			//save.push(min);
			for(int i = 0; i < noVertex; i++) {
				if(check[i] == "f") {
					if(dist[i] > dist[min] + m[min][i]) {
						dist[i] = dist[min] + m[min][i];
					}
				}
			}
			min = -1;
		}
		
		return dist;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		Graph g = new Graph(); // �׷��� ����
		g.loadData(scan); // �׷��� ������ ����
		
		int startVertex = scan.nextInt(); // ��� ����
		
		int[] dist = g.shortestPath(startVertex); // �ִ� ��ο� ���� ����� ����
		for(int i = 0; i < dist.length; i++) {
			System.out.print(dist[i] + " "); // ��� ���
		}

	}

}

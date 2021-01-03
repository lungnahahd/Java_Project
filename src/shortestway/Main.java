package shortestway;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class Graph{
	
	int noVertex; // 정점의 갯수
	int [][] m; // 인접 행렬
	public static final int NONE = 999;
	String[] check;
	public  int findcost = 0;
	

	
	
	
	
	class Queue{
		private int[] data;
		private int front = 0;
		private int rear = 0;
		int count = 0;
		public Queue() {
			data = new int [noVertex];
		}
		public void enque(int str) {
			data[rear] = str;
			rear = (rear + 1) % noVertex;
			//count 
		}
		
		public int deque() {
			int item = data[front];
			front = (front + 1) % noVertex;
			return item;
		}
		public boolean first() {
			return front == rear;
		}
	}
	
	class Stack{
		int[]stack = new int[noVertex];
		int top  = -1;
		public boolean isFull() {
			return top > noVertex;
		}
		public boolean first() {
			return top == -1;
		}
		public void push(int num) {
			if(isFull()) {
				return;
			}
			top++;
			stack[top] = num;
		}
		public int out() {
			int a = stack[top];
			top--;
			return a;
		}
	}
	
	public void loadData(Scanner scan) {
		int noVertex = scan.nextInt(); // 정점의 갯수
		
		this.noVertex = noVertex;
		check = new String[noVertex];
		m = new int[noVertex][noVertex];
		for(int i = 0; i<noVertex; i++) {
			check[i] = "f";
		}
		m = new int[noVertex][noVertex];
		
		//간선이 없는 그래프 생성
		for(int i = 0; i < noVertex; i++) {
			for(int j = 0; j < noVertex; j++) {
				m[i][j] = scan.nextInt(); //가중치
			}
		}
	}
	// 필요한 메소드나 필드 추가

	
	
	int min = -1;
	public ArrayList<Integer> shortestPath(int startVertex, int finalVertex){
		ArrayList<Integer> path = new ArrayList<Integer>();
		//최단 경로를 기록하기 위한 변수, 길이가 가변적인 배열
		//길이가 가변적인 배열, 여기서는 Integer만 받을 수 있다.
		
		path.add(startVertex);
		Stack thend = new Stack();
		int[] dist = new int[noVertex];
		int[] pre = new int[noVertex];
		for(int i = 0; i < noVertex; i++) {
			pre[i] = startVertex;
		}
		for(int i = 0; i< dist.length; i++) {
			dist[i] = m[startVertex][i];
		}
		check[startVertex] = "t";
	
		
	
		while(true) {
			
			
			for(int i = 0; i < noVertex; i++) {
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
		
		
			for(int i = 0; i < noVertex; i++) {
				
				if(check[i].equals("f")) {
					
					if(dist[i] > dist[min] + m[min][i]) {
						dist[i] = dist[min] + m[min][i];
						pre[i] = min; 
							
					}
					
				}
			}
			min = -1;
			if(check[finalVertex].equals("t")) {
				break;
			}
		}
	
		
		int preNode = pre[finalVertex];
		while(preNode!= startVertex) {
			thend.push(preNode);
			preNode = pre[preNode];
		}
		
		while(!thend.first()) {
			path.add(thend.out());
		}
		path.add(finalVertex);
		
		
		findcost = dist[finalVertex];
	
		path.add(findcost);
		return path;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		Graph g = new Graph(); // 그래프 생성
		g.loadData(scan); // 그래프 데이터 읽음
		
		int startVertex = scan.nextInt(); // 출발 정점
		int finalVertex = scan.nextInt(); // 도착 정점
		
		ArrayList<Integer> path = g.shortestPath(startVertex,finalVertex);
		// 최단 경로에 따른 비용을 구함
		for(int vertex : path) {
			System.out.print(vertex + " "); // 비용 출력
		}
		//System.out.println();
		//System.out.print(g.findcost);

	}

}

package shortcost;
import java.io.*;
import java.util.Scanner;



class Graph {
	int noVertex; // 정점의 갯수
	int [][] m; // 인접 행렬
	public static final int NONE = 999; // 경로없음
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
		int noVertex = scan.nextInt(); // 정점의 갯수
		
		this.noVertex = noVertex;
		check = new String[noVertex];
		for(int i = 0; i < noVertex; i++) {
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
	
	//필요한 메소드나 필드를 추가
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
		
		Graph g = new Graph(); // 그래프 생성
		g.loadData(scan); // 그래프 데이터 읽음
		
		int startVertex = scan.nextInt(); // 출발 정점
		
		int[] dist = g.shortestPath(startVertex); // 최단 경로에 따른 비용을 구함
		for(int i = 0; i < dist.length; i++) {
			System.out.print(dist[i] + " "); // 비용 출력
		}

	}

}

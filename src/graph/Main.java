package graph;
import java.io.*;
import java.util.Scanner;

//필요한 메소드는 추가

class ListNode{
	public class Term{
		int data;
		Term link;
	}
	public Term head;
	public Term tail;
	
	public void addTerm(int data){
		Term term = new Term();
		term.data = data;
		term.link = null;
		if(head == null){
			head = tail = term;
		}else{
			tail.link = term;
			tail = term;
		}
	}
	public void rmtail() {
		Term term = new Term();
		for(Term t = this.head;;t = t.link) {
			if(t.link == tail) {
				term = t;
				break;
			}
		}
		this.tail = term;
		term.link = null;
	}
}




class Stack{
	int num;
	int [] stack;
	int top = -1;
	Stack(int i){
		num = i;
		stack = new int[i];
	}
	public boolean isFull(){
		return top > num;
	}
	public boolean isEmpty() {
		return top < 0;
	}
	public void push(int item) {
		if(isFull()) {
			return;
		}
		top++;
		stack[top] = item;
	}
	public int pop() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		top--;
		return stack[top + 1];
	}
	public int peek() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		return stack[top];
	}
}

class Queue{
	private int[] data;
	private int size;
	private int front = 0;
	private int rear = 0;
	private int count = 0;
	
	public Queue(int i) {
		size = i;
		data = new int[size];
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public void enque(int a) {
		data[rear] = a;
		rear = rear + 1;
		count ++;
	}
	
	public int deque() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}else {
			int find = data[front];
			front = front + 1;
			count --;
			return find;
		}
	}
}
	


class Graph{
	// 필요한 변수, 메소드 추가
	public int[][]makegraph;
	public int noVertex;
	public ListNode[]save;
	//생성자를 작성
	public Graph(int noVertex) {
		this.noVertex = noVertex;
		makegraph = new int[noVertex][noVertex];
		save = new ListNode[noVertex];
		for(int i = 0; i < noVertex; i++) {
			ListNode no = new ListNode();
			no.addTerm(i);
			save[i] = no;
		}
	}

	
	
	
	// 간선 (i, j)를 삽입
	public void addEdge(int i, int j) {
		int k = 0;
		for(int check = 0;; check++) {
			if(save[check].head.data == i) {
				k = i;
				break;
			}
		}
		save[k].addTerm(j);
	}
	
	//간선 (i, j)를 삭제
	public void removeEdge(int i, int j) {
		int k = 0;
		for(int check = 0;; check++) {
			if(save[check].head.data == i) {
				k = i;
				break;
			}
		}
		for(ListNode.Term t = save[k].head;;t = t.link) {
			if(t.link.data ==j) {
				if(t.link == save[k].tail) {
					save[k].tail = t;
					t.link = null;
				}else {
					t.link = t.link.link;
				}
				break;
			}
		}
	}
	
	//BFS로 탐색하면서 방문하는 노드를 출력
	public void bfs(int vertex) {
		int start = vertex;
		String[] way = new String[noVertex];
		for(int i = 0; i < noVertex; i++) {
			way[i] = "f";
		}
		Queue road = new Queue(noVertex);
		
		road.enque(start);
		way[start] = "t";
		String say = Integer.toString(save[start].head.data);
		int n = 0;
		while(!road.isEmpty()) {
			if(n == 0) {
				road.deque();
				n = n + 1;
			}
			for(ListNode.Term t = save[start].head.link; t != null; t = t.link) {
				if(way[t.data] != "t") {
					road.enque(t.data);
					way[t.data] = "t";
				}
			}
			start = road.deque();
			say = say + " " + Integer.toString(start);
		}
		System.out.println(say);
	}
	
	//DFS로 탐색하면서 방문하는 노드를 출력
	public void dfs(int vertex) {
		int start = vertex;
		String[] way = new String[noVertex];
		for(int i = 0; i < noVertex; i++) {
			way[i] = "f";
		}
		Stack road = new Stack(noVertex);
		Stack last = new Stack(noVertex*noVertex);
	
		road.push(start);
		way[start] = "t";
		String say = Integer.toString(save[start].head.data);
		int n = 0;
		for(int i = 0; i < noVertex; i++) {
			last.push(i);
		}
		while(!road.isEmpty()) {
			if(n == 0) {
				road.pop();
				last.pop();
				n = n +1;
			}else {
				start = road.pop();
			}
			if(way[save[start].tail.data]== "t") {
				while(way[save[start].tail.data]=="t") {
					save[start].rmtail();
					if(save[start].tail == save[start].head) {
						break;
					}
				}
			}
			if(way[save[start].tail.data] == "f" && save[start].tail != save[start].head) {
				road.push(save[start].tail.data);
				say = say + " " + Integer.toString(save[start].tail.data);
				way[save[start].tail.data] = "t";
			}
			if(road.isEmpty()) {
				while(!last.isEmpty()) {
					int k = last.pop();
					if(way[k] == "f") {
						say = say + " " + Integer.toString(k);
					}
				}
				if(way[start] == "f") {
					say = say + " " + Integer.toString(start);
				}
			}
		
		}
		System.out.println(say);
	}
	
	//그래프를 출력하는 메소드
	String result;
	public void print() {
		for(int p = 0; p < noVertex ; p++) {
			result = Integer.toString(save[p].head.data);
			for(ListNode.Term t = save[p].head.link; t != save[p].tail.link ; t = t.link) {
				result = result + " " + Integer.toString(t.data);
			}
			System.out.println(result);
		}
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		int noVertex = scan.nextInt();
		Graph graph = new Graph(noVertex);
		
		for(int i = 0; i < noVertex; i++) {
			for(int j = 0; j < noVertex; j++) {
				int vertex = scan.nextInt();
				if(vertex == 1) {
					graph.addEdge(i,j);
				}
			}
		}
		
		while(scan.hasNext()) {
			String cmd = scan.next();
			if(cmd.equals("P")) {
				graph.print();
			}else if(cmd.equals("I")) {
				int from = scan.nextInt();
				int to = scan.nextInt();
				graph.addEdge(from, to);
			}else if(cmd.equals("D")) {
				int from = scan.nextInt();
				int to = scan.nextInt();
				graph.removeEdge(from, to);
			}else if(cmd.equals("DFS")) {
				int vertex = scan.nextInt();
				graph.dfs(vertex);
			}else if(cmd.equals("BFS")) {
				int vertex = scan.nextInt();
				graph.bfs(vertex);
			}else if(cmd.equals("E")) {
				break;
				//종료
			}
		}
		
		scan.close();

	}

}


package heap;
import java.io.*;
import java.util.Scanner;

class MaxHeap{
	private int size = 0; // MaxHeap에 저장된 데이터 갯수
	private int[] heap; //MaxHeap
	
	public MaxHeap(int capacity) {
		heap = new int[capacity];
	}
	
	public void print() {
		for(int i = 0; i < size; i++) {
			System.out.print(heap[i + 1] + " ");
		}
	}
		
	public void insert(int x) {
		int checkpoint;
		size = size +1;
		heap[size] = x;
		if(size != 1) {
			checkpoint = size;
			while(heap[checkpoint/2] < x && checkpoint/2 != 0){
				heap[checkpoint] = heap[checkpoint/2];
				if(checkpoint/2 != 0) {
					checkpoint = checkpoint/2;
				}
			}
			heap[checkpoint] = x;
		}
		
	}
		
	public int delete() {
		int find = heap[1];
		heap[1] = heap[size];
		size = size -1;
		int point = heap[1];
		int check = 1;
		while(point < heap[check*2] || point < heap[(check*2) + 1]) {
			if(heap[check*2] > heap[(check*2) + 1]) {
				heap[check] = heap[check*2];
				check = check*2;
			}else if(heap[check*2] < heap[(check*2) + 1]) {
				heap[check] = heap[(check*2) + 1];
				check = (check*2) + 1;
 			}
		}
		heap[check] = point;
		
		return find;
	}
	
	
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		MaxHeap heap = new MaxHeap(100);
		
		while(scan.hasNext()) {
			String command = scan.next();
			
			if(command.equals("I")) {
				int data = Integer.parseInt(scan.next());
				heap.insert(data);
			}else if(command.equals("D")) {
				heap.delete();
			}else if(command.equals("P")) {
				heap.print();
			}else if(command.equals("E")) {
				break;
			}
		}
		scan.close();
	}

}

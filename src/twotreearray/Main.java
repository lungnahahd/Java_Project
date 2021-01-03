package twotreearray;
import java.io.*;
import java.util.Scanner;



//필요한 클래스를 추가하시오.

class Stack{
	int[] stack = new int[16];
	int top = -1;
	
	public boolean isFull() {
		return top > 15;
	}
	public void push(int item) {
		if(isFull()) {
			return;
		}
		top ++;
		stack[top] = item;
	}
		
	public int pop() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		top --;
		return stack[top + 1];
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	
	public int peek() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		return stack[top];
	}
}
class ListNode{
	class Term{
		String data;
		Term link;
	}
	public Term head;
	public Term tail;
	
	public void addTerm(String data) {
		Term term = new Term();
		term.data = data;
		term.link = null;
		if (head == null) {
			head = tail = term;
		}else {
			tail.link = term;
			tail = term;
		}
	}
}
class Queue{
	Object[] data;
	static int Size = 16;
	int front = 0;
	int rear = 0;
	int count;
	int incr = 10;
	
	public Queue() {
		data = new Object[Size];
	}
	public boolean isEmpty() {
		return count == 0;
	}
	public void full() {
		int oldsize = Size;
		Size = Size + incr;
		Object newq[] = new Object[Size];
		for(int i = 0; i < count; i++) {
			newq[i] = data[front];
			front = (front + 1) % oldsize;
		}
		data = newq;
		front = 0;
		rear = count;
		
	}
	public void enque(Object str) {
		if(count == Size) {
			full();
		}
		data[rear] = str;
		rear = (rear + 1) % Size;
		count ++;
	}
	public Object dequeue() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}else {
			Object find  = data[front];
			front = (front + 1) % Size;
			count --;
			return find;
		}
	}
}



class BinaryTree{
	static int key = 1;
	String[] array;
	public static final int INIT_SIZE = 10;
	public static final int ROOT = 1;
	
	public BinaryTree() {
		array = new String[INIT_SIZE];
	}
	public void set(int index, String data) {
		if(index >= array.length) {
			String[] newArray = new String[index + 10];
			for(int i = 0; i < array.length; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		array[index] = data;
		key = index;
	}
	//배열에서 원소를 얻는 메소드
	public String get(int index) {
		if(index >= array.length) {
			return null;
		}
		return array[index];
	}
	public void inorder() {
		inorder(ROOT);
	}
	public void preorder() {
		preorder(ROOT);
	}
	public void postorder() {
		postorder(ROOT);
	}
	public void levelorder() {
		levelorder(ROOT);
	}
	
	
	
	//+++++++++++++++++++++++작성해야 되는 함수
	//중위순회
	private void inorder(int idx) {
		Stack save = new Stack();
		//ListNode list = new ListNode();
		Queue real = new Queue();
		save.push(idx);
		while(!save.isEmpty()) {
			if(idx < array.length) {
				while(array[idx] != null) {
					if(idx * 2 < array.length) {
						if(array[idx*2] != null) {
							save.push(idx*2);
						}	
					
						idx = idx*2;
					}else {
						break;
					}
				}
			}
			idx = save.pop();
			real.enque(array[idx]);
			idx = (idx*2) + 1;
			if(idx < array.length) {
				if(array[idx] != null) {
					save.push(idx);
				}
			}
		}
		String result = null;
		while(!real.isEmpty()) {
			if(result == null) {
				result = (String)real.dequeue();
			}else {
				result = result + " " + real.dequeue();
			}
		}
		System.out.print(result);
	}
	//전위순회
	private void preorder(int idx) {
		Stack save = new Stack();
		Stack way = new Stack();
		Queue qlist = new Queue();
		save.push(idx);
		qlist.enque(array[idx]);
		while(!save.isEmpty()) {
			save.pop();
			if((idx*2) + 1 < array.length) {
				if(array[idx*2] != null && array[(idx*2) + 1] != null) {
					way.push(idx);
				}
			}
			if((idx*2) + 1 < array.length) {
				if(array[idx*2] != null) {
					idx = idx*2;
					save.push(idx);
					qlist.enque(array[idx]);
					if(idx == key) {
						break;
					}
				}else if(array[(idx*2) + 1] != null) {
					idx = (idx*2) + 1;
					save.push(idx);
					qlist.enque(array[idx]);
					if(idx == key) {
						break;
					}
				}else if(array[idx*2] == null && array[(idx*2) + 1] == null) {
					if(way.isEmpty()) {
						break;
					}
					idx = way.pop();
					idx = (idx*2) + 1;
					save.push(idx);
					qlist.enque(array[idx]);
				}
			}else {
				if(idx == key) {
					break;
				}
				idx = way.pop();
				idx = (idx*2) + 1;
				save.push(idx);
				qlist.enque(array[idx]);
			}
		}
		String result = null;
		while(!qlist.isEmpty()) {
			if(result == null) {
				result = (String)qlist.dequeue();
			}else {
				result = result + " " + qlist.dequeue();
			}
		}
		System.out.print(result);
	}
	//후위순회
	private void postorder(int idx) {
		if(array[idx] != null) {
			if((idx*2) + 1 < array.length) {
				postorder(idx*2);
				postorder((idx*2) + 1);
				System.out.print(array[idx] + " ");
			}else {
				System.out.print(array[idx] + " ");
			}
		}
	}
	
	private void levelorder(int idx) {
		String result = null;
		while(idx < array.length) {
			if(result == null) {
				result = array[idx];
				idx++;
			}else {
				if(array[idx] != null) {
					result = result + " " + array[idx];
				}
				idx++;
			}
		}
		System.out.print(result);
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		BinaryTree tree = new BinaryTree();
		int no = scan.nextInt();
		for(int i = 0; i < no; i++) {
			int index = scan.nextInt();
			String data = scan.next();
			tree.set(index, data);
		}
		scan.close();
		
		tree.inorder();
		System.out.println();
		
		tree.preorder();
		System.out.println();
		
		tree.postorder();
		System.out.println();
		
		tree.levelorder();
	}

}

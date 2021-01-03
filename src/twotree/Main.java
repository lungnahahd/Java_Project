package twotree;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;

class BinaryTree{
	String data;
	BinaryTree left;
	BinaryTree right;
	
	public BinaryTree(String data) {
		this.data = data;
		left = right = null;
	}
	
	public void setChildren(BinaryTree left, BinaryTree right) {
		this.left = left;
		this.right = right;
	}
}
class Stack{
	BinaryTree[] stack = new BinaryTree[16];
	int top = -1;
	
	public boolean isFull() {
		return top > 15;
	}
	public void push(BinaryTree item) {
		if(isFull()) {
			return;
		}
		top ++;
		stack[top] = item;
	}
		
	public BinaryTree pop() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		top --;
		return stack[top + 1];
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	public BinaryTree peek() {
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


public class Main {
	//in 중위 / pre 전위 / post 후위 / pre 전위 /
	public static void inorder(BinaryTree node) {
		Stack save = new Stack();
		ListNode list = new ListNode();
		save.push(node);
		while(!save.isEmpty()) {
			while(node != null) {
				if(node.left != null) {
					save.push(node.left);
				}
				node = node.left;
			}
			node = save.pop();
			list.addTerm(node.data);
			node = node.right;
			if(node != null) {
				save.push(node);
			}	
		}
		String result = list.head.data;
		list.head = list.head.link;
		for(;list.head != null;list.head = list.head.link) {
			result = result + " " + list.head.data;
		}
		System.out.print(result);
		
	}
	public static void preorder(BinaryTree node) {
		Stack save = new Stack();
		Stack way = new Stack();
		ListNode list = new ListNode();
		save.push(node);
		list.addTerm(node.data);
		while(!save.isEmpty()) {
			save.pop();
			if(node.right != null && node.left != null) {
				way.push(node);
			}
			if(node.left != null) {
				node = node.left;
				save.push(node);
				list.addTerm(node.data);
			}else if(node.right != null) {
				node = node.right;
				save.push(node);
				list.addTerm(node.data);
			}else if(node.left == null && node.right == null) {
				if(way.isEmpty()) {
					break;
				}
				node = way.pop();			
				node = node.right;
				save.push(node);
				list.addTerm(node.data);
				
			}
		}
		String result = list.head.data;
		list.head = list.head.link;
		for(;list.head != null;list.head = list.head.link) {
			result = result + " " + list.head.data;
		}
		System.out.print(result);
	}
	
	static Queue quesave = new Queue();
	static String say;
	
	public static void prepostorder(BinaryTree node) {
		if(node != null) {
			prepostorder(node.left);
			prepostorder(node.right);
			quesave.enque(node.data);
		}
	}
	public static void postorder(BinaryTree node) {
		prepostorder(node);
		while(!quesave.isEmpty()) {
			if(say == null) {
				say =(String)quesave.dequeue();
			}else {
				say = say + " " + quesave.dequeue();
			}
		}
		System.out.print(say);		
	}
	
	
	public static void levelorder(BinaryTree node) {
		Queue save = new Queue();
		Queue last = new Queue();
		
		save.enque(node);
		while(!save.isEmpty()) {
			BinaryTree s = (BinaryTree)save.dequeue();
			if(s != null) {
				last.enque(s.data);
				if(s.left != null) {
					save.enque(s.left);
				}
				if(s.right != null) {
					save.enque(s.right);
				}
			}
		}
		String result = null;
		while(!last.isEmpty()) {
			if(result == null) {
				result  = (String)last.dequeue();
			}else {
				result = result + " " + last.dequeue();
			}
		}
		System.out.print(result);
		
	}

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		Stack stack = new Stack();
		
		BinaryTree root = null;
		
		//이진 트리 생성
		while(scan.hasNext()) {
			String token = scan.next();
			if(token.equals("null")) {
				stack.push(null);
			}else if(token.equals(")")) {
				BinaryTree right = stack.pop();
				if(stack.isEmpty()) {
					root = right;
					break;
				}
				BinaryTree left = stack.pop();
				root = stack.pop();
				root.setChildren(left, right);
				stack.push(root);
			}else if(token.equals("(")) {
				continue;
			}else {
				stack.push(new BinaryTree(token));
			}
		}
		scan.close();
		inorder(root);
		System.out.println();
		preorder(root);
		System.out.println();
		postorder(root);
		System.out.println();
		levelorder(root);
	}

}

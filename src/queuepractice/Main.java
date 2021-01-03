package queuepractice;
import java.io.*;
import java.util.Scanner;

class Queue{
	private Object[] q;
	private int front, rear;
	private int n = 10;
	
	public Queue() {
		q = new Object[n];
		front = rear = -1;
	}
	public Queue(int i) {
		q = new Object[i];
		front = rear = -1;
		n = i;
	}
	
	public boolean isEmpty() {
		return rear == front;
	}
	public void enqueue(Object item) {
		if(rear == n-1) {
			throw new IndexOutOfBoundsException();
		}else {
			rear = rear + 1;
			q[rear] = item;
		}
	}
	public Object dequeue() {
		if(front == rear) {
			throw new IndexOutOfBoundsException();
		}else {
			front = front + 1;
			return q[front];
		}
	}
	public void delete() {
		if(front == rear) {
			throw new IndexOutOfBoundsException();
		}else {
			front = front + 1;
		}
	}
	public Object peek() {
		if(front == rear) {
			throw new IndexOutOfBoundsException();
		}else {
			return q[front + 1];
		}
	}
}



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

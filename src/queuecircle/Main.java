package queuecircle;
import java.io.*;
import java.util.Scanner;

class Queue{
	private Object q[];
	private int front,rear;
	private int count;
	private int queuesize;
	private int increment;
	
	public Queue() {
		front = rear = 0;
		count = 0;
		queuesize = 50;
		increment = 10;
		q = new Object[queuesize];
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public void queueFull() {
		int oldsize = queuesize;
		queuesize = queuesize + increment;
		Object newq[] = new Object[queuesize];
		for(int i = 0; i < count; i++) {
			newq[i] = q[front];
			front = (front +1) % oldsize;
		}
		q = newq;
		front = 0;
		rear = count; 
	}
	
	public void enqueue(Object item) {
		if(count == queuesize) {
			queueFull();
		}
		q[rear] = item;
		rear = (rear + 1) % queuesize;
		count ++;
	}
	public Object dequeue() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}else {
			Object find = q[front];
			front = (front + 1) % queuesize;
			count --;
			return find;
		}
	}
	public void delete() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException();
		}else {
			front = (front + 1) % queuesize;
			count--;
		}
	}
	public Object peek() {
		if(isEmpty()) {
			return null;
		}else {
			return q[front];
		}
	}
	
	public void print() {
		for(int i = front; i != rear; i++) {
			if(q[i] != null) {
				System.out.println(q[i]);
			}
		}
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

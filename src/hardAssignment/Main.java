package hardAssignment;
import java.io.*;
import java.util.Scanner;

import sun.tools.tree.ThisExpression;

@SuppressWarnings("serial") // 경고를 억제하는 구문
class NoDeepCopiedObjectException extends RuntimeException{
	
}

class GenList implements Cloneable{
	

	private class ListNode{
		private Object data;
		private ListNode link;
		//private ListNode head;
		public ListNode() {
			data = null;
			link = null;
		}
		
		
		public Object clone() throws CloneNotSupportedException {
			
		}
		
		public boolean equals(Object obj) {
			if(this == obj) {
				throw new NoDeepCopiedObjectException();
			}	GenList k = null;
			try {
				k = (GenList)(l.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			
			if(!(obj instanceof ListNode)) {
				return false;
			}
			
			ListNode node = (ListNode)obj;
			
			boolean b = false;
			if(data == null && node.data == null) {
				b = true;
			}else if(data != null && node.data !=null) {
				b = data.equals(node.data);
			}
			
			if(b) {
				if(link == null && node.link == null) {
					b = true;
				}else if(link != null && node.link != null) {
					b = link.equals(node.link);
				}
				
			}
			return b;
		}
	}
		
		private ListNode head;
		
		public void insertData(Object data) {
			ListNode newNode = new ListNode();
			newNode.data = data;
			newNode.link = head;
			head = newNode;
		}
		
		public void print() {
			System.out.print("(");
			for(ListNode p = head; p != null; p=p.link) {
				if(p.data instanceof GenList) {
					((GenList)p.data).print();
				}else {
					System.out.print(p.data);
				}
				if(p.link != null) {
					System.out.print(", ");
				}
				System.out.print(")");
			}
		}
			
			@Override
			public boolean equals(Object obj) {
				if(this.getClass() != obj.getClass()) {
					return false;
				}
				return head.equals(((GenList)obj).head);
			}
			
			@Override
			protected Object clone() throws CloneNotSupportedException{
				GenList list = new GenList();
				list.head = (ListNode) head.clone();
				return list;
			}
		
	
	}


public class Main {

	public static void main(String[] args) throws Exception {
		GenList p = new GenList();
		p.insertData(new Integer(82));
		p.insertData("Korea");
		
		GenList q = new GenList();
		q.insertData(p);
		q.insertData("Seoul");
		
		GenList r = new GenList();
		r.insertData(q);
		r.insertData("Busan");
		
		GenList l = new GenList();
		l.insertData(r);
		l.insertData(q);
		l.insertData("City");
		
		GenList k = null;
		try {
			k = (GenList)(l.clone());
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		System.out.println(l.equals(k));

	}

}

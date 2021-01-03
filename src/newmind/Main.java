package newmind;
import java.io.*;
import java.util.Scanner;

@SuppressWarnings("serial")
class NoDeepCopiedObjectException extends RuntimeException {
	

}

class GenList implements Cloneable {
	private class ListNode {
		private Object data;
		private ListNode link;
	
		public ListNode() {
			data = null;
			link = null;
		}
		

		// �ۼ��ؾ� �� (deep copy)\
		
		
		public ListNode(Object a, ListNode b) {
			data = a;
			link = b;
		}
		
		
	
		
		
		public Object clone() throws CloneNotSupportedException  {
			Object data = this.data;
			ListNode link = this.link;
			ListNode newNode = new ListNode();
			if(this !=null) {
				if (data instanceof ListNode) {
					ListNode stay = new ListNode(data,link);
					newNode.data = ((ListNode)stay.data).clone();
				}else {
					ListNode stay = new ListNode(data, link);
					 newNode.data= stay.data; 
				}
				if(link instanceof ListNode) {
					ListNode stay = new ListNode(data, link);
					newNode.link = (ListNode)stay.link.clone();
				}else {
					ListNode stay = new ListNode(data,link);
					newNode.link = (ListNode)stay.link;
				}
				
				
				/**if(link instanceof ListNode) {
					r = (ListNode)link.clone();
				}else {
					r = null;
				}**/
				
				
				
			}
			
			
			
			/**if ( this != null) {
			newNode.data = (data instanceof ListNode) ? ((ListNode)data).clone() : data;
			newNode.link = (ListNode)link.clone();
			}**/
			return newNode;
		}
	
		// �����ϸ� �ȵ�.
		
		public boolean equals(Object obj) {
			if ( this == obj) {
				throw new NoDeepCopiedObjectException();
			}

			if (!(obj instanceof ListNode))
				return false;

			ListNode node = (ListNode)obj;

			boolean b = false;
			if (data == null && node.data == null)
				b = true;
			else if (data != null && node.data != null)
				b = data.equals(node.data);

			if (b) {
				if (link == null && node.link == null)
					b = true;
				else if (link != null && node.link != null)
					b = link.equals(node.link);
				
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
		for(ListNode p = head; p != null; p = p.link) {
			if (p.data instanceof GenList)
				((GenList)p.data).print();
			else
				System.out.print(p.data);
			
			if (p.link != null)
				System.out.print(", ");
		}
		System.out.print(")");
	}

	@Override
	public boolean equals(Object obj) {
		// ���� obj instanceof GenList ���·� �˻��ϳ�
		// �̷��� �� ��� obj�� GenList�� ���� Ŭ������
		// ��쿡�� ��ġ�ϴ� ������ �򰡵Ǳ� ������
		// �� Ŭ������ ��Ȯ�� ��ġ�ϴ��� �˻��ϴ� ��� ���
		if (this.getClass() != obj.getClass())
			return false;
		
		return head.equals(((GenList)obj).head);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {  
		GenList list = new GenList();
		list.head = (ListNode)head.clone();
		return list;
	}
}

class Main {
	
	// main �޼ҵ�� �������� ���ÿ�.
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
	
		// ������� �� �� print �Լ� �̿�	
		// l.print();
		// System.out.println();

		GenList k = null;
		try {
			k = (GenList)(l.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		System.out.println(l.equals(k));
	}
}

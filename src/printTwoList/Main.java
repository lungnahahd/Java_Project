package printTwoList;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class ListNode{
	String data;
	ListNode rlink;
	ListNode llink;
}

class DoubleLinkedList{
	private ListNode head;
	private ListNode tail;
	
	public DoubleLinkedList() {
		head = tail = null;
	}
	int length = 1;
	public void insert(String str) {
		
		int a = 0;
		int b = 0;
		if(head == null) {
			ListNode sn = new ListNode();
			sn.data = str;
			head = sn;
			tail = head;
			sn.llink = null;
			length ++;
		}else {
			ListNode ln = new ListNode();
			ln.data = str;
			ln.llink = tail;
			tail.rlink = ln;
			tail = ln;
			ln.rlink = null;
			String[] st = new String[length];
			
			for(ListNode i = head ; i != null; i = i.rlink) {
				st[a] = i.data;
				a++;
			}
			Arrays.sort(st);
			for(ListNode i = head; i != null; i = i.rlink) {
				i.data = st[b];
				b++;
			}
			length ++;
			
			
		}
		
	}
	
	public void delete(String str) {
		if(head == null) {
			return;
		}else{
			for(ListNode n = head; n != null; n = n.rlink) {
				if(n.data.equals(str)) {
					if(n.llink == null && n.rlink != null) {
						head = n.rlink;
						n.rlink.llink = null;
						
					}else if(n.rlink == null && n.llink != null) {
						tail = n.llink;
						n.llink.rlink = null;
						
					}else if(n.rlink == null && n.llink == null) {
						head = tail =  null;
					}else {
						n.llink.rlink = n.rlink;
						n.rlink.llink = n.llink;
					}
				}
			}
		}
	}
	
	
	public void print() {
		if(head == null) {
			System.out.println("EMPTY");
			return;
		}
		//String str = "";
		for(ListNode p = head; p != null; p = p.rlink) {
			System.out.print(p.data + " ");
		}
		System.out.println();
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		DoubleLinkedList list = new DoubleLinkedList();
		
		while(true) {
			String cmd = scan.next();
			if (cmd.equals("E")) {
				break;
			}
			if(cmd.equals("I")) {
				list.insert(scan.next());
			}else if(cmd.equals("D")) {
				list.delete(scan.next());
			}else if (cmd.equals("P")) {
				list.print();
			}else {
				System.out.println("ERROR");
			}
		}
	

	}

}

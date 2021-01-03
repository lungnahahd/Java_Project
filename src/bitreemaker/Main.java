package bitreemaker;
import java.io.*;
import java.util.Scanner;

class BinarySearchTree{
	class TreeNode{
		String key;
		TreeNode left;
		TreeNode right;
	}
	
	private TreeNode rootNode;
	
	public void insert(String x) {
		rootNode = insertKey(rootNode,x);
	}
	
	public TreeNode find(String x) {
		TreeNode t = rootNode;
		int result;
		while(t != null) {
			if((result = x.compareTo(t.key)) < 0) {
				t = t.left;
			} else if (result == 0) {
				return t;
			}else {
				t = t.right;
			}
		}
		return t;
	}
	
	private void printNode(TreeNode n) {
		if( n != null) {
			System.out.print("(");
			printNode(n.left);
			System.out.print(n.key);
			printNode(n.right);
			System.out.print(")");
		}
	}
	
	public void print() {
		printNode(rootNode);
		System.out.println();
	}
	
	private TreeNode insertKey(TreeNode t, String x) {
		if(t == null) {
			TreeNode result = new TreeNode();
			result.key = x;
			t = result;
		}else if(x.compareTo(t.key) < 0) {
			if(t.left == null) {
				TreeNode result = new TreeNode();
				result.key = x;
				t.left = result;
				return t;
			}
			insertKey(t.left,x);
			
		}else if(x.compareTo(t.key) > 0) {
			if(t.right == null) {
				TreeNode result = new TreeNode();
				result.key = x;
				t.right = result;
			}
			insertKey(t.right,x);
		}
		return t;
	}
}



public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		BinarySearchTree tree = new BinarySearchTree();
		
		while(scan.hasNext()) {
			String command = scan.next();
			
			if(command.equals("I")) {
				String data = scan.next();
				tree.insert(data);
			}else if(command.equals("P")) {
				tree.print();
			}
		}
		scan.close();

	}

}

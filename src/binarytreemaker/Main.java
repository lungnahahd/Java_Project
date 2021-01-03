package binarytreemaker;
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
		rootNode = insertKey(rootNode, x);
	}
	private TreeNode findpar;
	
	public TreeNode find(String x) {
		TreeNode t = rootNode;
		int result;
		findpar = null;
		
		while(t != null) {
			if((result = x.compareTo(t.key)) < 0) {
				findpar = t;
				t = t.left;
			}else if(result == 0) {
				return t;
			}else {
				findpar = t;
				t = t.right;
			}
		}
		return t;
	}
	
	
	

	
	private void printNode(TreeNode n) {
		if(n != null) {
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

	public TreeNode mostbig(TreeNode st) {
		if(st.right != null) {
			st = st.right;
			mostbig(st);
		}
		return st.right;
	}
	
	public void delete(String x) {
		TreeNode thing = find(x);
		
		if(thing.left == null && thing.right == null && thing != rootNode) {
			if(findpar.left == thing) {
				findpar.left = null;
			}else {
				findpar.right = null;
			}
		}else if(thing.left == null && thing.right != null) {
			if(findpar.left == thing) {
				findpar.left = thing.right;
			}else if(findpar.right == thing) {
				findpar.right = thing.right;
			}
		}else if(thing.right == null && thing.left != null) {
			if(findpar.left == thing) {
				findpar.left = thing.left;
			}else if(findpar.right == thing) {
				findpar.right = thing.left;
			}
		}else {
			TreeNode big = mostbig(thing.left);
			find(big.key);
			TreeNode bigmom = findpar;
			thing.key = big.key;
			if(big.left != null) {
				bigmom.right = big.left;
			}else {
				bigmom.right = null;
			}
		}
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
			}else if(command.equals("D")) {
				String data = scan.next();
				tree.delete(data);
			}else if(command.equals("P")) {
				tree.print();
			}
		}
		scan.close();
	}

}

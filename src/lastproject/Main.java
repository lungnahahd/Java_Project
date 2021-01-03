package lastproject;
import java.io.*;
import java.util.Scanner;

class Graph{
	int noVertex; //������ ����
	int[][] m; // �������
	
	public Graph(int noVertex) {
		this.noVertex = noVertex;
		m = new int[noVertex][noVertex];
	}
	//�ʿ��� �޼ҵ峪 �ʵ带 �߰�
	
	public int[][] getTransitiveClosure() {
		int[][] closure = new int[noVertex][noVertex];
		for(int k = 0; k < noVertex; k = k+1) {
			for(int a = 0; a < noVertex; a++) {
				for(int b = 0; b < noVertex; b++) {
					if(m[a][b] == 1 || m[a][k] + m[k][b] > 1) {
						closure[a][b] = 1;
					}
					
				}
			}
		}
		for(int k = 0; k < noVertex; k = k+1) {
			for(int a = 0; a < noVertex; a++) {
				for(int b = 0; b < noVertex; b++) {
					if(closure[a][b] == 1 || closure[a][k] + closure[k][b] > 1) {
						closure[a][b] = 1;
					}
					
				}
			}
		}
		return closure;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int noVertex = scan.nextInt(); // ������ ����
		
		Graph g = new Graph(noVertex); //�׷��� ����
		for(int i = 0; i < noVertex; i++) {
			for(int j = 0; j < noVertex; j++) {
				g.m[i][j] = scan.nextInt();
			}
		}
		
		int [][] mat = g.getTransitiveClosure();
		/**for(int[] m : mat) {
			for(int exist : m) {
				System.out.print(exist + " ");
			}
			System.out.println();
		}**/
		for(int a = 0; a < noVertex; a++) {
			String result = Integer.toString(mat[a][0]);
			for(int b = 1; b < noVertex; b++) {
				result = result + " " + Integer.toString(mat[a][b]);
			}
			System.out.println(result);
		}

	}

}

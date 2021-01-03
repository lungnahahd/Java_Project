package matrixassignment;
import java.io.*;
import java.util.Scanner;

class SparseMatrix{
	int[][] m;
	int[][] a;
	int one;
	int two;
	int three;
	public SparseMatrix(int row, int col, int no) {
		m = new int[no + 1][3];
		m[0][0] = col;
		m[0][1] = row;
		m[0][2] = no;
		this.one = col;
		this.two = row;
		this.three = no;
		a = new int[no+1][3];
		//a[0][0] = two;
		//a[0][1] = one;
		//a[0][2] = three;
	}
	//전치 행렬 변환 메소드 transpose()
	//int[][] a;
	//int t = 1;
	int p = 1;
	public void transpose() {
		a[0][0] = m[0][1];//two;
		a[0][1] = m[0][0];//one;
		a[0][2] = m[0][2];//three;
		for(int check = 0; check <two; check++) {
			for(int down = 1; down <= three;down++ ) {
				if(m[down][1] == check) {
					a[p][0] = m[down][1];
					a[p][1] = m[down][0];
					a[p][2] = m[down][2];
					this.p = p+1;
				}
			}
		}
		
		
		
		
		
	}
		
	
	public String toString(){
		StringBuffer str = new StringBuffer();
		for(int i = 0; i<=m[0][2]; i++) {
			str.append(a[i][0]).append(" ").append(a[i][1]).append(" ").append(a[i][2]).append("\n");
		}
		return str.toString();
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int col = scan.nextInt();
		int row = scan.nextInt();
		int no = scan.nextInt();
		
		SparseMatrix matrix = new SparseMatrix(row,col,no);
		
		for(int i = 1;i <= no; i++) {
			matrix.m[i][0] = scan.nextInt();
			matrix.m[i][1] = scan.nextInt();
			matrix.m[i][2] = scan.nextInt();
		}
		scan.close();
		
		matrix.transpose();
		
		System.out.print(matrix);

	}

}

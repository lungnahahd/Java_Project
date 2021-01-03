package arrayassignment1;
import java.io.*;
import java.util.Scanner;

class Polynomial {
	private int[][] term;
	private int curNoTerm = 0;
	
	int z = 0;
	int w = 0;
	
	//@param noTerm 항의 갯수
	public Polynomial(int noTerm) {
		term = new int[noTerm][2];
	}
	
	public Polynomial() {
		this(20); // default로 최대 20개의 항을 저장함
		 // 바로 위에 생성자를 부르는 것 (생성자에서 생성자 부르기) = Polynomial(20)호출
	}
	// @param coef(=계수) @param exp(=지수)
	
	public void addTerm(int coef, int exp) {
		term[curNoTerm][0] = exp;
		term[curNoTerm][1] = coef;
		curNoTerm++;
		z ++;
	}
	//@param exp
	//작성하시오
	
	public int maxExp(int k) {
		return term[k][0];
	}
	public int pcoef(int k) {
		return term[k][1];
	}
		
	

	public void delTerm(int exp) {
		term[exp][0] = 0;
		term[exp][1] = 0;
		
	}
	
	static int st = 0;
	String[] pri = new String[20];
	int r = 1;
	public String toString() {
		
		pri[0] = "0";
		for(;st<curNoTerm;) {
			if(pri[0] == "0") {
				if(pcoef(st)==0) {
					pri[0] = "0";
					st++;
				}else if(pcoef(st) != 0) {
					pri[0]= pcoef(st) + "x^" + maxExp(st);
					st++;
				}
			}else if(pri[0] != "0") {
				if(maxExp(st)== 0 && pcoef(st)==0) {
					pri[r] = pri[r-1];
					r++;
					st++;
					
				}else if(maxExp(st) == 0) {
					pri[r] = pri[r-1] + "+" + pcoef(st);
					r++;
					st++;
				}else if(pcoef(st) == 0) {
					pri[r] = pri[r-1];
					r++;
					st++;
				}else if(maxExp(st) == 1) {
					if(pcoef(st) == 1) {
						pri[r] = pri[r-1] + "+" + "x";
						r++;
						st++;
					}else {
						pri[r] = pri[r-1] + "+" + pcoef(st) + "x";
						r++;
						st++;
					}
				}else if(pcoef(st) == 1) {
					pri[r] = pri[r-1] + "+" +"x^" + maxExp(st);
					r++;
					st++;
				}else {
					pri[r] = pri[r-1] + "+" + pcoef(st) + "x^" + maxExp(st);
					st++;
					r++;
				}
			}
		}
		return pri[r-1];
				
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static int Av = 0;
	static int Bv = 0;
	
	public static Polynomial polyAdd(Polynomial p1, Polynomial p2) {
		Polynomial sum = new Polynomial();
	
		while(Av < p1.z && Bv < p2.z) {
			
			if(p1.maxExp(Av) > p2.maxExp(Bv)) {
				sum.addTerm(p1.pcoef(Av), p1.maxExp(Av));
				p1.delTerm(Av);
				Av++;
			}else if(p1.maxExp(Av) < p2.maxExp(Bv)) {
				sum.addTerm(p2.pcoef(Bv), p2.maxExp(Bv));
				p2.delTerm(Bv);
				Bv++;
			}else {
				sum.addTerm(p1.pcoef(Av)+p2.pcoef(Bv), p1.maxExp(Av));
				p1.delTerm(Av);
				p2.delTerm(Bv);
				Av ++;
				Bv ++;
			}
		}
		if(Bv < p2.z) {
			while(Bv < p2.z) {
				sum.addTerm(p2.pcoef(Bv), p2.maxExp(Bv));
				p2.delTerm(Bv);
				Bv ++;
			}
		}
		if(Av < p1.z) {
			while(Av < p1.z) {
				sum.addTerm(p1.pcoef(Av), p1.maxExp(Av));
				p1.delTerm(Av);
				Av ++;
			}
		}
			
			return sum;
		
		
	}
	
}

	




public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		//첫번째 다행식 입력
		Polynomial p1 = new Polynomial();
		int no = scan.nextInt();
		for(int i = 0; i < no; i++) {
			int coef = scan.nextInt();
			int exp = scan.nextInt();
			p1.addTerm(coef, exp);
		}
		
		Polynomial p2 = new Polynomial();
		int po = scan.nextInt();
		for(int i = 0; i< po ; i++) {
			int coef = scan.nextInt();
			int exp = scan.nextInt();
			p2.addTerm(coef, exp);
		}
		scan.close();
		
		Polynomial p3 = Polynomial.polyAdd(p1, p2);
		
		//System.out.println(p3);
		System.out.print(p3); 
		// 이것은 System.out.print(p3.toString())과 동일
		//p3
	}
}

